package hexlet.code;


import java.io.IOException;
import java.sql.SQLException;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
public class App {
    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        /*
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });
*/

        app.get("/", ctx -> ctx.result("Welcome to Hexlet!"));
        return app;

    }

    public static void main(String[] args) throws IOException, SQLException {
        Javalin app = getApp();
        app.start(7070);
    }

}
