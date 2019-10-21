package team.AI.servlet;

import team.AI.utils.TaskPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author 王智源
 */
@WebServlet("/StopTask")
public class StopTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String taskId = req.getParameter("taskId").substring(0,13);
        System.out.println(taskId+"dd");
        if (null==taskId){
            //不做处理
        }else{
            Boolean b = null;
            try {
                b = TaskPool.removeTask(taskId);
            } catch (SQLException e) {
                System.out.println("停止失败");
                resp.getWriter().write("false");
            }
            resp.getWriter().print(b.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
