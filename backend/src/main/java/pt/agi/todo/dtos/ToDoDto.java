package pt.agi.todo.dtos;

import pt.agi.todo.domain.ToDo;
import java.time.LocalDateTime;

public class ToDoDto {
    private String id;
    private String text;
    private boolean isCompleted;

    public ToDoDto(){ }

    public ToDoDto(String id, String text, boolean isCompleted){
        this.id = id;
        this.text = text;
        this.isCompleted = isCompleted;
    }

    public ToDoDto(ToDo toDo) {
        this.id = toDo.getId();
        this.text = toDo.getText();
        this.isCompleted = toDo.isCompleted();
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
