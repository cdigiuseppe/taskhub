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
public class TaskController {

    private final TaskService taskService;
    private final ProjectRepository projectRepository;

    public TaskController(TaskService taskService, ProjectRepository projectRepository) {
        this.taskService = taskService;
        this.projectRepository = projectRepository;
    }


    // TODO:Stai creando un task, lascia solo @Post, il mapping è già specificato, se ti serve il projectId allora passalo nell'oggetto di request TaskRequest
    @PostMapping
    public ResponseEntity<TaskDto> createNewTask( @RequestBody @Valid TaskRequest request){
        Project project = projectRepository.findById(request.getIdProject().intValue())
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

    //TODO: Non serve /task , lascia solo /{taskId}/users/{userId}
    @PostMapping("/{taskId}/users/{userId}")
    public ResponseEntity<String> assignUserToTask(@PathVariable int taskId, @PathVariable int userId){
        taskService.assignUserToTask(taskId, userId);
            return ResponseEntity.status(HttpStatus.OK).body("User assigned to task");
    }
}
