package com.api.services;

import com.api.JSON.GET.TaskListBody;
import com.api.collections.tasks.TaskDocument;
import com.api.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public List<TaskDocument> getTasks(TaskListBody taskListBody){
        if(taskListBody.getStatus() != null){
            return taskRepo.findAllByDateAndStatus(
                    taskListBody.getDate(),
                    new Date(taskListBody.getDate().getTime() + (1000 * 60 * 60 * 24)),
                    taskListBody.getStatus()
            );
        }
        else{
            return taskRepo.findAllByDate(
                    taskListBody.getDate(),
                    new Date(taskListBody.getDate().getTime() + (1000 * 60 * 60 * 24))
            );
        }


    }

}
