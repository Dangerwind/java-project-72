package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Url {

    private int id;
    private String name;
    private LocalDateTime createdAt;

    public Url (String name, LocalDateTime createdAt) {
            this.name = name;
            this.createdAt = createdAt;
    }


}
