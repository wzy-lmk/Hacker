package team.AI.utils;

import team.AI.bean.TaskInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * 发送各个类型邮件
 */
public class SendHtmlMail {


    /**
     *发送文件监测结果邮件
     * @param title 任务名称
     * @param time 创建时间
     * @param info 监测结果信息
     *             {
     *                  改动文件所在页面链接
     *                  文件名称
     *                  改动类型：增加，修改
     *             }
     */
    public static void sendFileCheckMail(String title, String time, ArrayList<Object> info,List<String> userMails){
        String mails = String.join(",",userMails);

        //拼接表格内容
        String tableContent="";

        String mailContent = " <style type=\"text/css\">\n" +
                "        #customers {\n" +
                "            font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\n" +
                "            border-collapse: collapse;\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "\n" +
                "        #customers td,\n" +
                "        #customers th {\n" +
                "            border: 1px solid #ddd;\n" +
                "            padding: 8px;\n" +
                "        }\n" +
                "\n" +
                "        #customers tr:nth-child(even) {\n" +
                "            background-color: #f2f2f2;\n" +
                "        }\n" +
                "\n" +
                "        #customers tr:hover {\n" +
                "            background-color: #ddd;\n" +
                "        }\n" +
                "\n" +
                "        #customers th {\n" +
                "            padding-top: 12px;\n" +
                "            padding-bottom: 12px;\n" +
                "            text-align: left;\n" +
                "            background-color: #4CAF50;\n" +
                "            color: white;\n" +
                "        }\n" +
                "    </style>" +
                " <div>\n" +
                "            <div style=\"text-align: center\">\n" +
                "                <img src=\"https://s2.ax1x.com/2019/10/02/udZr1H.png\">\n" +
                "            </div>\n" +
                "            <br>\n" +
                "            <div style=\"width:80%;margin: 0 auto;\">\n" +
                "                <div style=\"text-align: center\" id=\"title\">\n" +
                "                    <h3>您提交的监测任务有新的结果</h3>\n" +
                "                </div>\n" +
                "                <hr>\n" +
                "                <div id=\"content-info\" style=\"text-align: center\">\n" +
                "                    <span>任务名称： "+title+"</span>\n" +
                "                    <span style=\"margin-left: 40px\">类型：网站文件监测</span>\n" +
                "                    <span style=\"margin-left: 40px\">创建时间："+time+"</span>\n" +
                "                </div>\n" +
                "                <div><h4>结果详情</h4></div>\n" +
                "                <div>\n" +
                "                    新的监测结果\n" +
                "                </div>\n" +
                "                <div id=\"content-body\">\n" +
                "                        <table id=\"customers\" aria-disabled=\"false\">\n" +
                "                            <tbody><tr>\n" +
                "                                <th>链接</th><th>文件名</th><th>状态</th>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                                                 tableContent+ //添加表格内容
                "                            </tr>\n" +
                "                        </tbody></table>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>    ";
    }

    /**
     * 发送反共黑客网监测邮件
     * @param title 任务名称
     * @param time 创建时间
     * @param content 监测结果内容
     * @param userMails 要发送的用户邮件列表
     */
    public static void SendFGHackerMail(String title,String time,String content,List<String> userMails){
        String mails = String.join(",",userMails);

        String mailContent = "        <style type=\"text/css\">\n" +
                "           #customers {\n" +
                "  font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "#customers td, #customers th {\n" +
                "  border: 1px solid #ddd;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "#customers tr:nth-child(even){background-color: #f2f2f2;}\n" +
                "\n" +
                "#customers tr:hover {background-color: #ddd;}\n" +
                "\n" +
                "#customers th {\n" +
                "  padding-top: 12px;\n" +
                "  padding-bottom: 12px;\n" +
                "  text-align: left;\n" +
                "  background-color: #4CAF50;\n" +
                "  color: white;\n" +
                "}\n" +
                "            </style>\n" +
                "<div>\n" +
                "        <div style=\"text-align: center\">\n" +
                "            <img src=\"https://s2.ax1x.com/2019/10/02/udZr1H.png\">\n" +
                "        </div>\n" +
                "        <br>\n" +
                "        <div style=\"width:80%;margin: 0 auto;\">\n" +
                "            <div style=\"text-align: center\" id=\"title\">\n" +
                "                <h3>反共黑客网有新的动态</h3>\n" +
                "            </div>\n" +
                "            <hr>\n" +
                "            <div id=\"content-info\" style=\"text-align: center\">\n" +
                "                <span>任务名称：网站监测 </span>\n" +
                "                <span style=\"margin-left: 40px\">类型：反共黑客网监测</span>\n" +
                "                <span style=\"margin-left: 40px\">创建时间："+time+"</span>\n" +
                "            </div>\n" +
                "            <div><h4>结果详情</h4></div>\n" +
                "            <div>\n" +
                "                新的网站动态\n" +
                "            </div>\n" +
                "            <div id=\"content-body\">\n" +
                "                    <table id=\"customers\" aria-disabled=\"false\">\n" +
                "                        <tbody><tr>\n" +
                "                            <th>时间</th><th>标题</th><th>内容</th>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td>"+time+"</td><td>"+title+"</td><td>"+content+"</td>\n" +
                "                        </tr>\n" +
                "                    </tbody></table>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>";

        Map<String,String> map=new HashMap<String,String>();

        //邮件服务器主机名(smtp服务器地址)
        //如：qq的smtp服务器地址：smtp.qq.com
        map.put("smtp", "smtp.qq.com");
        //邮件传输协议：如smtp
        map.put("protocol", "smtp");
        //登录邮箱的用户名
        map.put("username", "319732708@qq.com");
        //登录邮箱的密码
        map.put("password", "comgmkjavictbgde");
        //发送人的帐号
        map.put("from", "319732708@qq.com");
        //接收人的帐号，多个以","号隔开
        map.put("to", mails);
        //邮件主题
        map.put("subject", "任务监测结果");
        //邮件内容
        map.put("body", content);
        //内嵌了多少张图片，如果没有，则new一个不带值的Map
        Map<String,String> image=new HashMap<String,String>();
        List<String> list=new ArrayList<String>();

        //创建实例
        HTMLMail sm=new HTMLMail(map,list,image);
        //执行发送
        try {
            sm.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送敏感词监测邮件
     * @param tablemap 表格内容
     * @param info 任务信息
     */
    public static void SendSenitiveTaskMail(Map<String, Map<String, Integer>> tablemap, TaskInfo info){

        StringBuilder tablebuilder = new StringBuilder();

        //拼接表格内容
        tablemap.forEach(new BiConsumer<String, Map<String, Integer>>() {
            @Override
            public void accept(String s, Map<String, Integer> s2) {
                tablebuilder.append("<tr><td>").append(s).append("</td><td>");

                s2.forEach(new BiConsumer<String, Integer>() {
                    @Override
                    public void accept(String s, Integer integer) {
                        tablebuilder.append(s).append("</td><td>").append(integer).append("</td></tr>");
                    }
                });

            }
        });

        String content="<style type=\"text/css\">\n" +
                "    #customers {  font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;  border-collapse: collapse;  width: 100%;}#customers td, #customers th {  border: 1px solid #ddd;  padding: 8px;}#customers tr:nth-child(even){background-color: #f2f2f2;}#customers tr:hover {background-color: #ddd;}#customers th {  padding-top: 12px;  padding-bottom: 12px;  text-align: left;  background-color: #4CAF50;  color: white;}\n" +
                "</style>\n" +
                "<div>\n" +
                "    <div style=\"text-align: center\">\n" +
                "        <img src=\"https://s2.ax1x.com/2019/10/02/udZr1H.png\">\n" +
                "    </div>\n" +
                "    <br>\n" +
                "    <div style=\"width:80%;margin: 0 auto;\">\n" +
                "        <div style=\"text-align: center\" id=\"title\">\n" +
                "            <h3>\n" +
                "                您提交的监测任务有新的结果\n" +
                "            </h3>\n" +
                "        </div>\n" +
                "        <hr>\n" +
                "        <div id=\"content-info\" style=\"text-align: center\">\n" +
                "            <span>\n" +
                "                任务名称：" +info.getType()+
                "            </span>\n" +
                "            <span style=\"margin-left: 40px\">\n" +
                "                类型：敏感词查询\n" +
                "            </span>\n" +
                "            <span style=\"margin-left: 40px\">\n" +
                "                创建时间：" +info.getStarttime().toLocalDate()+
                "            </span>\n" +
                "        </div>\n" +
                "        <div>\n" +
                "            <h4>\n" +
                "                结果详情\n" +
                "            </h4>\n" +
                "        </div>\n" +
                "        <div>\n" +
                "            在下列页面中发现疑似敏感词信息，请及时处理\n" +
                "        </div>\n" +
                "        <div id=\"content-body\">\n" +
                "          <table id=\"customers\" aria-disabled=\"false\">\n" +
                "                        <tbody><tr>\n" +
                "                            <th>出现链接</th><th>敏感词</th><th>数量</th>\n" +
               tablebuilder.toString() +
                "              </tbody></table>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>";

        Map<String,String> map=new HashMap<String,String>();

        //邮件服务器主机名(smtp服务器地址)
        //如：qq的smtp服务器地址：smtp.qq.com
        map.put("smtp", "smtp.qq.com");
        //邮件传输协议：如smtp
        map.put("protocol", "smtp");
        //登录邮箱的用户名
        map.put("username", "319732708@qq.com");
        //登录邮箱的密码
        map.put("password", "comgmkjavictbgde");
        //发送人的帐号
        map.put("from", "319732708@qq.com");
        //接收人的帐号，多个以","号隔开
        map.put("to", "319732708@qq.com");
        //邮件主题
        map.put("subject", "任务监测结果");
        //邮件内容
        map.put("body", content);
        //内嵌了多少张图片，如果没有，则new一个不带值的Map
        Map<String,String> image=new HashMap<String,String>();
        List<String> list=new ArrayList<String>();

        //创建实例
        HTMLMail sm=new HTMLMail(map,list,image);
        //执行发送
        try {
            sm.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
