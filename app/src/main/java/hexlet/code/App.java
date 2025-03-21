package hexlet.code;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.stream.Collectors;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import controllers.UrlsController;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;
import hexlet.code.model.Url;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import lombok.extern.slf4j.Slf4j;

import hexlet.code.repository.BaseRepository;

import static io.javalin.rendering.template.TemplateUtil.model;

@Slf4j
public class App {

    public static String getUrlDb() {
        return System.getenv()
                .getOrDefault("JDBC_DATABASE_URL", "jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;");
    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }

    private static String readResourceFile(String fileName) throws IOException {
        var inputStream = App.class.getClassLoader().getResourceAsStream(fileName);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    public static Javalin getApp() throws IOException, SQLException {

        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;");

        var dataSource = new HikariDataSource(hikariConfig);
        var sql = readResourceFile("schema.sql");

        log.info(sql);
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
        });

        // основная страница
        app.get(NamedRoutes.rootPath(), UrlsController::root);
        app.post(NamedRoutes.urlsPath(), UrlsController::addUrl);
        app.get(NamedRoutes.urlsPath(), UrlsController::showUrls);
        app.get(NamedRoutes.urlPath("{id}"), UrlsController::showUrl);

        // для 7 этапа заготовил
        app.post(NamedRoutes.checkPath("{id}"), UrlsController::checkPath);

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
