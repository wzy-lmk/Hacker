package team.AI.DaoIMP;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import team.AI.Dao.UserDao;
import team.AI.bean.HistroyAct;
import team.AI.bean.UserBean;
import team.AI.utils.DBUtiles;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserIMP implements UserDao {
    /*
        用户登陆，判断数据库中是否有此用户
    */
    public UserBean login(UserBean userBean) {
        QueryRunner runner = new QueryRunner(DBUtiles.getDataSource());
        String sql = "select * from user where (email='" + userBean.getEmail() + "' and password='" + userBean.getPassword() + "') or (phone='" + userBean.getPhone() + "' and password='" + userBean.getPassword() + "')";
        try {
            UserBean bean = runner.query(sql, new BeanHandler<UserBean>(UserBean.class));
            if (bean != null) {
                return bean;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
        用户注册
    */
    public Boolean reg(UserBean userBean) {
        QueryRunner runner = new QueryRunner(DBUtiles.getDataSource());
        String sql = "insert user (name,email,phone,password)values(?,?,?,?)";
        Object object[] = {userBean.getName(), userBean.getEmail(), userBean.getPhone(), userBean.getPassword()};
        try {
            int insert = 0;
            insert = runner.update(sql, object);
            if (insert != 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
        判断手机号和邮箱是否存在
    */
    public Boolean isExistPhoneAndEmail(UserBean userBean) {
        QueryRunner runner = new QueryRunner(DBUtiles.getDataSource());
        String sql = "select * from user where phone='" + userBean.getPhone() + "' or email='" + userBean.getEmail() + "'";
        try {
            Object[] objects = runner.query(sql, new ArrayHandler());
            if (objects.length == 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
        以邮箱查找用户的姓名
    */
    public UserBean emailFindPhone(UserBean userBean) {
        QueryRunner runner = new QueryRunner(DBUtiles.getDataSource());
        String sql = "select * from user where email='" + userBean.getEmail() + "'";
        try {
            UserBean bean = runner.query(sql, new BeanHandler<UserBean>(UserBean.class));
            if (bean != null) {
                return bean;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
        通过邮箱修改用户的密码
    */
    public int emailToUpdatePWD(UserBean userBean) {
        QueryRunner runner = new QueryRunner(DBUtiles.getDataSource());
        Object object[] = {userBean.getPassword(), userBean.getEmail()};
        String sql = "update user set password=? where email=? ";
        try {
            int update = runner.update(sql, object);
            if (update != 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*
        修改个人信息
    */
    public int ChangeSelfinfo(UserBean userBean) {
        QueryRunner runner = new QueryRunner(DBUtiles.getDataSource());
        String sql = "update user set name=?,email=?,phone=?,password=? where phone=?";
        Object object[] = {userBean.getName(), userBean.getEmail(), userBean.getPhone(), userBean.getPassword(), userBean.getNewPhone()};
        try {
            int update = 0;
            update = runner.update(sql, object);
            if (update != 0) {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*
        添加历史信息
    */
    public int InsertHistroyinfo(HistroyAct histroyAct) {
        QueryRunner runner = new QueryRunner(DBUtiles.getDataSource());
        Object objects[] = {histroyAct.getActname(), histroyAct.getActcontent(), histroyAct.getActtime()};
        String sql = "insert histroyrecode (actname,actcontent,acttime)values(?,?,?)";
        try {
            int update = 0;
            update = runner.update(sql, objects);
            if(update!=0){
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*
        查询历史信息
    */
    public List<HistroyAct> Selecthistroyinfo() {
        QueryRunner runner=new QueryRunner(DBUtiles.getDataSource());
        String sql="select actname,actcontent,acttime from histroyrecode order by acttime desc";
        try {
            List<HistroyAct> list = runner.query(sql, new BeanListHandler<HistroyAct>(HistroyAct.class));
            if(!list.isEmpty()){
                return list;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        //UserBean userBean = new UserBean();
        //userBean.setPhone("");
        //userBean.setEmail("");
        //userBean.setPhone("17856002909");
//        userBean.setPassword("123456");
//        userBean.setEmail("319732708@qq.com");
//        userBean.setPhone("17856002909");
//        userBean.setName("王智源");

        //       userBean.setPassword("123456");
//        userBean.setName("李梦可");
//        userBean.setEmail("1583214829@qq.com");
//        userBean.setPhone("17856002383");
//        userBean.setPassword("123456");
//
        UserIMP loginIMP = new UserIMP();
//        loginIMP.reg(userBean);

       // int pwd = loginIMP.ChangeSelfinfo(userBean);
        //System.out.println(pwd);

        //HistroyAct histroyAct=new HistroyAct();
//        histroyAct.setActname("修改个人信息");
//        histroyAct.setActcontent("修改失败");
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String s = String.valueOf(df.format(new Date()));
//        histroyAct.setActtime(s);
        List<HistroyAct> list = loginIMP.Selecthistroyinfo();
       for(int i=0;i<list.size();i++){
           HistroyAct histroyAct1 = (HistroyAct)list.get(i);
           System.out.println(histroyAct1.getActname());
       }


    }
}
