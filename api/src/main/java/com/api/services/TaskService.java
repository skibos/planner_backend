package com.api.services;

import com.api.JSON.AddTaskBody;
import com.api.JSON.PatchTaskBody;
import com.api.collections.tasks.TaskDocument;
import com.api.repositories.TaskRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public List<TaskDocument> getTasks(String deviceId, String date, String status){

        LocalDate convertedDate = LocalDate.parse(date);

        if(status != null){
            int convertedStatus = Integer.parseInt(String.valueOf(status));
            return taskRepo.findAllByDateAndStatus(
                    LocalDateTime.of(convertedDate, LocalTime.MIN),
                    LocalDateTime.of(convertedDate, LocalTime.MAX),
                    convertedStatus,
                    deviceId
            );
        }
        else{
            return taskRepo.findAllByDate(
                    LocalDateTime.of(convertedDate, LocalTime.MIN),
                    LocalDateTime.of(convertedDate, LocalTime.MAX),
                    deviceId
            );
        }
    }

    public TaskDocument getSingleTask(String id){
        if(taskRepo.findById(id).isPresent()){
            return taskRepo.findById(id).get();
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
    }

    public void addTask(AddTaskBody addTaskBody, String deviceId){
        TaskDocument newDocument = new TaskDocument(
                ObjectId.get().toString(),
                addTaskBody.getTitle(),
                addTaskBody.getDescription(),
                addTaskBody.getStartingDate(),
                addTaskBody.getEndingDate(),
                addTaskBody.getPriority(),
                0,
                deviceId
        );

        taskRepo.insert(newDocument);
    }

    public void deleteAllTasks(String deviceId){
        List<TaskDocument> taskListByDeviceId = taskRepo.findAllByDeviceId(deviceId);

        taskRepo.deleteAll(taskListByDeviceId);
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
