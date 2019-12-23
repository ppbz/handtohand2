package com.sjtu.handtohandnew;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.baidu.mapapi.map.BaiduMap;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMucSharedFile;
import com.sjtu.handtohandnew.Base.Model;
import com.sjtu.handtohandnew.Bean.UserInfoBean;
import com.sjtu.handtohandnew.utils.Constant;
import com.tencent.connect.UserInfo;

import java.util.List;

public class EventListener {
    private Context mContext;
    private LocalBroadcastManager mLBM;

    public EventListener(Context context) {
        mContext = context;
        //创建一个发送广播的管理者对象
        mLBM = LocalBroadcastManager.getInstance(mContext);

        //注册一个联系人变化的监听
        EMClient.getInstance().contactManager().setContactListener(emContactListener);
    }

    //注册一个联系人变化的监听
    private final EMContactListener emContactListener = new EMContactListener() {

        //添加联系人方法
        @Override
        public void onContactAdded(String hxid) {
            //数据库更新
            Model.getInstance().getDBManager().getContactTableDao().saveContact(new UserInfoBean(hxid), true);

            //发送联系人变化的广播
            mLBM.sendBroadcast(new Intent(Constant.CONTACT_CHANGED));
        }

        //联系人删除后执行的方法
        @Override
        public void onContactDeleted(String hxid) {
            //数据库更新
            Model.getInstance().getDBManager().getContactTableDao().deleteContactByHxId(hxid);
            //Model.getInstance().getDBManager().getInviteTableDao().removeInvitation(hxid);
            //发送联系人变化的广播
            mLBM.sendBroadcast(new Intent(Constant.CONTACT_CHANGED));
        }

        @Override
        public void onContactInvited(String s, String s1) {

        }

        @Override
        public void onFriendRequestAccepted(String s) {

        }

        @Override
        public void onFriendRequestDeclined(String s) {

        }


    };
}
