package team.AI.serviceIMP;

import org.apache.poi.ss.formula.functions.T;
import team.AI.DaoIMP.TaskInfoIMP;
import team.AI.bean.TaskInfo;
import team.AI.service.TaskInfoService;

public class TaskInfoServiceIMP implements TaskInfoService {
    TaskInfoIMP taskInfoIMP=new TaskInfoIMP();
    /*
        将信息加入到数据库表中
    */
    public Boolean Insertinfo(TaskInfo taskInfo) {
        return taskInfoIMP.Insertinfo(taskInfo);
    }
}
