package TaskHub.TaskHub.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProjectRequest {
    @NotNull
    @NotEmpty
    private String name;
}
