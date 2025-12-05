package com.curd.basicProject.dto;

import com.curd.basicProject.entites.Priority;

public class TaskUpdateDTO {
    private String description;
    private Priority priority; // Enum

    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
