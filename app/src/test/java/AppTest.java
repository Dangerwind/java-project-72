import hexlet.code.App;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.CheckRepository;
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
import static org.junit.jupiter.api.Assertions.assertFalse;

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
// !!!!! правка по последнему пункты - вынес вперед BeforeEach
    @BeforeEach
    public final void setUp() throws IOException, SQLException {
        app = App.getApp();
    }

    @AfterAll
    public static void stopMServer() throws IOException {
        mServer.shutdown();
    }
// !!!!! исправление по 2 пункту - заменил имя на более понятное testCheckPage !!!!!!!!!!!!!!!
    @Test
    public void testCheckPage() throws SQLException {
        var mockUrlString = mServer.url("/").toString();
        Url mockUrl = new Url(mockUrlString);
        UrlsRepository.save(mockUrl); // сохранили у себя url для проверки

// !!!!! исправление 3-4 пунктам - убрал чтение из репозитория и взял ID из URL и убрал проверку на null
        var idInBase = mockUrl.getId();

        JavalinTest.test(app, (server, client) -> {
            var response = client.post(NamedRoutes.checkPath(idInBase));
            assertThat(response.code()).isEqualTo(200);

            response = client.get(NamedRoutes.urlPath(idInBase));
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                    .contains("Это мока-затычка для проверки");
// !!!!! исправления по 5 пункту - добавил проверку данных, которые добавились в базу !!!!!!!
            UrlCheck checkData = CheckRepository.findLast().get(1L);  // только 1 запись должна быть в Map
            assertEquals(checkData.getH1(), "Это мока-затычка для проверки");
            assertEquals(checkData.getTitle(), "title для моки");
            assertEquals(checkData.getDescription(), "description для моки");

        });
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
            String fixture = "http://www.testurl.com";
            String urlForAdding = "url=" + fixture;
            var response = client.post(NamedRoutes.urlsPath(), urlForAdding);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                    .contains(fixture);
            response = client.get(NamedRoutes.urlPath(1L));
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                    .contains(fixture);

//  !!!!!  исправления по 6 пункту - проверяем появилось ли добавленная ссылка в базе  !!!
            assertFalse(UrlsRepository.findByName(fixture).isEmpty());
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
