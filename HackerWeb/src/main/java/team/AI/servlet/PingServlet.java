package team.AI.servlet;

import sun.applet.Main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet("/Ping")
public class PingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String url = req.getParameter("url");
        int timeout = 300;
        boolean status=false;
        try {
            status = InetAddress.getByName(url).isReachable(timeout);
        }catch (Exception e){
            status=false;
        }

        resp.getWriter().print(status);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
