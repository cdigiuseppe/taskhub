package TaskHub.TaskHub.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter

public class TaskDto extends Dto {
    private int id;
    private String state;
    private String description;
    private Timestamp dateAdded;

    public TaskDto(int id, String state, String description, Timestamp dateAdded) {
        this.id = id;
        this.state = state;
        this.description = description;
        this.dateAdded = dateAdded;
    }
}
