package com.api.controllers;

import com.api.JSON.GetTaskListBody;
import com.api.JSON.AddTaskBody;
import com.api.JSON.PatchTaskBody;
import com.api.collections.tasks.TaskDocument;
import com.api.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/getTasks")
    public List<TaskDocument> getTasks(@RequestBody GetTaskListBody getTaskListBody){
        return taskService.getTasks(getTaskListBody);
    }

    @PostMapping("/addTask")
    public void addTask(@RequestBody AddTaskBody addTaskBody){
        taskService.addTask(addTaskBody);
    }

    @DeleteMapping("/deleteAllTasks")
    public void deleteAllTasks(){
        taskService.deleteAllTasks();
    }

    @DeleteMapping("/deleteSingleTask")
    public void deleteSingleTask(@RequestParam String id){
        taskService.deleteSingleTask(id);
    }

    @PatchMapping("/updateTask")
    public void updateTask(@RequestBody PatchTaskBody patchTaskBody){
        taskService.updateTask(patchTaskBody);
    }

}
