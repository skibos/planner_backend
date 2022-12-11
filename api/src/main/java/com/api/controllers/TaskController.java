package com.api.controllers;

import com.api.JSON.AddTaskBody;
import com.api.JSON.PatchTaskBody;
import com.api.collections.tasks.TaskDocument;
import com.api.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/getTasks")
    public List<TaskDocument> getTasks(
            @RequestHeader String deviceId,
            @RequestHeader String date,
            @RequestHeader(required=false) String status
            ){
        return taskService.getTasks(deviceId, date, status);
    }

    @PostMapping("/addTask")
    public void addTask(@RequestBody AddTaskBody addTaskBody, @RequestHeader String deviceId){
        taskService.addTask(addTaskBody, deviceId);
    }

    @DeleteMapping("/deleteAllTasks")
    public void deleteAllTasks(@RequestHeader String deviceId){
        taskService.deleteAllTasks(deviceId);
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
