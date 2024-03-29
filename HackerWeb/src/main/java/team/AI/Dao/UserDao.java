package team.AI.Dao;

import team.AI.bean.HistroyAct;
import team.AI.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public interface UserDao {
    /*
        用户登陆
    */
    public abstract UserBean login(UserBean userBean);

    /*
        用户注册
    */
    public abstract Boolean reg(UserBean userBean);

    /*
        判断手机号和邮箱是否存在
    */
    public abstract Boolean isExistPhoneAndEmail(UserBean userBean);

    /*
        以邮箱查找用户的姓名
    */
    public abstract UserBean emailFindPhone(UserBean userBean);
    /*
        通过邮箱修改用户的密码
    */
    public abstract int emailToUpdatePWD(UserBean userBean);
    /*
        修改个人信息
    */
    public abstract int ChangeSelfinfo(UserBean userBean);
    /*
        添加历史信息
    */
    public abstract int InsertHistroyinfo(HistroyAct histroyAct);
    /*
        查询历史信息
    */
    public abstract List<HistroyAct> Selecthistroyinfo(HistroyAct histroyAct);

}
