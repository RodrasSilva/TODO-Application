package pt.agi.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import pt.agi.todo.repositories.ToDoRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = ToDoRepository.class)
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

}
