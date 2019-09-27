package team.AI.IMG;

import com.sun.javaws.jnl.MatcherReturnCode;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.List;

public class MD5IMP {
    /*
        添加md5的值
     */
    public int InsertMD5(MD5Bean md5Bean){
        QueryRunner runner=new QueryRunner(DBU.getDataSource());
        String sql="insert md5 (newurl,url,md5)values(?,?,?)";
        Object object[]={md5Bean.getNewurl(),md5Bean.getUrl(),md5Bean.getMd5()};
        try {
            int update=0;
            update = runner.update(sql, object);
            if(update!=0){
                return update;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*
        查询是否存在
     */
    public Boolean SelectUrl(MD5Bean md5Bean){
        QueryRunner runner=new QueryRunner(DBU.getDataSource());
        String sql="select * from md5 where url='"+md5Bean.getNewurl()+"'";
        try {
            List<Object[]> query = runner.query(sql, new ArrayListHandler());
            if(!query.isEmpty()){
                return false;
            }else{
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
        通过网页url和图片的url来查找
    */
    public MD5Bean NewUrlAndUrl(MD5Bean md5Bean){
        QueryRunner runner=new QueryRunner(DBU.getDataSource());
        String sql="select * from md5 where newurl='"+md5Bean.getNewurl()+"' and url='"+md5Bean.getUrl()+"' ";
        try {
            MD5Bean query = runner.query(sql, new BeanHandler<MD5Bean>(MD5Bean.class));
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean SelectNewUrlAndUrl(MD5Bean md5Bean){
        QueryRunner runner=new QueryRunner(DBU.getDataSource());
        String sql="select * from md5 where newurl='"+md5Bean.getNewurl()+"' and url='"+md5Bean.getUrl()+"' ";
        try {
            Object[] query = runner.query(sql, new ArrayHandler());
            if(query.length==0){
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
