package TaskHub.TaskHub.service;

import TaskHub.TaskHub.model.Task;
import TaskHub.TaskHub.model.User;
import TaskHub.TaskHub.repository.TaskRepository;
import TaskHub.TaskHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findAllTaskByProjectId(int projectId){
        return taskRepository.findByProjectId(projectId);
    }

    public Task changeStateTask(int taskId, String state){
        Task existingTask = null;
        Optional<Task> checkTask = taskRepository.findById(taskId);
        if (checkTask.isPresent()){
            existingTask = checkTask.get();
            existingTask.setState(state);
        }
        return taskRepository.save(existingTask);
    }

    public boolean assignUserToTask(int taskId, int userId){
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalTask.isPresent() && optionalUser.isPresent()){
            Task task = optionalTask.get();
            User user = optionalUser.get();
            task.getUsers().add(user);
            System.out.println("User tasks: " + user.getTasks());
            System.out.println("Before saving, task users: " + task.getUsers());
            taskRepository.save(task);
            return true;
        }
        return false;
    }
}
