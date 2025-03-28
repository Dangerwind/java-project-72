package hexlet.code.controllers;

import hexlet.code.dto.UrlPage;
import hexlet.code.dto.UrlsPage;
import hexlet.code.dto.BasePage;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;

import hexlet.code.repository.CheckRepository;
import hexlet.code.repository.UrlsRepository;

import hexlet.code.util.NamedRoutes;

import io.javalin.http.Context;
import static io.javalin.rendering.template.TemplateUtil.model;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

import io.javalin.http.NotFoundResponse;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import kong.unirest.UnirestException;
import org.jsoup.Jsoup;

public class UrlsController {

    public static void page404(Context ctx) {
        var page = new BasePage();
        page.setFlashType(ctx.consumeSessionAttribute("flashType"));
        page.setFlashMessage(ctx.consumeSessionAttribute("flashMessage"));
        ctx.render("404.jte", model("page", page));
    }
// --- главная страница ----------------------------------------------------------------------
    public static void root(Context ctx) {
        var page = new BasePage();
        page.setFlashType(ctx.consumeSessionAttribute("flashType"));
        page.setFlashMessage(ctx.consumeSessionAttribute("flashMessage"));
        ctx.render("index.jte", model("page", page));
    }

// --- делаем проверку сайта -------------------------------------------------------
    public static void checkPath(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var urlNameForCheck = UrlsRepository.findById(id)
                .orElseThrow(() -> new  NotFoundResponse("Нет Url с таки ID"));
        try {
            HttpResponse<String> response = Unirest
                    .get(urlNameForCheck.getName())
                    .asString();
            var body = Jsoup.parse(response.getBody());
            var statusCheck = response.getStatus();


            var titleText = body.title();

            var getSome = body.selectFirst("h1");
            var h1 = getSome != null ? getSome.text() : "";

            getSome = body.selectFirst("meta[name=description]");
            var description = getSome != null ? getSome.attr("content") : "";

            var createAt = LocalDateTime.now();
            var rr1 = new UrlCheck(statusCheck, titleText, h1,
                    description, id, createAt);

            CheckRepository.save(rr1);
            ctx.sessionAttribute("flashMessage", "Страница успешно проверена");
            ctx.sessionAttribute("flashType", "info");
            ctx.redirect(NamedRoutes.urlPath(id));
        } catch (UnirestException e) {
            ctx.sessionAttribute("flashMessage", "Некорректный адрес");
            ctx.sessionAttribute("flashType", "danger");
            ctx.redirect(NamedRoutes.urlPath(id));
        }
    }

// --- добавляет сайт и выводит станицу списка всех сайтов и когда они были проверены -------------
    public static void addUrl(Context ctx) throws SQLException {
        var urlsName = ctx.formParamAsClass("url", String.class)
                .check(n -> !n.isEmpty(), "Пустой URL")
                .get()
                .trim();

        // получает url который ввели
        URL uri = null;
        try {
            assert urlsName != null;
            uri = new URI(urlsName).toURL();
        } catch (Exception e) {
            ctx.sessionAttribute("flashMessage", "Некорректный URL");
            ctx.sessionAttribute("flashType", "danger");
            ctx.redirect(NamedRoutes.rootPath());
            return;
        }

        String protocol = uri.getProtocol();
        String host = uri.getHost();
        int port = uri.getPort();
        // собрали полное имя хост с протоколом и портом
        String newUrl = protocol + "://" + host + ((port == -1 ? "" : (":" + port))); //  + "/");

        // если такой URL есть в базе
        if (UrlsRepository.findByName(newUrl).isPresent()) {
            ctx.sessionAttribute("flashType", "info");
            ctx.sessionAttribute("flashMessage", "Страница уже существует");
            ctx.redirect(NamedRoutes.rootPath());
            return;
        }
        var ldt = LocalDateTime.now();
        var myUrl = new Url(newUrl, ldt);
        UrlsRepository.save(myUrl);  // сохранили его

        // показать список url-ов
        var allUrls = UrlsRepository.getEntities();
        var lastCheck = CheckRepository.findLast();
        var page = new UrlsPage(allUrls, lastCheck);

        page.setFlashType("success");
        page.setFlashMessage("Страница успешно добавлена");
        ctx.render("urlslist.jte", model("page", page));
    }

// --- выводит страницу со списком сайтов и когда была проверка ----------------------------
    public static void showUrls(Context ctx) throws SQLException {
        var allUrls = UrlsRepository.getEntities();
        var lastCheck = CheckRepository.findLast();
        var page = new UrlsPage(allUrls, lastCheck);
        page.setFlashType(ctx.consumeSessionAttribute("flashType"));
        page.setFlashMessage(ctx.consumeSessionAttribute("flashMessage"));
        ctx.render("urlslist.jte", model("page", page));
    }

// -- страница по одному сайту когда и какие проверки были ----------------------------------
    public static void showUrl(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();

        Optional<Url> url = UrlsRepository.findById(id);
        if (url.isEmpty()) {
            ctx.sessionAttribute("flashMessage", "Некорректный адрес");
            ctx.sessionAttribute("flashType", "danger");
            ctx.redirect(NamedRoutes.page404());
            return;
        }
        var urls = CheckRepository.findById(id);
        var page = new UrlPage(url.get(), urls);

        page.setFlashType(ctx.consumeSessionAttribute("flashType"));
        page.setFlashMessage(ctx.consumeSessionAttribute("flashMessage"));
        ctx.render("check.jte", model("page", page));
    }
}
