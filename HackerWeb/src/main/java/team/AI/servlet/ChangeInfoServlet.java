package team.AI.servlet;

import team.AI.bean.HistroyAct;
import team.AI.bean.UserBean;
import team.AI.serviceIMP.UserServiceIMP;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/ChangeInfoServlet")
public class ChangeInfoServlet extends HttpServlet {
    /*
        修改个人信息
    */
    protected void service(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String change="";
        HttpSession session = request.getSession();
        UserBean bean=(UserBean)session.getAttribute("userinfo");
        String NewPhone=bean.getPhone();
        String username = request.getParameter("username");
        String password=request.getParameter("password");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        if(!username.equals("")&&!password.equals("")&&!email.equals("")&&!tel.equals("")){
            UserBean userBean=new UserBean();
            userBean.setName(username);
            userBean.setPassword(password);
            userBean.setEmail(email);
            userBean.setPhone(tel);
            userBean.setNewPhone(NewPhone);
            UserServiceIMP userServiceIMP=new UserServiceIMP();
            int i = userServiceIMP.ChangeSelfinfo(userBean);
            HistroyAct histroyAct=new HistroyAct();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = String.valueOf(df.format(new Date()));
            if(i!=0){
                histroyAct.setActname("修改个人信息");
                if(!bean.getName().equals(username)){
                    change=change+bean.getName()+" to "+"username"+"  ";
                }
                if(!tel.equals(NewPhone)){
                    change=change+NewPhone+" to "+tel+"  ";
                }
                if(!bean.getEmail().equals(email)){
                    change=change+email+" to "+bean.getEmail()+"  ";
                }
                if(!bean.getPassword().equals(password)){
                    change=change+bean.getPassword()+" to "+password+"  ";
                }
                histroyAct.setActcontent("成功修改"+change);
                histroyAct.setActtime(time);
                int update = userServiceIMP.InsertHistroyinfo(histroyAct);
                if(update!=0){
                    System.out.println("修改成功");
                }else{
                    System.out.println("修改失败");
                }
            }else{
                histroyAct.setActname("修改个人信息");
                histroyAct.setActcontent("修改失败");
                histroyAct.setActtime(time);
                int update = userServiceIMP.InsertHistroyinfo(histroyAct);
                if(update!=0){
                    System.out.println("修改成功");
                }else{
                    System.out.println("修改失败");
                }
            }
        }else{
            System.out.println("信息不能为空");
        }
    }
}
