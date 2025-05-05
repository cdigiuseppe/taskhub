package TaskHub.TaskHub.controller;

import TaskHub.TaskHub.model.Project;
import TaskHub.TaskHub.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping("/createNewProject")
    public ResponseEntity<?> createNewProject(@RequestBody Project project){
        if(projectService.findByName(project.getName()).isEmpty()) {
            Project createNewProject = projectService.createNewProject(project);
            return new ResponseEntity<>(createNewProject, HttpStatus.CREATED);
        }else {
            HashMap<String, String> error = new HashMap<>();
            error.put("errore", "Esiste già un progetto con questo nome");
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/allProjects")
    public List<Project> getProjects(){
        return projectService.getAllProjects();
    }

    @PostMapping("/projects/{projectId}/users/{userId}")
    public ResponseEntity<String> assignUserToProject(@PathVariable int projectId, @PathVariable int userId){
        if (projectService.assignUserToProject(projectId, userId)){
            return ResponseEntity.ok("User assigned");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project or User not found");
        }
    }

}
