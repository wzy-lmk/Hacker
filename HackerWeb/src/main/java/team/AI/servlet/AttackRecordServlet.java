package team.AI.servlet;

import com.alibaba.fastjson.JSONObject;
import team.AI.DaoIMP.AttackIMP;
import team.AI.bean.HistroyAct;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * 显示反共黑客网上的信息
 */
@WebServlet("/AttackRecord")
public class AttackRecordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        /*
                计算任务数和正在进行的任务数
         */
        Date date=new Date();
        HttpSession session = req.getSession();
        int sum=(int)session.getAttribute("sum");
        int count =(int) session.getAttribute("count");
        count++;
        sum++;
        session.setAttribute("sum",sum);
        session.setAttribute("count",count);
//        HistroyAct histroyAct=(HistroyAct)session.getAttribute("histroyAct");
//        histroyAct.setActname("黑客攻击记录");
//        String s = String.valueOf(date.getTime());
//        histroyAct.setActname((s));




        AttackIMP attackIMP = new AttackIMP();
//        ArrayList<InfoBean> allInfo = attackIMP.getAllInfo();
        ArrayList<Object[]> allInfoss = attackIMP.getAllInfoss();
        if(allInfoss==null){
            resp.getWriter().print("fail");
        }else {
            String result = JSONObject.toJSONString(allInfoss);
            System.out.println(result);
            resp.getWriter().print(result);
        }
    }
    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
