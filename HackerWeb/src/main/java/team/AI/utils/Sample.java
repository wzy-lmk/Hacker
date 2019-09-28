package team.AI.utils;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "17040432";
    public static final String API_KEY = "0LRt4uf3WiN2srS7tN1BLiWW";
    public static final String SECRET_KEY = "tBm1PaXbIUZgSp5gRqBYNquUN6GD2Tby";

    public  ArrayList PicText(String url) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        ArrayList arrayList=new ArrayList();
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");

        // 通用文字识别, 图片参数为远程url图片
//        JSONObject res = client.basicGeneralUrl("http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=图片&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=2870322368,453611869&os=218400603,376838744&simid=3363908014,439423957&pn=35&rn=1&di=18480&ln=1282&fr=&fmq=1569573572597_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fpic39.nipic.com%2F20140329%2F5654593_113505353155_2.jpg&rpstart=0&rpnum=0&adpicid=0&force=undefined", options);
//        System.out.println(res.toString(2));

        //String image = "/Users/apple/Desktop/IMG_1296.JPG";
        JSONObject res = client.basicGeneral(url, options);


        boolean words_result = res.has("words_result");
       if(words_result){
           JSONArray words_result1 = res.getJSONArray("words_result");
           for(int i=0;i<words_result1.length();i++){
               JSONObject jsonObject = words_result1.getJSONObject(i);
               String words =(String) jsonObject.get("words");
               //System.out.println(words);
               arrayList.add(words);
           }
       }
        //System.out.println(res.toString(2));
        return arrayList;
    }

    public static void main(String[] args) {
        Sample sample=new Sample();
        ArrayList arrayList = sample.PicText("/Users/apple/Desktop/IMG_1296.JPG");
        Iterator iterator = arrayList.iterator();
        while(iterator.hasNext()){
            String next = (String)iterator.next();
            System.out.println(next);
        }
    }
}
