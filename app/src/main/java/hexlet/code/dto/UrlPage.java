package hexlet.code.dto;

import hexlet.code.model.Url;

import hexlet.code.model.UrlCheck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UrlPage extends BasePage {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    private Url url;
    private List<UrlCheck> urlsCheck;

    public UrlPage(Url url, List<UrlCheck> urlsCheckList ) {
        this.url = url;
        this.urlsCheck = urlsCheckList;
    }
}
