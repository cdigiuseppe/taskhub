package TaskHub.TaskHub.exceptions;

public class TaskNotExistException extends RuntimeException {
    public TaskNotExistException(String message) {
        super(message);
    }

  public TaskNotExistException(String message, Throwable cause) {
    super(message, cause);
  }
}
