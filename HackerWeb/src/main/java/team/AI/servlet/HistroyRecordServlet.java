package team.AI.servlet;

import team.AI.bean.HistroyAct;
import team.AI.serviceIMP.UserServiceIMP;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/HistroyRecordServlet")
public class HistroyRecordServlet  extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();

        UserServiceIMP userServiceIMP=new UserServiceIMP();
        List<HistroyAct> list = userServiceIMP.Selecthistroyinfo();
        for(int i=0;i<3;i++){
            HistroyAct histroyAct =(HistroyAct) list.get(i);
            session.setAttribute("history"+i,histroyAct);
        }
    }
}
