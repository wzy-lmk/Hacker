package team.AI.servlet;

import team.AI.bean.UserBean;
import team.AI.serviceIMP.ResultPageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 王智源
 */
@WebServlet("/ResultPage")
public class ResultPageServlet extends HttpServlet {
    private String senitiveWord ="敏感词查询";
    String monitor="monitor";
    String img="img";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        ResultPageServiceImpl service = new ResultPageServiceImpl();
        String type = req.getParameter("type");
        UserBean user = (UserBean) req.getSession().getAttribute("userinfo");
        String email = user.getEmail();

        String res = "无结果";

        //敏感词查询
        if (senitiveWord.equals(type)){
            res = service.getPageHtml(email,senitiveWord);
        }else if (monitor.equals(type)){
            res = service.getPageHtml(email,monitor);
        }else if(img.equals(type)){
            res = service.getPageHtml(email,img);
        }else{
            res = "错误";
        }
        resp.getWriter().print(res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
