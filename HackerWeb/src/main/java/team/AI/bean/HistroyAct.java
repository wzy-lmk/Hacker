package team.AI.bean;

public class HistroyAct {

    private String user;
    private String actname;
    private String actcontent;
    private String acttime;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getActname() {
        return actname;
    }

    public void setActname(String actname) {
        this.actname = actname;
    }

    public String getActcontent() {
        return actcontent;
    }

    public void setActcontent(String actcontent) {
        this.actcontent = actcontent;
    }

    public String getActtime() {
        return acttime;
    }

    public void setActtime(String acttime) {
        this.acttime = acttime;
    }

    @Override
    public String toString() {
        return "HistroyAct{" +
                "actname='" + actname + '\'' +
                ", actcontent='" + actcontent + '\'' +
                ", acttime='" + acttime + '\'' +
                '}';
    }

}
