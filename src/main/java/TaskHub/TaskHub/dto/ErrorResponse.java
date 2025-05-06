package TaskHub.TaskHub.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    private HttpStatus status;
    private String errorMessage;
    private String errorCode;
    private String stackTrace;

}
