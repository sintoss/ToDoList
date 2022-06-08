package com.ehei.gi4.amara.younes.todolist.service;

import com.ehei.gi4.amara.younes.todolist.model.Todo;
import com.ehei.gi4.amara.younes.todolist.model.User;
import com.ehei.gi4.amara.younes.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    public List<Todo> getTodosByUser(String login) {
        return todoRepository.findByUserLogin(login);
    }


    public Optional<Todo> getTodoById(long id) {
        return todoRepository.findById(id);
    }


    public void updateTodo(Todo todo) {
        todoRepository.save(todo);
    }


    public void addTodo(String desc, LocalDateTime targetDate, User user) {
        todoRepository.save(new Todo(desc, targetDate, user));
    }


    public void deleteTodo(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            todoRepository.delete(todo.get());
        }
    }


    public void saveTodo(Todo todo) {
        todoRepository.save(todo);
    }
}
