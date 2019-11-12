package com.example.skittles70;

import java.lang.reflect.Array;
import java.util.LinkedList;

public class Sprint extends Project {


    int sprintNumber;
    String startDate;
    String endDate;
    LinkedList<String>tasks=new LinkedList<>();

    public int getSprintNumber() {
        return sprintNumber;
    }

    public void setSprintNumber(int sprintNumber) {
        this.sprintNumber = sprintNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public LinkedList<String> getTasks() {
        return tasks;
    }

    public void addTask(String task) {
        this.tasks .add(task);
    }


}
