package team.AI.servlet;

import com.google.gson.Gson;
import team.AI.bean.HistroyAct;
import team.AI.bean.JsonBean;
import team.AI.bean.UserBean;
import team.AI.serviceIMP.UserServiceIMP;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    /*
        登陆
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserBean userBean =new UserBean();
        userBean.setPhone(username);
        userBean.setEmail(username);
        userBean.setPassword(password);


        Gson gson=new Gson();
        JsonBean jsonBean=new JsonBean();
        if(!username.equals("")&&!password.equals("")){
            UserServiceIMP loginServiceIMP=new UserServiceIMP();
            UserBean bean = loginServiceIMP.login(userBean);
            if(bean!=null){
                int sum=0;
                int count=0;
                HttpSession session = request.getSession();

                /*
                    历史记录
                 */
                UserServiceIMP userServiceIMP=new UserServiceIMP();
                List<HistroyAct> list = userServiceIMP.Selecthistroyinfo();
                for(int i=0;i<3;i++){
                    HistroyAct histroyAct =(HistroyAct) list.get(i);
                    session.setAttribute("histroy"+i,histroyAct);
                }
                //用户个人信息
                session.setAttribute("userinfo",bean);
                //总任务数
                session.setAttribute("sum",sum);
                //任务进行的个数
                session.setAttribute("count",count);
            }else{
                jsonBean.setFail2("账号或者密码错误");
            }
        }else{
            jsonBean.setFail1("账号密码不能为空");

        }
        String json = gson.toJson(jsonBean);
        response.getWriter().println(json);
    }
}
