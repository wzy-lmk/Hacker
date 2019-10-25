package team.AI.service;


import team.AI.bean.ResultBean;

import java.util.List;

/**
 * @author 王智源
 */
public interface ResultPageService {

    /**
     * 获取用户创建的任务ID
     * @param email 邮箱
     * @param type 任务类型
     * @return
     */
   public List<String> getTaskIds(String email, String type);

    /**
     * 根据任务id获取任务的结果信息
     * @param taskId 任务id
     * @return
     */
   public String getResultBean(String taskId);

    /**
     * 拼接结果html
     * @param email 邮箱
     * @param type 类型
     * @return
     */
   public String getPageHtml(String email,String type);


}
