package hexlet.code.dto;

import hexlet.code.model.Url;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UrlsPage extends BasePage {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private List<Url> urls;

    public UrlsPage(List<Url> urls) {
        this.urls = urls;
    }

}
