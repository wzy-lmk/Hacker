package team.SensitiveWord.verifySensitive;//package team.SensitiveWord.verifySensitive;

import alex.zhrenjie04.wordfilter.WordFilterUtil;
import alex.zhrenjie04.wordfilter.result.FilteredResult;
import team.SensitiveWord.entity.UrlInfo;

public class AlexWord {

    public static String CheckWord(String text){
        FilteredResult result = WordFilterUtil.filterText(text,'*');
        System.out.println(result.getBadWords());
        return null;
    }


    public static UrlInfo CheckWord(UrlInfo info){
        if (null==info.getContent()||info.getContent().equals("")){
            info.setHits("无敏感词");
        }else {
            FilteredResult result = WordFilterUtil.filterText(info.getContent(),'*');
            if (result.getBadWords().trim().equals("")){
                info.setHits("无敏感词");
            }else {
                info.setHits(result.getBadWords());
            }
        }
        return info;

    }

    public static void main(String[] args) {

//        String string = "太多的伤感 情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
//                + "然后法轮功我们的扮演的角色就是跟随着供铲谠的主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
//                + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制观音法门器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
        String string = "你好，我是你的粉丝";
        CheckWord(string);

    }
}
