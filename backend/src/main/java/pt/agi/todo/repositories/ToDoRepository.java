package pt.agi.todo.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import pt.agi.todo.domain.ToDo;

import java.util.List;
import java.util.Optional;

public interface ToDoRepository extends MongoRepository<ToDo, String>{
    List<ToDo> findAll();
    ToDo insert(ToDo toDo);
    ToDo save(ToDo toDo);
    Optional<ToDo> findById(String id);
    void deleteById(String id);
}
