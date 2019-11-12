package com.example.skittles70;

import java.lang.reflect.Array;
import java.util.LinkedList;

public class Project {



    String projectName;
    String productOwner;
    String projectDescription;


    String startDate;
    String endDate;

    LinkedList<String>userStories=new LinkedList<>();
    LinkedList<Sprint>sprint=new LinkedList<>();

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProductOwner() {
        return productOwner;
    }

    public void setProductOwner(String productOwner) {
        this.productOwner = productOwner;
    }

    public LinkedList<String> getUserStories() {
        return userStories;
    }

    public void adduserStory(String userStory) {
        this.userStories.add(userStory);
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
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

    @Override
    public boolean equals(Object o){
        if(this==o)
            return  true;

        else{
            Project p=(Project)o;

            if(this.projectName.equals(p.projectName)){
                return  true;
            }

            return  false;
        }
    }

}
