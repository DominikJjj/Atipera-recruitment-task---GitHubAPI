package atipera.task.githubrepositorieslisting.dto;

import org.springframework.http.HttpStatus;

public class ErrorNotification {
    private int status;
    private String message;

    public ErrorNotification(int status, String message){
        this.status = status;
        this.message = message;
    }

    //Getters
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    //Setters
    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
