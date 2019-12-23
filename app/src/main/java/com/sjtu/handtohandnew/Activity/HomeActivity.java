package com.sjtu.handtohandnew.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.*;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sjtu.handtohandnew.Base.BaseActivity;
import com.sjtu.handtohandnew.Base.BaseInterface;
import com.sjtu.handtohandnew.Fragment.Chat01Fragment;
import com.sjtu.handtohandnew.Fragment.ChatFragment;
import com.sjtu.handtohandnew.Fragment.ContactListFragment;
import com.sjtu.handtohandnew.Fragment.MineFragment;
import com.sjtu.handtohandnew.Fragment.OrderFragment;
import com.sjtu.handtohandnew.Fragment.SelectFragment;
import com.sjtu.handtohandnew.Fragment.SendFragment;
import com.sjtu.handtohandnew.R;

public class HomeActivity extends BaseActivity implements BaseInterface {
    private SelectFragment selectFragment;
    private LinearLayout llHomepage,llOrder,llSend,llMine,llChat;
    private OrderFragment orderFragment,orderFragment02;
    private SendFragment sendFragment,sendFragment02;
    private MineFragment mineFragment;
    private SelectFragment selectFragment02;
    private Button mBtnFind,mBtnSend;
    private ChatFragment chatFragment;
    private ContactListFragment chat01Fragment;

    public static HomeActivity homeAct;

    /*@ViewInject(R.id.LayoutSelect)
    public static RelativeLayout relativeLayoutSelect;

    @com.lidroid.xutils.view.annotation.event.OnClick(R.id.btn_sfind)
    private void find(View view){
        orderFragment02 = new OrderFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.act_home_viewpager, orderFragment02).addToBackStack(null).commitAllowingStateLoss();
    }*/

    /*@ViewInject(R.id.act_home_viewpager)
    public static ViewPager viewPager;

    @ViewInject(R.id.mineFragment)
    public static ScrollView scrollView;*/

    /*@com.lidroid.xutils.view.annotation.event.OnClick(R.id.fragment_mine_DiZhi)
    private void myAddress(View view){
        startAct(AddressActivity.class);
    }*/




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        selectFragment = new SelectFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.act_home_viewpager,selectFragment,"a").commitAllowingStateLoss();
        //没有这步骤后面点击两次会出错
        orderFragment = new OrderFragment();
        sendFragment = new SendFragment();
        selectFragment02 = new SelectFragment();
        mineFragment = new MineFragment();
        chat01Fragment = new ContactListFragment();
        /*orderFragment02 = new OrderFragment();
        sendFragment02 = new SendFragment();*/


        //设计点击事件
        llHomepage = (LinearLayout)findViewById(R.id.act_home_homepageLinearLayout);
        llOrder = (LinearLayout)findViewById(R.id.act_home_orderLinearLayout);
        llSend = (LinearLayout)findViewById(R.id.act_home_sendLinearLayout);
        llMine = (LinearLayout)findViewById(R.id.act_home_mineLinearLayout);
        llChat = findViewById(R.id.act_home_chatLinearLayout);
        /*mBtnFind = findViewById(R.id.btn_sfind);
        mBtnSend = findViewById(R.id.btn_ssend);*/

        llHomepage.setOnClickListener(new OnClick());
        llMine.setOnClickListener(new OnClick());
        llSend.setOnClickListener(new OnClick());
        llOrder.setOnClickListener(new OnClick());
        llChat.setOnClickListener(new OnClick());
        /*mBtnFind.setOnClickListener(new OnClick());
        mBtnSend.setOnClickListener(new OnClick());*/
        InitView();
        InitData();
        InitViewOper();

    }




    @Override
    public void InitView() {
        ViewUtils.inject(this);
        homeAct = this;
    }

    @Override
    public void InitData() {

    }

    @Override
    public void InitViewOper() {

        int id = getIntent().getIntExtra("id", 0);
        if (id == 1){
            ContactListFragment contactListFragment = new ContactListFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.act_home_viewpager,contactListFragment).addToBackStack(null).commitAllowingStateLoss();
        }

    }



    class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //设计更换点击出现不同界面
            switch (v.getId()) {
                case R.id.act_home_orderLinearLayout:
                    /*if (orderFragment == null){*/
                        /*orderFragment = new OrderFragment();*/
                    /*}else {
                        break;
                    }*/
                    Fragment fragment01 = getSupportFragmentManager().findFragmentByTag("a");
                    /*if (fragment01 != null){
                        getSupportFragmentManager().beginTransaction().hide(fragment01).add(R.id.act_home_viewpager,orderFragment).addToBackStack(null).commitAllowingStateLoss();
                    } else {*/
                    getSupportFragmentManager().beginTransaction().replace(R.id.act_home_viewpager, orderFragment).addToBackStack(null).commitAllowingStateLoss();
                    //}
                    break;

                case R.id.act_home_sendLinearLayout:
                    /*if (sendFragment == null){
                        sendFragment = new SendFragment();
                    }*//*else {
                        break;
                    }*/
                    /*Fragment fragment02 = getSupportFragmentManager().findFragmentByTag("a");*/
                    /*if (fragment02 != null){
                        getSupportFragmentManager().beginTransaction().hide(fragment02).add(R.id.act_home_viewpager,sendFragment).addToBackStack(null).commitAllowingStateLoss();
                    } else {*/
                    getSupportFragmentManager().beginTransaction().replace(R.id.act_home_viewpager, sendFragment).addToBackStack(null).commitAllowingStateLoss();
                    //}
                    break;
                case R.id.act_home_mineLinearLayout:
                    /*if (mineFragment == null){
                        mineFragment = new MineFragment();
                    }else {
                        break;
                    }*/
                    /*Fragment fragment03 = getSupportFragmentManager().findFragmentByTag("a");*/
                    /*if (fragment03 != null){
                        getSupportFragmentManager().beginTransaction().hide(fragment03).add(R.id.act_home_viewpager,mineFragment).addToBackStack(null).commitAllowingStateLoss();
                    } else {*/
                    getSupportFragmentManager().beginTransaction().replace(R.id.act_home_viewpager, mineFragment).addToBackStack(null).commitAllowingStateLoss();
                    //}
                    break;
                case R.id.act_home_homepageLinearLayout:
                    /*if (selectFragment02 == null){
                       selectFragment02 = new SelectFragment();
                    }else {
                        break;
                    }
                    Fragment fragment04 = getSupportFragmentManager().findFragmentByTag("a");
                    if (fragment04 != null){
                        getSupportFragmentManager().beginTransaction().hide(fragment04).add(R.id.act_home_viewpager,selectFragment02).addToBackStack(null).commitAllowingStateLoss();
                    } else {*/
                    getSupportFragmentManager().beginTransaction().replace(R.id.act_home_viewpager, selectFragment02).addToBackStack(null).commitAllowingStateLoss();
                    //}
                    break;
                case R.id.act_home_chatLinearLayout:
                    getSupportFragmentManager().beginTransaction().replace(R.id.act_home_viewpager,chat01Fragment).addToBackStack(null).commitAllowingStateLoss();
                    break;
                /*case R.id.btn_sfind:
                    getSupportFragmentManager().beginTransaction().replace(R.id.act_home_viewpager,orderFragment02).addToBackStack(null).commitAllowingStateLoss();

                    break;

                case R.id.btn_ssend:
                    getSupportFragmentManager().beginTransaction().replace(R.id.act_home_viewpager,sendFragment02).addToBackStack(null).commitAllowingStateLoss();
                    break;*/
                /*case R.id.btn_ssend:
                    getSupportFragmentManager().beginTransaction().replace(R.id.act_home_viewpager,sendFragment).addToBackStack(null).commitAllowingStateLoss();

                    break;*/
            }
        }
    }
}
