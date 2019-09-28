package team.AI.serviceIMP;

import team.AI.DaoIMP.AIHeckerCheckIMP;
import team.AI.IMG.MD5Bean;
import team.AI.service.AIHeckerCheckService;

import java.util.ArrayList;

public class AIHeckerCheckServiceIMP implements AIHeckerCheckService {
    AIHeckerCheckIMP aiHeckerCheckIMP=new AIHeckerCheckIMP();
    /*
       黑客关键词
    */
    public ArrayList HeckerCheck() {
        return aiHeckerCheckIMP.HeckerCheck();
    }

}
