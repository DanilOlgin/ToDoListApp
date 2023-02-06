package com.tdlp.todolistproject.controller;

import com.tdlp.todolistproject.service.ToDoItemService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private ToDoItemService todoItemService;

    @GetMapping(value = "/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("toDoItems", todoItemService.getAll());
        return modelAndView;
    }
}