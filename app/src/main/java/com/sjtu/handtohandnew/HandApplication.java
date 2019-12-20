package com.sjtu.handtohandnew;

import android.app.Application;
import android.content.Context;

import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.sjtu.handtohandnew.Base.Model;

public class HandApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化EaseUI
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(true);
        options.setAutoAcceptGroupInvitation(false);

        EaseUI.getInstance().init(this,options);

        //初始化数据模型层
        Model.getInstance().init(this);

        //初始化全局上下文
        mContext = this;
    }
    //获取全局上下文对象
    public static Context getGlobalApplication(){
        return mContext;
    }

}
