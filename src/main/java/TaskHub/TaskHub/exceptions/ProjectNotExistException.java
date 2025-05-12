package TaskHub.TaskHub.exceptions;

public class ProjectNotExistException extends RuntimeException {
    public ProjectNotExistException(String message) {
        super(message);
    }

  public ProjectNotExistException(String message, Throwable cause) {
    super(message, cause);
  }
}
