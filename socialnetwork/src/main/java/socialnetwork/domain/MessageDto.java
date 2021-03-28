package socialnetwork.domain;

import java.time.LocalDateTime;
import java.util.List;

public class MessageDto {
    private String from;
    private String message;
    private LocalDateTime date;

    public MessageDto(String from, String message, LocalDateTime date) {
        this.from = from;
        this.message = message;
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "from='" + from + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
