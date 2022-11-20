package com.api.JSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTaskListBody {
    private LocalDate date;
    private Integer status;

    public GetTaskListBody(LocalDate date){
        this.date = date;
    }
}
