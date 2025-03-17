package controllers;


import hexlet.code.dto.UrlPage;
import hexlet.code.model.Url;
import io.javalin.http.Context;

import java.time.LocalDateTime;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlsController {
    public static void root(Context ctx) {
        System.out.println(" !!!!!! --------------!!!!!!!!!!!!!!!!!! !! ---");

        var gogo = new Url();
        gogo.setId(134);
        gogo.setName("hellllooooppp!!!");
        var page = new UrlPage(gogo);

        ctx.render("index.jte", model("page", page));
    }
}
