package by.bsuir.remote.client.controller;

import by.bsuir.jee.RemoteCounter;
import by.bsuir.jee.TodoCrud;
import by.bsuir.jee.model.Todo;
import by.bsuir.remote.client.model.TodoDto;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class TodoController {

  private final TodoCrud crud;
  private final RemoteCounter counter;

  public TodoController(TodoCrud todoCrud, RemoteCounter counter) {
    this.crud = todoCrud;
    this.counter = counter;
  }

  @GetMapping
  public ResponseEntity<List<Todo>> getAllToDo() {
    List<Todo> todos = crud.read();
    return new ResponseEntity<>(todos, new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Todo> getById(@PathVariable("id") Long id) {
    Todo entity = crud.read(id);
    return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping("/count")
  public ResponseEntity<Integer> getCount() {
    return new ResponseEntity<>(counter.getCount(), new HttpHeaders(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Todo> create(@RequestBody TodoDto todo) {
    counter.increment();
    Todo updated = crud.create(new Todo(
        todo.getId(),
        todo.getHeader(),
        todo.getDescription()
    ));
    return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<Todo> update(@RequestBody TodoDto todo) {
    Todo updated = crud.update(new Todo(
        todo.getId(),
        todo.getHeader(),
        todo.getDescription()
    ));
    return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public HttpStatus deleteEmployeeById(@PathVariable("id") Long id) {
    if (crud.delete(id)) {
      counter.decrement();
      return HttpStatus.OK;
    } else {
      return HttpStatus.BAD_REQUEST;
    }
  }
}