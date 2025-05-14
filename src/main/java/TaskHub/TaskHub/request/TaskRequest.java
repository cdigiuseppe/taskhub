package TaskHub.TaskHub.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class TaskRequest {

    @NotNull
    @Positive
    private Long idProject;

    @NotNull
    @NotEmpty
    private String state;

    @NotNull
    @NotEmpty
    private String description;
}
