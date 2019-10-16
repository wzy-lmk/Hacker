package team.AI.bean;

import java.sql.Date;

public class TaskInfo {
    private int id;
    private String type;
    private Date starttime;
    private String email;
    private int runNumber;
    private boolean isrun;
    private String taskurl;

    public TaskInfo(String type, Date starttime, String email, int runNumber, boolean isrun, String taskurl) {
        this.type = type;
        this.starttime = starttime;
        this.email = email;
        this.runNumber = runNumber;
        this.isrun = isrun;
        this.taskurl = taskurl;
    }

    public TaskInfo(int id, String type, Date starttime, String email, int runNumber, boolean isrun, String taskurl) {
        this.id = id;
        this.type = type;
        this.starttime = starttime;
        this.email = email;
        this.runNumber = runNumber;
        this.isrun = isrun;
        this.taskurl = taskurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRunNumber() {
        return runNumber;
    }

    public void setRunNumber(int runNumber) {
        this.runNumber = runNumber;
    }

    public boolean isIsrun() {
        return isrun;
    }

    public void setIsrun(boolean isrun) {
        this.isrun = isrun;
    }

    public String getTaskurl() {
        return taskurl;
    }

    public void setTaskurl(String taskurl) {
        this.taskurl = taskurl;
    }
}
