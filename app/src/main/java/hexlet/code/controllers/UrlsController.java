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

import io.javalin.http.NotFoundResponse;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import kong.unirest.UnirestException;
import org.jsoup.Jsoup;

public class UrlsController {

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
            //  ctx.redirect(NamedRoutes.urlPath(id));
        } catch (UnirestException e) {
            ctx.sessionAttribute("flashMessage", "Некорректный адрес");
            ctx.sessionAttribute("flashType", "danger");
            //ctx.redirect(NamedRoutes.urlPath(id));
        }
// !!!! правки часть 3, 1 комментарий - вывел редирект из try-catch
        ctx.redirect(NamedRoutes.urlPath(id));
    }

// --- добавляет сайт и выводит станицу списка всех сайтов и когда они были проверены -------------
    public static void addUrl(Context ctx) throws SQLException {
        var urlsName = ctx.formParamAsClass("url", String.class).get();

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
        String newUrl = protocol + "://" + host + ((port == -1 ? "" : (":" + port)));

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
// !!!! правки часть 2, 1 комментарий - сделал редирект на показ добавленных urls
        ctx.sessionAttribute("flashMessage", "Страница успешно добавлена");
        ctx.sessionAttribute("flashType", "success");
        ctx.redirect(NamedRoutes.urlsPath());
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

// !!!! правки часть 2, 2 комментарий - если не нашел - выкинул исключение 404 Not Found
// !!!! правки часть 3, 2 комментарий - убрал лишнюю обертку Optional
        var url = UrlsRepository.findById(id)
                .orElseThrow(() -> new NotFoundResponse("404, Not Found, id=" + id + " is wrong!"));

// !!!! правки часть 3, 3 комментарий - тут же мы в List<UrlCheck> запихиваем все что находится по ID
// потому я оставил этот поиск. Или я не прав?
        var urls = CheckRepository.findById(id);
        var page = new UrlPage(url, urls);

        page.setFlashType(ctx.consumeSessionAttribute("flashType"));
        page.setFlashMessage(ctx.consumeSessionAttribute("flashMessage"));
        ctx.render("check.jte", model("page", page));
    }
}
