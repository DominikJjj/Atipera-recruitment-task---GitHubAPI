package atipera.task.githubrepositorieslisting.middleware;

import atipera.task.githubrepositorieslisting.dto.ErrorNotification;
import atipera.task.githubrepositorieslisting.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorNotification> handleUserNotFoundException(UserNotFoundException exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorNotification(404, exception.getMessage()));
    }
}
