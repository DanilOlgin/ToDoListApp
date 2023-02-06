package com.tdlp.todolistproject.controller;

import com.tdlp.todolistproject.model.ToDoItem;
import com.tdlp.todolistproject.service.ToDoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.util.Optional;

@Controller
public class ToDoFormController {

    @Autowired
    private ToDoItemService toDoItemService;

    @GetMapping("/create-todo")
    public String showCreateForm(ToDoItem todoItem) {
        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid ToDoItem toDoItem, BindingResult result, Model model) {

        ToDoItem item = new ToDoItem();
        item.setDescription(toDoItem.getDescription());
        item.setIsComplete(toDoItem.getIsComplete());

        toDoItemService.save(item);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteToDoItem(@PathVariable("id") Long id, Model model) {
        ToDoItem toDoItem = toDoItemService.getById(id)
                .orElseThrow(()->new IllegalArgumentException("id " + id + " not found"));
        toDoItemService.delete(toDoItem);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        ToDoItem toDoItem = toDoItemService.getById(id)
                .orElseThrow(()->new IllegalArgumentException("id " + id + " not found"));
        model.addAttribute("todo", toDoItem);
        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateToDoItem(@PathVariable("id") Long id, @Valid ToDoItem toDoItem, BindingResult bindingResult, Model model) {
        ToDoItem item = toDoItemService.getById(id)
                .orElseThrow(()->new IllegalArgumentException("id " + id + " not found"));
        item.setIsComplete(toDoItem.getIsComplete());
        item.setDescription(toDoItem.getDescription());

        toDoItemService.save(item);
        return "redirect:/";
    }
}
