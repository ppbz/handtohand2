package com.sjtu.handtohandnew.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sjtu.handtohandnew.Activity.AddressActivity;
import com.sjtu.handtohandnew.Activity.HistoryActivity;
import com.sjtu.handtohandnew.Activity.LoginActivity;
import com.sjtu.handtohandnew.Base.BaseFragment;
import com.sjtu.handtohandnew.Base.BaseInterface;
import com.sjtu.handtohandnew.Base.Model;
import com.sjtu.handtohandnew.R;

public class MineFragment extends BaseFragment implements BaseInterface {
    private TextView textViewName;
    private ContactListFragment contactListFragment;

    @ViewInject(R.id.mineFragment)
    private LinearLayout linearLayout01;

    @OnClick(R.id.fragment_mine_DiZhi)
    private void addressMine(View view){
        startAct(AddressActivity.class);
    }

    @OnClick(R.id.fragment_mine_LiShi)
    private void historyMine(View view){
        startAct(HistoryActivity.class);
    }

    /*@OnClick(R.id.fragment_mine_friend)
    private void myFriend(View view){
        contactListFragment = new ContactListFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.act_home_viewpager,contactListFragment);
    }*/


    @OnClick(R.id.fragment_mine_login)
    private void logout(View view){
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //登录环信服务器退出登录
                EMClient.getInstance().logout(false, new EMCallBack() {
                    @Override
                    public void onSuccess() {

                        //关闭DBHelper
                        Model.getInstance().getDBManager().close();

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                //更新UI
                                Toast.makeText(getActivity(),"退出成功",Toast.LENGTH_SHORT).show();
                                //回到登录页面
                                startAct(LoginActivity.class);

                                getActivity().finish();
                            }
                        });
                    }

                    @Override
                    public void onError(int i, String s) {

                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });
    }

    @Override
    protected void init() {

    }

    @Override
    protected View initContentView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_mine,null);
        ViewUtils.inject(this,view);
        textViewName = view.findViewById(R.id.fragment_mine_userName);
        InitData();
        return view;
    }



    @Override
    public void InitView() {
    }

    @Override
    public void InitData() {
        textViewName.setText(EMClient.getInstance().getCurrentUser());
    }

    @Override
    public void InitViewOper() {

    }

    /*@OnClick(R.id.fragment_mine_DiZhi)*/
}
