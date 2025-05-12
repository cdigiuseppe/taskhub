package TaskHub.TaskHub.service;

import TaskHub.TaskHub.dto.TaskDto;
import TaskHub.TaskHub.request.TaskRequest;
import TaskHub.TaskHub.exceptions.TaskNotExistException;
import TaskHub.TaskHub.exceptions.UserNotExistException;
import TaskHub.TaskHub.model.Task;
import TaskHub.TaskHub.model.User;
import TaskHub.TaskHub.repository.TaskRepository;
import TaskHub.TaskHub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task createNewTask(TaskRequest request) {
        Task task = new Task();
        task.setDescription(request.getDescription());
        task.setState(request.getState());
        task.setDateAdded(new Timestamp(System.currentTimeMillis()));
        return taskRepository.save(task);
    }

    public List<TaskDto> findAllTaskByProjectId(int projectId){
        return taskRepository.findByProjectId(projectId).stream()
                .map(task -> new TaskDto(task.getId(), task.getState(), task.getDescription(), task.getDateAdded()))
                .toList();
    }

    public Task changeStateTask(int taskId, String state){
        Task existingTask = null;
        Optional<Task> checkTask = taskRepository.findById(taskId);

        if (checkTask.isEmpty()){
            throw new TaskNotExistException("Task not found");
        }

        existingTask = checkTask.get();
        existingTask.setState(state);

        if (state.equals("A") || state.equals("C") || state.equals("I")) {
            existingTask.setState(state);
        } else {
            throw new IllegalArgumentException("Invalid state: " + state);
        }

        return taskRepository.save(existingTask);
    }

    public void assignUserToTask(int taskId, int userId) throws TaskNotExistException, UserNotExistException {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalTask.isPresent() && optionalUser.isPresent()){
            Task task = optionalTask.get();
            User user = optionalUser.get();
            task.getUsers().add(user);
            taskRepository.save(task);
        }
        if (optionalTask.isEmpty()){
            throw new TaskNotExistException("Task not found");
        }
        if (optionalUser.isEmpty()){
            throw new UserNotExistException("User not found");
        }
    }
}
