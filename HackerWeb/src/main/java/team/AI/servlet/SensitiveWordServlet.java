package team.AI.servlet;
import org.apache.log4j.Logger;
import team.AI.serviceIMP.SensitiveWordServiceIMP;
import team.SensitiveWord.crawler.WebsiteProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/SensitiveWord")
public class SensitiveWordServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        /*
                计算任务数和正在进行的任务数
         */
//        HttpSession session = req.getSession();
//        int sum=(int)session.getAttribute("sum");
//        int count =(int) session.getAttribute("count");
//        count++;
//        sum++;
//        session.setAttribute("count",count);
//        session.setAttribute("sum",sum);

//        String choose = req.getParameter("choose");
        String url = req.getParameter("url");
//        String content = req.getParameter("content");
        String result=null;
        int type[];

        SensitiveWordServiceIMP serviceIMP = new SensitiveWordServiceIMP();

        serviceIMP.startCrawler(url);

        resp.getWriter().print(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
