package TaskHub.TaskHub.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
public class ProjectDto {
    private int id;
    private String name;
    private Timestamp dateAdded;
    private List<UserDto> users;

    public ProjectDto(int id, String name, Timestamp dateAdded) {
        this.id = id;
        this.name = name;
        this.dateAdded = dateAdded;
    }
}
