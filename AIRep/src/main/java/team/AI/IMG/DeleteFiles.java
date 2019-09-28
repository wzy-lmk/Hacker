package team.AI.IMG;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DeleteFiles {
    public static void main(String[] args) {
        DeleteFiles deleteFiles=new DeleteFiles();
        deleteFiles.CreateFile("/Users/apple/Desktop/img0");
        //File file = new File("/Users/apple/Desktop/img0");

    }

    public void delete(File file) {
        if(!file.exists()) return;
        if(file.isFile() || file.list()==null) {
            file.delete();
        }else {
            File[] files = file.listFiles();
            for(File a:files) {
                delete(a);
            }
            file.delete();
        }

    }

    public void CreateFile(String path){
        File file=new File(path);
        if(!file.exists()){//如果文件夹不存在
            file.mkdir();//创建文件夹
        }else{
            file.mkdir();
        }

    }
    //
    public static void deleteDir(String dirPath) {
        File file = new File(dirPath);
        if(file.isFile())
        {
            file.delete();
        }else
        {
            File[] files = file.listFiles();
            if(files == null)
            {
                file.delete();
            }else
            {
                for (int i = 0; i < files.length; i++)
                {
                    deleteDir(files[i].getAbsolutePath());
                }
                file.delete();
            }
        }

    }
}


