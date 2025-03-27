import hexlet.code.App;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import okhttp3.mockwebserver.MockWebServer;

public class AppTest {
    private Javalin app;
    public static MockWebServer mServer;

    public static String readFixtures(String fileName) throws IOException {
        Path fPath = Paths.get("src/test/resources/", fileName);
        return new String(Files.readAllBytes(fPath));
    }

    @BeforeAll
    public static void startMServer() throws IOException {
        mServer = new MockWebServer();
        mServer.enqueue(new MockResponse().setBody(readFixtures("fixtura.html")));
        mServer.start();
    }

    @AfterAll
    public static void stopMServer() throws IOException {
        mServer.shutdown();
    }

    @Test
    public void testMock() throws SQLException {
        var mockUrlString = mServer.url("/").toString();
        Url mockUrl = new Url(mockUrlString);
        UrlsRepository.save(mockUrl); // сохранили у себя url для проверки

        // -----

        var idInBase = UrlsRepository.findByName(mockUrlString);
        assertNotNull(idInBase);  // это если не добавилось в базу

        JavalinTest.test(app, (server, client) -> {
            var response = client.post(NamedRoutes.checkPath(idInBase.get().getId()));
            assertThat(response.code()).isEqualTo(200);

            response = client.get(NamedRoutes.urlPath(idInBase.get().getId()));
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                    .contains("Это мока-затычка для проверки");
        });


    }

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

            // пришел ли в базу url
            var savedId = UrlsRepository.findById(1L);
           // System.out.println(" ");
            assertEquals(savedId.get().getName(), fixture);

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
