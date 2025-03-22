package hexlet.code;

import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

/*
        app.get(NamedRoutes.rootPath(), UrlsController::root);
        app.post(NamedRoutes.urlsPath(), UrlsController::addUrl);
        app.get(NamedRoutes.urlsPath(), UrlsController::showUrls);
        app.get(NamedRoutes.urlPath("{id}"), UrlsController::showUrl);
 */
public class AppTest {
    private Javalin app;

    @BeforeEach
    public final void setUp() throws IOException, SQLException {
        app = App.getApp();
    }

    @Test
    public void testRootPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.rootPath());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                    .contains("Бесплатная проверка сайтов на SEO пригодность.");
        });
    }

    @Test
    public void testAddUrlPage() {

        JavalinTest.test(app, (server, client) -> {

            String fixture = "http://www.testurl.com/";
            // add url
            String urlForAdding = "url=" + fixture;
            var response = client.post(NamedRoutes.urlsPath(), urlForAdding);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                    .contains(fixture);

            // show url
            response = client.get(NamedRoutes.urlPath(1L));
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                    .contains(fixture);

            // checking the d.base for recording and reading
            var savedId = UrlsRepository.findById(1L);
            System.out.println(" ");
            assertEquals(savedId.getName(), fixture);

        });
    }

    @Test
    public void testShowUrlsPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlsPath());
            assertThat(response.code()).isEqualTo(200);
        });
    }



}
