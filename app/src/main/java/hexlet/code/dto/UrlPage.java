package hexlet.code.dto;

import hexlet.code.model.Url;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UrlPage {
    private Url url;
}
