package team.AI.DaoIMP;

import org.apache.commons.dbutils.QueryRunner;
import team.AI.Dao.TaskInfoDao;
import team.AI.bean.TaskInfo;
import team.AI.utils.DBUtiles;

import java.sql.SQLException;

public class TaskInfoIMP implements TaskInfoDao {
    /*
        将信息加入到数据库表中
    */
    public Boolean Insertinfo(TaskInfo taskInfo) {
        QueryRunner runner=new QueryRunner(DBUtiles.getDataSource());
        String sql="insert tasks (taskid,type,startTime,email,runNumber,isrun,taskurl)values(?,?,?,?,?,?,?)";
        Object objects[]={taskInfo.getTaskid(),taskInfo.getType(),taskInfo.getStarttime(),taskInfo.getEmail(),taskInfo.getRunNumber(),taskInfo.isIsrun(),taskInfo.getTaskurl()};
        try {
            int update=0;
            update = runner.update(sql, objects);
            if(update!=0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
