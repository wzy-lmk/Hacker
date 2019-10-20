package team.AI.servlet;
import team.AI.bean.UserBean;
import team.AI.serviceIMP.SensitiveWordServiceIMP;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 王智源
 */
@WebServlet("/SensitiveWord")
public class SensitiveWordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取用户信息
        UserBean userinfo = (UserBean) req.getSession().getAttribute("userinfo");
        String url = req.getParameter("url");

        String result=null;
        SensitiveWordServiceIMP serviceIMP = new SensitiveWordServiceIMP();
        //记录任务
        serviceIMP.RecordingTask(url,userinfo,"敏感词查询");
        //启动爬虫
        serviceIMP.startCrawler(url);


        resp.getWriter().print(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
