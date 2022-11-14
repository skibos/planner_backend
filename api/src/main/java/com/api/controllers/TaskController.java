package com.api.controllers;

import com.api.JSON.GET.TaskListBody;
import com.api.collections.tasks.TaskDocument;
import com.api.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/getTasks")
    public List<TaskDocument> getTasks(@RequestBody TaskListBody taskListBody){
        return taskService.getTasks(taskListBody);
    }

}
