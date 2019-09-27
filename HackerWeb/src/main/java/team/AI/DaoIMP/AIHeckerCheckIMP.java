package team.AI.DaoIMP;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import team.AI.Dao.AIHeckerCheckDao;
import team.AI.bean.AIHeckerCheckBean;
import team.AI.utils.DBUtiles;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class AIHeckerCheckIMP implements AIHeckerCheckDao {
    /*
        黑客关键词
     */
    public ArrayList HeckerCheck() {
        QueryRunner runner=new QueryRunner(DBUtiles.getDataSource());
        String sql="select words from aihackercheck";
        try {
            ArrayList<AIHeckerCheckBean> list =(ArrayList) runner.query(sql, new BeanListHandler<AIHeckerCheckBean>(AIHeckerCheckBean.class));
            if (!list.isEmpty()){
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) {
        AIHeckerCheckIMP aiHeckerCheckIMP=new AIHeckerCheckIMP();
        ArrayList arrayList = aiHeckerCheckIMP.HeckerCheck();
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()){
            AIHeckerCheckBean bean =(AIHeckerCheckBean) iterator.next();
            System.out.println(bean.getWords());
        }

    }
}
