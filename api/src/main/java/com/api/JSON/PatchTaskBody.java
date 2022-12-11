package com.api.JSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchTaskBody extends AddTaskBody{
    @NonNull
    private @MongoId String id;
    private String title;
    private String description;
    private Integer priority;
    private LocalDateTime startingDate;
    private LocalDateTime endingDate;
    private Integer status;
}
