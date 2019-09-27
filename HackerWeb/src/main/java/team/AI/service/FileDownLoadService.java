package team.AI.service;

import team.SensitiveWord.entity.UrlInfo;

import java.util.ArrayList;

public interface FileDownLoadService {

    public String getFileName();

    public String CreateFile(ArrayList<UrlInfo> arrayList);
}
