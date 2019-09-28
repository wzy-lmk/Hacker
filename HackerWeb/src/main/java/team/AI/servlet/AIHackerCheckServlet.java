package team.AI.servlet;

import com.alibaba.fastjson.*;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import team.AI.IMG.*;
import team.AI.utils.Sample;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.*;

@WebServlet("/AIHackerCheckServlet")
public class AIHackerCheckServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String url = request.getParameter("url");
        String keyword = request.getParameter("keyword");
        ArrayList lists = new ArrayList();
        int i = 0;
        DeleteFiles deleteFiles = new DeleteFiles();
        String address = null;
        InputStream iStream = AIHackerCheckServlet.class.getClassLoader().getResourceAsStream("downloadAddr.properties");
        Properties properties = new Properties();
        try {
            properties.load(iStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        address = properties.getProperty("address");
        ThreadLocalClientFactory threadLocalClientFactory = new ThreadLocalClientFactory();
        WebClient webClient = threadLocalClientFactory.getWebClient();
        DealUrl dealUrl = new DealUrl();
        HtmlPage page = null;
        String newURL = null;
        if (!url.equals("")) {
            try {
                //获取HTML页面
                page = webClient.getPage(url);
                List<DomAttr> byXPath = page.getByXPath("//img/@src");
                deleteFiles.CreateFile(address);
                for (DomAttr domAttr : byXPath) {
                    try {
                        String dealU = dealUrl.getNetwork(url);
                        if (!dealUrl.isNet(domAttr.getValue())) {
                            newURL = dealU + dealUrl.getUrl(domAttr.getValue());
                        }
                        URL u = new URL(newURL);
                        DataInputStream dataInputStream = new DataInputStream(u.openStream());
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(address + "/img" + i + ".jpg"));
                        ByteArrayOutputStream output = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int length;

                        while ((length = dataInputStream.read(buffer)) > 0) {
                            output.write(buffer, 0, length);
                        }
                        fileOutputStream.write(output.toByteArray());
                        dataInputStream.close();
                        fileOutputStream.close();

                        Sample sample = new Sample();
                        ArrayList list = sample.PicText(address + "/img" + i + ".jpg");
                        i++;
                        Iterator iterator = list.iterator();
                        while (iterator.hasNext()) {
                            String next = (String) iterator.next();
                            if (keyword.equals("")) {
                                System.out.println(next);
                                if (next.equals("反共黑客") || next.equals("反共") || next.equals("反黑") || next.equals("反客") || next.equals("黑客") || next.equals("反黑")) {
                                    lists.add(newURL);
                                }
                            }
                            if (!keyword.equals("")) {
                                if (next.equals(keyword)) {
                                    lists.add(newURL);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                webClient.getCurrentWindow().getJobManager().removeAllJobs();
                System.gc();
                webClient.close();
            }
            File file = new File(address);
            deleteFiles.delete(file);
            if (lists.isEmpty()) {
                response.getWriter().print("none");
            }
            String resultJson = JSONObject.toJSONString(lists);
            response.getWriter().print(resultJson);
        } else {
            System.out.println("输入错误");

        }

    }
}
