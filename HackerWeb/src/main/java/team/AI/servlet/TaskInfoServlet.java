package team.AI.servlet;

import com.alibaba.fastjson.JSONObject;
import team.AI.DaoIMP.TaskInfoIMP;
import team.AI.bean.TaskInfo;
import team.AI.bean.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 王智源
 */
@WebServlet("/TaskInfo")
public class TaskInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String type = req.getParameter("type");
        UserBean userinfo = (UserBean) req.getSession().getAttribute("userinfo");
        TaskInfoIMP infoIMP = new TaskInfoIMP();
        List<TaskInfo> taskInfo = infoIMP.getTaskInfo(type,userinfo.getEmail());
        resp.getWriter().print(JSONObject.toJSONString(taskInfo));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
