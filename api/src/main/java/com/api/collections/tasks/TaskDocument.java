package com.api.collections.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;


@Document(collection = "tasks")

@Data
@AllArgsConstructor
public class TaskDocument {
    private @MongoId String id;
    private String title;
    private String description;
    private LocalDateTime startingDate;
    private LocalDateTime endingDate;
    private int priority;
    private int status;
    private String deviceId;

}
