package team.AI.servlet;

import com.alibaba.fastjson.*;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import team.AI.IMG.*;
import team.AI.bean.HistroyAct;
import team.AI.bean.TaskInfo;
import team.AI.bean.UserBean;
import team.AI.serviceIMP.AIHeckerCheckServiceIMP;
import team.AI.serviceIMP.TaskInfoServiceIMP;
import team.AI.serviceIMP.UserServiceIMP;
import team.AI.utils.Sample;
import team.AI.utils.TaskPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/AIHackerCheckServlet")
public class AIHackerCheckServlet extends HttpServlet {
    Boolean isrun = false;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String url = request.getParameter("url");
        String keyword = request.getParameter("keyword");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String words = "";
                ArrayList arrayList = new ArrayList();
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
                                words = words + next;
                            }
                        }
                        words=words.trim();
                        System.out.println(words);
                        if (keyword.equals("")) {
                            AIHeckerCheckServiceIMP aiHeckerCheckServiceIMPH = new AIHeckerCheckServiceIMP();
                            ArrayList arrayList1 = aiHeckerCheckServiceIMPH.HeckerCheck();
                            Iterator iterator1 = arrayList1.iterator();
                            while (iterator1.hasNext()) {
                                String next = (String) iterator1.next();
                                int count = words.indexOf(next);
                                if (count != -1) {
                                    lists.add(newURL);
                                    lists.add(next);
                                    arrayList.add(lists);
                                }
                            }
                        }
                        if (!keyword.equals("")) {
                            int i1 = words.indexOf(keyword);
                            if (i1 != -1) {
                                lists.add(newURL);
                                lists.add(keyword);
                                arrayList.add(lists);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        webClient.getCurrentWindow().getJobManager().removeAllJobs();
                        System.gc();
                        webClient.close();
                    }
                    File file = new File(address);
                    deleteFiles.delete(file);
//                    if (lists.isEmpty()) {
//                        Map map = new HashMap();
//                        map.put("res","none");
//                        try {
//                            response.getWriter().print(JSONObject.toJSONString(map));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }else{
//                        String resultJson = JSONObject.toJSONString(lists);
//                        try {
//                            response.getWriter().print(resultJson);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
                } else {
                    System.out.println("输入错误");
                }
                isrun = true;
            }
            //发送邮件
        };

        String time = String.valueOf(System.currentTimeMillis());
        //添加历史记录
        Date date = new Date();
        String time1 = date.toLocaleString();
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("userinfo");
        HistroyAct histroyAct = new HistroyAct();
        histroyAct.setUser(userBean.getName());
        histroyAct.setActtime(time1);
        if (!url.equals("") && keyword.equals("")) {
            histroyAct.setActcontent("检测网站-->" + url);
        }
        if (!url.equals("") && !keyword.equals("")) {
            histroyAct.setActcontent("检测网站-->" + url + "  检测词-->" + keyword);
        }
        histroyAct.setActname("黑客入侵检测");
        UserServiceIMP userServiceIMP = new UserServiceIMP();
        userServiceIMP.InsertHistroyinfo(histroyAct);


        //将任务加入到task表中
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskid(time);
        taskInfo.setType("黑客入侵检测");
        taskInfo.setEmail(userBean.getEmail());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String times = simpleDateFormat.format(new Date());
        taskInfo.setStarttime(times);
        taskInfo.setRunNumber(0);
        taskInfo.setTaskurl(url);
        taskInfo.setIsrun(isrun);
        TaskInfoServiceIMP taskInfoServiceIMP = new TaskInfoServiceIMP();
        taskInfoServiceIMP.Insertinfo(taskInfo);
        String resultJson = JSONObject.toJSONString(taskInfo);
        try {
            response.getWriter().print(resultJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TaskPool.addTask(time, runnable, 1);

    }
}
