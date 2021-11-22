package pt.agi.todo.dtos;

import java.io.Serializable;

public class CreateToDoRequestDto implements Serializable {
    private String text;

    public CreateToDoRequestDto(){ }

    public String getText(){ return text; }
    public void setText(String text){ this.text = text;}
}
