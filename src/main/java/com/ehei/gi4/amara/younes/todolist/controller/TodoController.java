package com.ehei.gi4.amara.younes.todolist.controller;

import com.ehei.gi4.amara.younes.todolist.model.Todo;
import com.ehei.gi4.amara.younes.todolist.model.User;
import com.ehei.gi4.amara.younes.todolist.service.TodoService;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;

@Controller
public class TodoController {
    private TodoService todoService;
    private HttpSession session;

    public TodoController(TodoService todoService, HttpSession session) {
        this.todoService = todoService;
        this.session = session;
    }

   /* @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }*/


    @GetMapping("/app")
    public String showTodos(ModelMap model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.put("todos", todoService.getTodosByUser(user.getLogin()));
            //model.addAttribute("todos",todoService.getTodosByUser(user.getId()));
            // model.put("todos", service.retrieveTodos(name));

            return "app";
        } else
            return "redirect/login?logout";
    }

    @GetMapping("/app/new")
    public String showAddTodoPage(ModelMap model) {
        model.addAttribute("todo", new Todo());
        return "/add-todo";
    }

    @PostMapping("/add-todo")
    public String addTodo(@ModelAttribute("todo") Todo todo) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            todo.setUser(user);
            todoService.saveTodo(todo);
            return "redirect:/app";

        }
        return "redirect:/index";
    }
    @GetMapping("/app/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        // service.deleteTodo(id);
        return "redirect:/app";
    }

    @GetMapping("/app/edit/{id}")
    public String showUpdateTodoPage(@PathVariable long id, ModelMap model) {
        Todo todo = todoService.getTodoById(id).get();
        model.put("todo", todo);
        return "update_todo";
    }

    @PostMapping("/app/{id}")
    public String updateTodo(@PathVariable Long id,@ModelAttribute("todo") Todo todo) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            todo.setUser(user);
            todoService.updateTodo(todo);
            return "redirect:/app";

        }
        return "redirect:/index";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/index";
    }


}
