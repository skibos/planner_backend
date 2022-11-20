package com.api.services;

import com.api.JSON.GetTaskListBody;
import com.api.JSON.AddTaskBody;
import com.api.JSON.PatchTaskBody;
import com.api.collections.tasks.TaskDocument;
import com.api.repositories.TaskRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public List<TaskDocument> getTasks(GetTaskListBody getTaskListBody){
        if(getTaskListBody.getStatus() != null){
            return taskRepo.findAllByDateAndStatus(
                    LocalDateTime.of(getTaskListBody.getDate(), LocalTime.MIN),
                    LocalDateTime.of(getTaskListBody.getDate(), LocalTime.MAX),
                    getTaskListBody.getStatus()
            );
        }
        else{
            return taskRepo.findAllByDate(
                    LocalDateTime.of(getTaskListBody.getDate(), LocalTime.MIN),
                    LocalDateTime.of(getTaskListBody.getDate(), LocalTime.MAX)
            );
        }
    }

    public void addTask(AddTaskBody addTaskBody){
        TaskDocument newDocument = new TaskDocument(
                ObjectId.get().toString(),
                addTaskBody.getTitle(),
                addTaskBody.getDescription(),
                addTaskBody.getStartingDate(),
                addTaskBody.getEndingDate(),
                addTaskBody.getPriority(),
                0
        );

        taskRepo.insert(newDocument);
    }

    public void deleteAllTasks(){
        taskRepo.deleteAll();
    }

    public void deleteSingleTask(String id){
        if(taskRepo.existsById(id)) {
            taskRepo.deleteById(id);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
    }

    public void updateTask(PatchTaskBody patchTaskBody){

        if(taskRepo.findById(patchTaskBody.getId()).isPresent()) {
            TaskDocument updateDocument = taskRepo.findById(patchTaskBody.getId()).get();
            if(patchTaskBody.getTitle() != null) updateDocument.setTitle(patchTaskBody.getTitle());
            if(patchTaskBody.getDescription() != null) updateDocument.setDescription(patchTaskBody.getDescription());
            if(patchTaskBody.getStartingDate() != null) updateDocument.setStartingDate(patchTaskBody.getStartingDate());
            if(patchTaskBody.getEndingDate() != null) updateDocument.setEndingDate(patchTaskBody.getEndingDate());
            if(patchTaskBody.getPriority() != null) updateDocument.setPriority(patchTaskBody.getPriority());
            if(patchTaskBody.getStatus() != null) updateDocument.setStatus(patchTaskBody.getStatus());

            taskRepo.save(updateDocument);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
    }

}
