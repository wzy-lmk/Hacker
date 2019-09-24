package team.AI.servlet;
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

    SensitiveWordServiceIMP serviceIMP = new SensitiveWordServiceIMP();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        /*
                计算任务数和正在进行的任务数
         */
        HttpSession session = req.getSession();
        int sum=(int)session.getAttribute("sum");
        int count =(int) session.getAttribute("count");
        count++;
        sum++;
        session.setAttribute("count",count);
        session.setAttribute("sum",sum);

        String choose = req.getParameter("choose");
        String url = req.getParameter("url");
        String content = req.getParameter("content");
        System.out.println("url---"+url+"choose---"+choose);
        String result=null;
        int type[];

        if (null!=choose&&choose.trim()!=""){
            String[] split = choose.split(",");

            if (content!=null){
                type = new int[split.length+1];
            }else{
                type = new int[split.length];
            }

            for (int i = 0; i < split.length; i++) {
                type[i]=Integer.parseInt(split[i]);
            }
            //启动爬虫
            result = serviceIMP.startCrawler(url, type);

        }else {
            result=serviceIMP.startCrawler(url,0,1,2,3,4);
        }

        resp.getWriter().print(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
