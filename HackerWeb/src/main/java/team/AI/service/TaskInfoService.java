package team.AI.service;

import team.AI.bean.TaskInfo;

public interface TaskInfoService {
    /*
        将信息加入到数据库表中
    */
    public abstract Boolean Insertinfo(TaskInfo taskInfo);
}
