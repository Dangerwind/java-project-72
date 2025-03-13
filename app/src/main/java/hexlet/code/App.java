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
//  получаем порт из окружения, если его нет то 7070
    public static int getPort() {
        return Integer.parseInt(System.getenv()
                .getOrDefault("PORT", "7070"));
    }
    public static void main(String[] args) throws IOException, SQLException {
        Javalin app = getApp();
        app.start(getPort());
    }

}


/*
Hostname  dpg-cv9fh7jqf0us73au31rg-a
Port  5432
Database  mypostgresql_jx6b
Username  mypostgresql_jx6b_user
Password  5zDdSq0JuJIoQ3qDp3f7WljlGDJ88FrT
Internal Database URL   postgresql://mypostgresql_jx6b_user:5zDdSq0JuJIoQ3qDp3f7WljlGDJ88FrT@dpg-cv9fh7jqf0us73au31rg-a/mypostgresql_jx6b
External Database URL   postgresql://mypostgresql_jx6b_user:5zDdSq0JuJIoQ3qDp3f7WljlGDJ88FrT@dpg-cv9fh7jqf0us73au31rg-a.oregon-postgres.render.com/mypostgresql_jx6b
PSQL Command   PGPASSWORD=5zDdSq0JuJIoQ3qDp3f7WljlGDJ88FrT psql -h dpg-cv9fh7jqf0us73au31rg-a.oregon-postgres.render.com -U mypostgresql_jx6b_user mypostgresql_jx6b

 */
