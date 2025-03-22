package hexlet.code.controllers;


import hexlet.code.dto.UrlPage;
import hexlet.code.dto.UrlsPage;
import hexlet.code.dto.BasePage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;

import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlsController {
    public static void root(Context ctx) {

        String flashType = ctx.consumeSessionAttribute("flashType");
        String flashMessage = ctx.consumeSessionAttribute("flashMessage");

        var page = new BasePage();
        page.setFlashType(flashType);
        page.setFlashMessage(flashMessage);
        ctx.render("index.jte", model("page", page));
    }

    // для 7 этапа заготовил
    public static void checkPath(Context ctx) throws SQLException {

        ctx.sessionAttribute("flashMessage", "Некорректный URL");
        ctx.sessionAttribute("flashType", "danger");
        ctx.redirect(NamedRoutes.rootPath());
    }
    public static void addUrl(Context ctx) throws SQLException {
        var urlsName = ctx.formParam("url");
        var ldt = LocalDateTime.now();

        // получает url который ввели
        URL uri = null;
        try {
            uri = new URI(urlsName).toURL();
        } catch (Exception e) {
            ctx.sessionAttribute("flashMessage", "Некорректный URL");
            ctx.sessionAttribute("flashType", "danger");
            ctx.redirect(NamedRoutes.rootPath());
            return;
        }

        String protocol = uri.getProtocol();
        String host = uri.getHost();
        String port = String.valueOf(uri.getPort());

        // собрали полное имя хост с протоколом и портом
        String newUrl = protocol + "://" + host + ((port.equals("-1") ? "" : (":" + port)) + "/");

        String flashType = null;
        String flashMessage = null;

        // если такого URL нет в базе
        if (UrlsRepository.findByName(newUrl) == null) {
            var myUrl = new Url(newUrl, ldt);
            UrlsRepository.save(myUrl);  // сохранили его
           // ctx.sessionAttribute("flashType", "success");\
            flashType = "success";
           // ctx.sessionAttribute("flashMessage", "Страница успешно добавлена");
            flashMessage = "Страница успешно добавлена";
        } else {
            // сделали флэш уведомление
            ctx.sessionAttribute("flashType", "info");
            ctx.sessionAttribute("flashMessage", "Страница уже существует");
            ctx.redirect(NamedRoutes.rootPath()); // если есть, то обратно на ввод
        }

        // показать список url-ов
        var allUrls = UrlsRepository.getEntities();
        var page = new UrlsPage(allUrls);

        page.setFlashMessage(flashType);
        page.setFlashType(flashMessage);
        ctx.render("urlslist.jte", model("page", page));
    }

    public static void showUrls(Context ctx) throws SQLException {

        // показать список url-ов
        var allUrls = UrlsRepository.getEntities();
        var page = new UrlsPage(allUrls);
        ctx.render("urlslist.jte", model("page", page));
    }

    public static void showUrl(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id",Long.class).get();

        var url = UrlsRepository.findById(id);
        var page = new UrlPage(url);
        ctx.render("check.jte", model("page", page));
    }
}
