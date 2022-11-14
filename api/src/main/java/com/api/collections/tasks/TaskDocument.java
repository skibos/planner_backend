package com.api.collections.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document(collection = "tasks")

@Data
@AllArgsConstructor
public class TaskDocument {
    private @MongoId String _id;
    private String title;
    private String description;
    private Date startingDate;
    private Date endingDate;
    private int priority;
    private int status;

}
