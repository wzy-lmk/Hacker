package team.AI.serviceIMP;

import sun.applet.Main;
import team.AI.service.FileDownLoadService;
import team.AI.utils.Excel;
import team.SensitiveWord.entity.UrlInfo;

import java.io.File;
import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.Date;

public class FileDowmLoadServiceIMP implements FileDownLoadService {

    private String fileroot="D:\\IDEA-WorkSpace\\Antihacker";
    private String filename;

    public String getFileName(){
        filename = Long.toString(new Date().getTime());
        String filepath = fileroot+ File.separator+filename;
        return filepath;

    }

    public String CreateFile(ArrayList<UrlInfo> arrayList){
        Excel excel = new Excel();
        excel.Excel(arrayList.size(),2,arrayList,getFileName());
        System.out.println("file:"+filename+"---->create success!");
        return filename+".xlsx";
    }

}
