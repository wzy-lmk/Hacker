package team.AI.Dao;

import team.AI.bean.TaskInfo;

public interface TaskInfoDao {
    /*
        将信息加入到数据库表中
    */
    public abstract Boolean Insertinfo(TaskInfo taskInfo);
}
