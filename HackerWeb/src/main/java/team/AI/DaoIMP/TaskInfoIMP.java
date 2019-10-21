package team.AI.DaoIMP;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import team.AI.Dao.TaskInfoDao;
import team.AI.bean.TaskInfo;
import team.AI.utils.DBUtiles;

import java.sql.SQLException;
import java.util.List;

public class TaskInfoIMP implements TaskInfoDao {
    /*
        将信息加入到数据库表中
    */
    @Override
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

    @Override
    public List<TaskInfo> getTaskInfo(String type,String email) {
        QueryRunner runner=new QueryRunner(DBUtiles.getDataSource());
        try {
            List<TaskInfo> res = runner.query("select id,taskid,type,startTime,email,runNumber,isrun,taskurl from tasks where type=? and isrun=true and email=?", new BeanListHandler<TaskInfo>(TaskInfo.class),new Object[]{type,email});
            for (TaskInfo re : res) {
                System.out.println(re.toString());
            }
            return  null==res?null:res;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        new TaskInfoIMP().getTaskInfo("敏感词查询","3197");
    }
}
