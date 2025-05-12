package TaskHub.TaskHub.exceptions;

import TaskHub.TaskHub.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ProjectAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleProjectAlreadyExistsException(ProjectAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NO_CONTENT);
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setErrorCode("E100");

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }
}
