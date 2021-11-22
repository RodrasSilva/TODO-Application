package pt.agi.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.agi.todo.domain.ToDo;
import pt.agi.todo.dtos.CreateToDoRequestDto;
import pt.agi.todo.dtos.EditToDoRequestDto;
import pt.agi.todo.dtos.ToDoDto;
import pt.agi.todo.exceptions.InvalidToDoContentException;
import pt.agi.todo.exceptions.ToDoNotFoundException;
import pt.agi.todo.repositories.ToDoRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository repository;

    public ToDoDto createToDo(CreateToDoRequestDto request){
        if(request.getText() == null
                || request.getText().isEmpty()
                || request.getText().trim().isEmpty()){
            throw new InvalidToDoContentException("Content must have value");
        }

        ToDo toDo = new ToDo(request.getText(), false);
        toDo = repository.insert(toDo);
        return new ToDoDto(toDo);
    }

    public ToDoDto getToDo(String id){
        return repository.findById(id).map(ToDoDto::new)
                .orElseThrow(() -> new ToDoNotFoundException("ToDo not found"));
    }

    public List<ToDoDto> getAllToDos(){
        return repository.findAll()
                .stream().map(ToDoDto::new)
                .collect(Collectors.toList());
    }

    public ToDoDto editToDo(String id, EditToDoRequestDto editRequest){
        ToDo toDo = repository.findById(id)
                .orElseThrow(() -> new ToDoNotFoundException("ToDo not found"));
        toDo.setCompleted(editRequest.isCompleted());
        repository.save(toDo);
        return new ToDoDto(toDo);
    }

    public void removeToDo(String id){
        repository.deleteById(id);
        return;
    }
}
