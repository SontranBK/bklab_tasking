package com.frsarker.todotask;

import java.util.ArrayList;

public class Task_Company {
    String NameTask;
    String Status = "In Progress";
    String Member;
    String Description;
    String TimeBegin,TimeEnd;
    String Id;
    public String getNameTask(){
        return NameTask;
    }
    public String getStatus(){
        return Status;
    }
    public String getMember(){
        return Member;
    }
    public String getDescription(){
        return Description;
    }
    public String getTimeBegin(){
        return TimeBegin;
    }
    public String getTimeEnd(){
        return TimeEnd;
    }
    public String getId(){
        return  Id;
    }
}
