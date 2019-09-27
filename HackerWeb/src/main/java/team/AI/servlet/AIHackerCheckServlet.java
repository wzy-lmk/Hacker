package team.AI.servlet;

import com.alibaba.fastjson.*;
import team.AI.IMG.DealUrl;
import team.AI.IMG.ImgAddress;
import team.AI.bean.AIHeckerCheckBean;
import team.AI.serviceIMP.AIHeckerCheckServiceIMP;
import team.AI.utils.PickkPicText;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

@WebServlet("/AIHackerCheckServlet")
public class AIHackerCheckServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String url = request.getParameter("url");

        AIHeckerCheckServiceIMP aiHeckerCheckServiceIMP=new AIHeckerCheckServiceIMP();
        ArrayList HeckerCheck = aiHeckerCheckServiceIMP.HeckerCheck();
        ArrayList<String> lists = new ArrayList<>();

        if(!url.equals("")){
            ImgAddress imgAddress=new ImgAddress();
            ArrayList address = imgAddress.imgAddress(url);
            DealUrl dealUrl=new DealUrl();

            PickkPicText pickkPicText=new PickkPicText();
            Iterator iterator = address.iterator();

            while(iterator.hasNext()){
                String imgHalfAddr = (String)iterator.next();//图片地址
                if(dealUrl.isNet(imgHalfAddr)){
                    ArrayList danger = pickkPicText.isDanger(imgHalfAddr);
                    Iterator iterator1 = danger.iterator();
                    int i=0;
                    while(iterator1.hasNext()){
                        Iterator iterator2 = HeckerCheck.iterator();
                        while(iterator2.hasNext()){
                            AIHeckerCheckBean heckerCheckBean =(AIHeckerCheckBean) iterator2.next();
                            if(iterator1.next().equals(heckerCheckBean.getWords())){
                                //返回url
                                if(i==0){
                                    lists.add(imgHalfAddr);
                                    i++;
                                }
                            }

                        }
                    }
                    if(lists.isEmpty()){
                        response.getWriter().print("none");
                    }

                }else{
                    String network = dealUrl.getNetwork(url);
                    String addReve = dealUrl.getUrl(imgHalfAddr);
                    String fullUrlAddr=network+addReve;//图片地址
                    ArrayList danger = pickkPicText.isDanger(fullUrlAddr);
                    Iterator iterator1 = danger.iterator();
                    int i=0;
                    while(iterator1.hasNext()){
                        Iterator iterator2 = HeckerCheck.iterator();
                        while(iterator2.hasNext()){
                            AIHeckerCheckBean heckerCheckBean =(AIHeckerCheckBean) iterator2.next();
                            if(iterator1.next().equals(heckerCheckBean)){
                                //返回url
                                if(i==0){
                                    lists.add(imgHalfAddr);
                                    i++;
                                }
                            }else{
                                //安全
                            }

                        }
                    }
                    if(lists.isEmpty()){
                        //你要检查的网站暂时安全,请继续关注本网站
                        response.getWriter().print("none");
                    }
                }
            }
            String resultJson = JSONObject.toJSONString(lists);
            response.getWriter().print(resultJson);


        }

    }
}
