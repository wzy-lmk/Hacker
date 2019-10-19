package team.AI.utils;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

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
    public static void removeTask(String taskId){
        if (null!=taskpool.get(taskId)){
            taskpool.get(taskId).cancel(true);
            taskpool.remove(taskId);
        }
    }

}
