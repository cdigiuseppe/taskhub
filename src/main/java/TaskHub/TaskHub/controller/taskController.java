package TaskHub.TaskHub.controller;

import TaskHub.TaskHub.model.Project;
import TaskHub.TaskHub.model.Task;
import TaskHub.TaskHub.repository.ProjectRepository;
import TaskHub.TaskHub.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class taskController {

    @Autowired
    TaskService taskService;

    //come utilizzare il findById senza dover richiamare la repository in un controller
    @Autowired
    ProjectRepository projectRepository;

    @PostMapping("/createNewTask/projects/{projectId}")
    public ResponseEntity<Task> createNewTask(@PathVariable int projectId,@RequestBody Task task){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        task.setProject(project);

        Task createNewTask = taskService.createNewTask(task);
        return new ResponseEntity<Task>(createNewTask, HttpStatus.CREATED);
    }

    @GetMapping("/allTaskByProjectId/{projectId}")
    public ResponseEntity<List<Task>>findAllTaskByProjectId(@PathVariable int projectId){
        List<Task> tasks = taskService.findAllTaskByProjectId(projectId);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PatchMapping("/updateStateTask/{taskId}/state/{state}")
    public ResponseEntity<Task> updateTask(@PathVariable int taskId,@PathVariable String state){
        if (state.equals("A") || state.equals("C") || state.equals("I")){
            Task updateTask = taskService.changeStateTask(taskId,state);
            return new ResponseEntity<>(updateTask, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/task/{taskId}/users/{userId}")
    public ResponseEntity<String> assignUserToTask(@PathVariable int taskId, @PathVariable int userId){
        if (taskService.assignUserToTask(taskId, userId)){
            return ResponseEntity.ok("Task assigned");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task or User not found");
        }
    }
}
