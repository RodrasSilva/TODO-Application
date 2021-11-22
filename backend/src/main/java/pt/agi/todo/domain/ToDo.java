package pt.agi.todo.domain;

import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

public class ToDo {
    @Id
    private String id;
    private String text;
    private boolean isCompleted;

    public ToDo(){ }

    public ToDo(String text, boolean isCompleted){
        this.text = text;
        this.isCompleted = isCompleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
