package TaskHub.TaskHub.converters;

import TaskHub.TaskHub.dto.TaskDto;
import TaskHub.TaskHub.model.Task;

public class TaskConverter implements Converter<TaskDto, Task>{
    @Override
    public TaskDto convertiDaEntity(Task task) {
        if (task == null) {
            return null;
        }

        return new TaskDto(task.getId(), task.getState(), task.getDescription(), task.getDateAdded());
    }

    @Override
    public Task convertiDaDto(TaskDto taskDto) {
        if (taskDto == null) {
            return null;
        }
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setState(taskDto.getState());
        task.setDescription(taskDto.getDescription());
        task.setDateAdded(taskDto.getDateAdded());
        return task;
    }
}
