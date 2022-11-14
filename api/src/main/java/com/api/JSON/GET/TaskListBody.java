package com.api.JSON.GET;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TaskListBody {
    private Date date;
    private Integer status;

    public TaskListBody(Date date){
        this.date = date;
    }
}
