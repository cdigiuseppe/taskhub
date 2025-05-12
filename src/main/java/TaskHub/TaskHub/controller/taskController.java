package TaskHub.TaskHub.controller;

import TaskHub.TaskHub.converters.TaskConverter;
import TaskHub.TaskHub.dto.TaskDto;
import TaskHub.TaskHub.request.TaskRequest;
import TaskHub.TaskHub.model.Project;
import TaskHub.TaskHub.model.Task;
import TaskHub.TaskHub.repository.ProjectRepository;
import TaskHub.TaskHub.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class taskController {

    private final TaskService taskService;
    private final ProjectRepository projectRepository;

    public taskController(TaskService taskService, ProjectRepository projectRepository) {
        this.taskService = taskService;
        this.projectRepository = projectRepository;
    }

    @PostMapping("/projectdId/{projectId}")
    public ResponseEntity<TaskDto> createNewTask(@PathVariable int projectId, @RequestBody @Valid TaskRequest request){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        Task createNewTask = taskService.createNewTask(request);
        createNewTask.setProject(project);

        return ResponseEntity.status(HttpStatus.CREATED).body(new TaskConverter().convertiDaEntity(createNewTask));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<List<TaskDto>>findAllTaskByProjectId(@PathVariable int projectId){
        List<TaskDto> tasks = taskService.findAllTaskByProjectId(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @PatchMapping("/updateStateTask/{taskId}/state/{state}")
    public ResponseEntity<Task> updateTask(@PathVariable int taskId,@PathVariable String state){
        Task updateTask = taskService.changeStateTask(taskId,state);
        return new ResponseEntity<>(updateTask, HttpStatus.OK);
    }

    @PostMapping("/task/{taskId}/users/{userId}")
    public ResponseEntity<String> assignUserToTask(@PathVariable int taskId, @PathVariable int userId){
        taskService.assignUserToTask(taskId, userId);
            return ResponseEntity.status(HttpStatus.OK).body("User assigned to task");
    }
}
