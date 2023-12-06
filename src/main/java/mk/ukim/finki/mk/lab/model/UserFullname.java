package mk.ukim.finki.mk.lab.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserFullname implements Serializable {
    private String name;
    private String surname;
}
