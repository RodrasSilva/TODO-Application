package pt.agi.todo.dtos;

public class EditToDoRequestDto {
    private boolean isCompleted;

    public EditToDoRequestDto(){ }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
