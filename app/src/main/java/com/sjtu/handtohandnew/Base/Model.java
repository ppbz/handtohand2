package com.sjtu.handtohandnew.Base;

import android.content.Context;

import com.sjtu.handtohandnew.Bean.UserAccountDao;
import com.sjtu.handtohandnew.Bean.UserInfoBean;
import com.sjtu.handtohandnew.Db.DBManager;

import java.security.PublicKey;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//s数据模型层全局类
public class Model {
    private Context mContext;
    private DBManager dbManager;
    public UserAccountDao userAccountDao;
    private ExecutorService executors = Executors.newCachedThreadPool();
    //创建对象
    private static Model model = new Model();
    //私有化构造
    private Model(){

    }
    //获取单例对象
    public static Model getInstance(){
        return model;
    }
    //初始化的方法
    public void init(Context context){
        mContext = context;

        //创建用户账号数据库的操作类对象
        userAccountDao = new UserAccountDao(mContext);
    }
    //获取全局线程池对象
    public ExecutorService getGlobalThreadPool(){
        return executors;
    }
    //用户登陆成功后的处理方法
    public void loginSuccess(UserInfoBean account){
        //校验
        if(account == null){
            return;
        }
        if(dbManager != null){
            dbManager.close();
        }
        dbManager = new DBManager(mContext,account.getName());

    }
    public DBManager getDBManager(){
        return dbManager;
    }

    //获取用户账号数据库的操作类对象
    public UserAccountDao getUserAccountDao(){
        return userAccountDao;
    }
}
