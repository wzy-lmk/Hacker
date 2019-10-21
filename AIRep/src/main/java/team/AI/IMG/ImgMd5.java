package team.AI.IMG;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImgMd5 {



    public String getMD5(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream in = conn.getInputStream();
        String md5Hex = DigestUtils.md5Hex(in);
        DigestUtils.md5Hex(md5Hex);
        return md5Hex;
    }


    public static void main(String[] args) throws IOException {
        ImgMd5 imgMd5 =new ImgMd5();
        String md5 = imgMd5.getMD5("http://www.chzu.edu.cn/_visitcount?siteId=3&type=2&columnId=12638");
        System.out.println(md5);
    }
}
