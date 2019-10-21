package team.AI.Dao;

import team.AI.bean.TaskInfo;

import java.util.List;

public interface TaskInfoDao {
    /*
        将信息加入到数据库表中
    */
    public abstract Boolean Insertinfo(TaskInfo taskInfo);

    /**
     * 获取任务信息
     * @param type
     * @return
     */
    public abstract List<TaskInfo> getTaskInfo(String type,String email);
}
