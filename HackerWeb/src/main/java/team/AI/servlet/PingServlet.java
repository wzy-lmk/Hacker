package team.AI.servlet;

import sun.applet.Main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;

@WebServlet("/Ping")
public class PingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String url = req.getParameter("url");
        if (url.startsWith("http")){
            if (url.startsWith("https")){
                url=url.substring(8,url.length());
            }else{
                url=url.substring(7,url.length());
            }
        }
        int timeout = 300;
        boolean status=false;
        boolean v6status=false;

        try {
            status = InetAddress.getByName(url).isReachable(timeout);
        }catch (Exception e){
            status=false;
        }
        if (status==false){
            try {
                v6status=Inet6Address.getByName(url).isReachable(timeout);
            }catch (Exception e){
                v6status=false;
            }
        }
        if (status||v6status){
            resp.getWriter().print(true);
        }else{
            resp.getWriter().print(status);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
