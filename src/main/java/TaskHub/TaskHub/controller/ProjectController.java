package TaskHub.TaskHub.controller;

import TaskHub.TaskHub.converters.ProjectConverter;
import TaskHub.TaskHub.dto.ProjectDto;
import TaskHub.TaskHub.request.ProjectRequest;
import TaskHub.TaskHub.model.Project;
import TaskHub.TaskHub.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping()
    public ResponseEntity<ProjectDto> createNewProject(@RequestBody @Valid ProjectRequest request) {
        Project project = projectService.createNewProject(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProjectConverter().convertiDaEntity(project));
    }

    @GetMapping()
    public List<ProjectDto> getProjects(){
        return projectService.getAllProjects();
    }

    @PostMapping("/{projectId}/users/{userId}")
    public ResponseEntity<String> assignUserToProject(@PathVariable int projectId, @PathVariable int userId){
        projectService.assignUserToProject(projectId, userId);
            return ResponseEntity.status(HttpStatus.OK).body("User assigned");
    }
}
