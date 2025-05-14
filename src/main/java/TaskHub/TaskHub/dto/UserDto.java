package TaskHub.TaskHub.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UserDto extends Dto {
    private int id;
    private String name;
    private String surname;
    private String CF;
    private Timestamp dateAdded;

    public UserDto(int id, String name, String surname, String CF, Timestamp dateAdded) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.CF = CF;
        this.dateAdded = dateAdded;
    }
}
