package team.AI.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileDown extends HttpServlet {

    private String contentType;
    private String enc="UTF_8";
    private String fileroot;



    @Override
    public void init(ServletConfig config) throws ServletException {
        contentType = config.getInitParameter("contentType");
        fileroot =config.getInitParameter("fileRoot");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String filename = new Date().toString();
        String filepath = fileroot+ File.separator+filename;

        File downloadFile = new File(filepath);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
