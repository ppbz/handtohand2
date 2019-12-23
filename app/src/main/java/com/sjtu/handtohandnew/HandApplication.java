package com.sjtu.handtohandnew;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
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
        /*options.setAcceptInvitationAlways(true);
        options.setAutoAcceptGroupInvitation(false);*/

        EaseUI.getInstance().init(this,options);

        //初始化数据模型层
        Model.getInstance().init(this);

        //初始化全局上下文
        mContext = this;

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        /*SDKInitializer.setCoordType(CoordType.BD09LL);*/
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }
    //获取全局上下文对象
    public static Context getGlobalApplication(){
        return mContext;
    }

}
