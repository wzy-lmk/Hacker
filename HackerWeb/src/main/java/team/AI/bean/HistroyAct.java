package team.AI.bean;

public class HistroyAct {

    private String actname;
    private String actcontent;
    private String acttime;

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