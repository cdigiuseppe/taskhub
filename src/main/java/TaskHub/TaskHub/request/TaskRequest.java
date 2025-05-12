package TaskHub.TaskHub.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TaskRequest {

    @NotNull
    @NotEmpty
    private String state;

    @NotNull
    @NotEmpty
    private String description;
}
