package team.AI.utils;

import team.AI.bean.ResultBean;
import team.SensitiveWord.entity.UrlInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 王智源
 */
public class ResultSpliceUtil {
    /**
     * 拼接task-card
     * @param results
     * @return
     */
    public static String spliceResult1(ArrayList<ArrayList> results,String taskid,String tasktime){
        if (null==results||results.size()==0){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        //拼接一个task-card
        StringBuilder builder = new StringBuilder("<div class=\"task-card\">\n" +
                "        <div class=\"task-title\">\n" +
                "            <span><img class=\"expand\" src=\"assets/images/展开.png\" alt=\"展开\"></span>\n" +
                "            <span class=\"task-title1\">\n" +
                "                    <a href=\"#\">任务ID：")
                .append(taskid)
                .append("</a>\n" +
                        "                    <a href=\"?C=N&amp;O=D\">&nbsp;↓&nbsp;</a>\n" +
                        "                </span>\n" +
                        "            <span class=\"task-title2\">\n" +
                        "                    <a href=\"#\">创建时间:")
                .append(tasktime)
                .append("</a>\n" +
                        "                    <a href=\"?C=N&amp;O=D\">&nbsp;↓&nbsp;</a>\n" +
                        "                </span>\n" +
                        "            <span class=\"task-title3\">\n" +
                        "                    <a href=\"\">运行次数：")
                .append("</a>\n" +
                        "                </span>\n" +
                        "        </div>\n" +
                        "        <hr>\n" +
                        "        <div class=\"task-content\">\n");

        ArrayList list = results.get(0);
        String url = null;
        String fileName = null;
        String type = null;
        for (int i = 0; i < list.size(); i++) {
            if (i % 3 == 0) {
                url = (String) list.get(i);
                continue;
            }
            if (i % 3 == 1) {
                fileName = (String) list.get(i);
                continue;
            }
            if (i % 3 == 2) {
                type = (String) list.get(i);
            }
            builder.append("<div class=\"task-count\">\n" +
                    "                <span><a href=\"#\">运行时间：")
                    .append(time)
                    .append("</span>\n" +
                            "                <hr><div class=\"content\">\n" +
                            "                    <span class=\"url\">")
                    .append(url)
                    .append("</span>\n" +
                            "                    <span class=\"word\">")
                    .append(fileName)
                    .append("</span>\n" +
                            "                    <span>")
                    .append(type)
                    .append("</span>\n" +
                            "                </div></div>");
        }
        builder.append("</div>\n" +
                "    </div>");
        return builder.toString();
    }

    /**
     * 拼接敏感词结果html
     * @param urlInfos
     * @param taskid
     * @param tasktime
     * @return
     */
    public static String spliceResult(ArrayList<UrlInfo> urlInfos,String taskid,String tasktime){
        if (null==urlInfos||urlInfos.size()==0){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());

        //拼接一个task-card
        StringBuilder builder = new StringBuilder("<div class=\"task-card\">\n" +
                "        <div class=\"task-title\">\n" +
                "            <span><img class=\"expand\" src=\"assets/images/展开.png\" alt=\"展开\"></span>\n" +
                "            <span class=\"task-title1\">\n" +
                "                    <a href=\"#\">任务ID：")
                .append(taskid)
                .append("</a>\n" +
                        "                    <a href=\"?C=N&amp;O=D\">&nbsp;↓&nbsp;</a>\n" +
                        "                </span>\n" +
                        "            <span class=\"task-title2\">\n" +
                        "                    <a href=\"#\">创建时间:")
                .append(tasktime)
                .append("</a>\n" +
                        "                    <a href=\"?C=N&amp;O=D\">&nbsp;↓&nbsp;</a>\n" +
                        "                </span>\n" +
                        "            <span class=\"task-title3\">\n" +
                        "                    <a href=\"\">运行次数：")
                .append("</a>\n" +
                        "                </span>\n" +
                        "        </div>\n" +
                        "        <hr>\n" +
                        "        <div class=\"task-content\">\n");

        for (UrlInfo urlInfo : urlInfos) {
            if (!"无敏感词".equals(urlInfo.getHits())){

                builder.append("<div class=\"task-count\">\n" +
                        "                <span><a href=\"#\">运行时间：")
                        .append(time)
                        .append("</span>\n" +
                                "                <hr><div class=\"content\">\n" +
                                "                    <span class=\"url\">")
                        .append(urlInfo.getUrl())
                        .append("</span>\n" +
                                "                    <span class=\"word\">")
                        .append(urlInfo.getHits())
                        .append("</span>\n" +
                                "                    <span>")
                        .append(urlInfo.getHits().split(",").length)
                        .append("</span>\n" +
                                "                </div></div>");
            }
        }
        builder.append("</div>\n" +
                "    </div>");
        return builder.toString();
    }
}
