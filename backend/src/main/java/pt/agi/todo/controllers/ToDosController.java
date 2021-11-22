package pt.agi.todo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pt.agi.todo.dtos.*;
import pt.agi.todo.services.ToDoService;
import java.util.List;

@RestController
public class ToDosController {
    private ToDoService toDoService;

    ToDosController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/toDos")
    public ToDoDto createToDo (@RequestBody CreateToDoRequestDto createToDoRequest){
        return toDoService.createToDo(createToDoRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/toDos/{id}")
    public ToDoDto getToDo(@PathVariable String id){
        return toDoService.getToDo(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/toDos")
    public List<ToDoDto> getAllToDos(){
        return toDoService.getAllToDos();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/toDos/{id}")
    public ToDoDto editToDo(@PathVariable String id, @RequestBody EditToDoRequestDto editToDoRequest){
        return toDoService.editToDo(id, editToDoRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/toDos/{id}")
    public void deleteToDo(@PathVariable String id){
        toDoService.removeToDo(id);
    }
}
