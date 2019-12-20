package com.sjtu.handtohandnew.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.exceptions.HyphenateException;
import com.sjtu.handtohandnew.Activity.ChatActivity;
import com.sjtu.handtohandnew.Activity.OrderDetailActivity;
import com.sjtu.handtohandnew.Base.Model;
import com.sjtu.handtohandnew.Bean.UserInfoBean;
import com.sjtu.handtohandnew.R;
import com.sjtu.handtohandnew.utils.Constant;
import com.tencent.connect.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactListFragment extends EaseContactListFragment {
    private ImageView iv_contact_red;
    private LocalBroadcastManager mLBM;
    private LinearLayout ll_contact_invite;
    private String mHxid;

    private BroadcastReceiver ContactChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //刷新页面
            refreshContact();
        }
    };

    @Override
    protected void initView() {
        super.initView();
//设置listview条目的点击事件
        setContactListItemClickListener( new EaseContactListItemClickListener() {
            @Override
            public void onListItemClicked(EaseUser user) {
                if (user == null) {
                    return;
                }

                Intent intent = new Intent( getActivity(), ChatActivity.class );

                //传递参数
                intent.putExtra( EaseConstant.EXTRA_USER_ID, user.getUsername() );
                startActivity( intent );
            }
        } );

    }
    @Override
    protected void setUpView() {
        super.setUpView();
        //注册广播
        mLBM = LocalBroadcastManager.getInstance( getActivity() );
        //mLBM.registerReceiver( ContactInviteChangeReceiver, new IntentFilter( Constant.CONTACT_INVITE_CHANGED ) );
        mLBM.registerReceiver( ContactChangeReceiver, new IntentFilter( Constant.CONTACT_CHANGED ) );
        //mLBM.registerReceiver( GroupChangeReceiver, new IntentFilter( Constant.GROUP_INVITE_CHANGED ) );

        //从环信服务器获取所有的联系人信息
        getContactFromHxServer();

        //绑定listview和contextmenu
        registerForContextMenu( listView );

    }
    private void getContactFromHxServer() {
        //从环信服务器获取所有的联系人信息
        Model.getInstance().getGlobalThreadPool().execute( new Runnable() {
            @Override
            public void run() {
                try {
                    //获取到所有好友的环信id
                    List<String> hxids = EMClient.getInstance().contactManager().getAllContactsFromServer();

                    //校验
                    if (hxids != null && hxids.size() >= 0) {
                        List<UserInfoBean> contacts = new ArrayList<>();
                        //转换
                        for (String hxid : hxids) {
                            UserInfoBean userInfo = new UserInfoBean( hxid );
                            contacts.add( userInfo );
                        }

                        //保存好友信息到本地数据库
                        Model.getInstance().getDBManager().getContactTableDao().saveContacts( contacts, true );

                        if (getActivity() == null) {
                            return;
                        }
                        //刷新页面
                        getActivity().runOnUiThread( new Runnable() {
                            @Override
                            public void run() {
                                //刷新页面方法
                                refreshContact();
                            }
                        } );

                    }

                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        } );

    }

    //刷新页面
    private void refreshContact() {
        //获取数据
        List<UserInfoBean> contacts = Model.getInstance().getDBManager().getContactTableDao().getContacts();
        //校验
        if (contacts != null && contacts.size() >= 0) {
            //设置数据
            Map<String, EaseUser> contactsMap = new HashMap<>();

            //转换
            for (UserInfoBean contact : contacts) {
                EaseUser easeUser = new EaseUser( contact.getHxid() );
                contactsMap.put( contact.getHxid(), easeUser );
            }
            setContactsMap( contactsMap );

            //刷新页面
            refresh();
        }

    }

    //长按删除
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu( menu, v, menuInfo );
        //获取环信id
        int position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
        EaseUser easeUser = (EaseUser) listView.getItemAtPosition( position );
        mHxid = easeUser.getUsername();

        //添加布局
        getActivity().getMenuInflater().inflate( R.menu.delete, menu );
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.contact_delete) {
            //执行删除选中的联系人操作
            deleteContact();
            return true;
        }
        return super.onContextItemSelected( item );
    }

    //执行删除选中的联系人操作
    private void deleteContact() {
        Model.getInstance().getGlobalThreadPool().execute( new Runnable() {
            @Override
            public void run() {
                //从环信服务器删除联系人
                try {
                    EMClient.getInstance().contactManager().deleteContact( mHxid );
                    //本地数据库更新
                    Model.getInstance().getDBManager().getContactTableDao().deleteContactByHxId( mHxid );
                    if (getActivity() == null) {
                        return;
                    }
                    getActivity().runOnUiThread( new Runnable() {
                        @Override
                        public void run() {
                            //提示
                            Toast.makeText( getActivity(), "删除" + mHxid + "成功", Toast.LENGTH_SHORT ).show();
                            //刷新页面
                            refreshContact();
                        }
                    } );
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    if (getActivity() == null) {
                        return;
                    }
                    getActivity().runOnUiThread( new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText( getActivity(), "删除" + mHxid + "失败", Toast.LENGTH_SHORT ).show();
                        }
                    } );
                }
            }
        } );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //mLBM.unregisterReceiver( ContactInviteChangeReceiver );
        mLBM.unregisterReceiver( ContactChangeReceiver );
        //mLBM.unregisterReceiver( GroupChangeReceiver );
    }

}