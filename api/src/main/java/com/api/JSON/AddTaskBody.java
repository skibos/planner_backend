package com.api.JSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.TimeZone;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTaskBody {
     private String title;
     private String description;
     private Integer priority;
     private LocalDateTime startingDate;
     private LocalDateTime endingDate;
}
