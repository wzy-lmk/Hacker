package team.AI.utils;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PickkPicText {
    public  ArrayList isDanger(String url) {
        String APP_ID = "17040432";
        String API_KEY = "0LRt4uf3WiN2srS7tN1BLiWW";
        String SECRET_KEY = "tBm1PaXbIUZgSp5gRqBYNquUN6GD2Tby";
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        ArrayList arrayList=new ArrayList();
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");
        JSONObject res = client.basicGeneralUrl(url, options);
        System.out.println(res.toString(2));
        if(res.has("words_result")){
            JSONArray words_result = res.getJSONArray("words_result");
            for(int i=0;i<words_result.length();i++){
                JSONObject jsonObject = words_result.getJSONObject(i);
                String words =(String) jsonObject.get("words");
                System.out.println(words);
                //arrayList.add(words);
            }
        }

        return arrayList;
    }

    public static void main(String[] args) {
        PickkPicText pickkPicText=new PickkPicText();
        ArrayList danger = pickkPicText.isDanger("/Users/apple/Desktop/IMG_1296.JPG");
//        Iterator iterator = danger.iterator();
//        while(iterator.hasNext()){
//            String next =(String) iterator.next();
//            System.out.println(next);
//        }
    }
}
