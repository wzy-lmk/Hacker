package team.AI.utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 王智源
 */
public class TaskPool {

    private static Map<String, ScheduledFuture> taskpool = new HashMap<>();
    private static ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(2,new BasicThreadFactory.Builder().namingPattern("task-pool-%d").daemon(true).build());
    private static QueryRunner queryRunner  = new QueryRunner(DBUtiles.getDataSource());
    /**
     *添加任务到线程池中
     * @param taskId 任务id,唯一标识
     * @param task 任务进程
     * @param timeInterval  任务进行时间间隔
     * @return
     */
    public static void  addTask(String taskId,Runnable task,int timeInterval){
        taskpool.put(taskId,poolExecutor.scheduleWithFixedDelay(task,0, timeInterval, TimeUnit.HOURS));
    }

    /**
     * 停止并删除任务线程
     * @param taskId 任务id
     */
    public static boolean removeTask(String taskId) throws SQLException {
        ScheduledFuture future = taskpool.get(taskId);
        if (null!=future){
            System.out.println("taskid:"+taskId+" stop runing");
            boolean cancel = taskpool.get(taskId).cancel(true);
            taskpool.remove(taskId);
            queryRunner.update("delete from tasks where taskid=?",taskId);
            return cancel;
        }
        return false;
    }

}
