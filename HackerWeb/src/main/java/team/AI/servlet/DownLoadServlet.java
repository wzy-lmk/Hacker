package team.AI.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/DownLoad")
public class DownLoadServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String filename = req.getParameter("filename");
        //下载文件设置消息头
        //MIME类型，任意二进制文件（万能）
        resp.addHeader("contentType", "application/octet-stream");
        resp.addHeader("content-Disposition", "attachment;filename="+filename);

        InputStream inputStream = new FileInputStream(new File("D:\\IDEA-WorkSpace\\Antihacker\\"+filename));

        ServletOutputStream outputStream = resp.getOutputStream();
        byte[] bs = new byte[1024];
        int len = -1;
        while((len=inputStream.read(bs))!=-1) {
            outputStream.write(bs, 0, len);
        }
        outputStream.close();
        inputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
