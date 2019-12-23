package com.sjtu.handtohandnew.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.exceptions.HyphenateException;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sjtu.handtohandnew.Adapter.CommodityAdapter;
import com.sjtu.handtohandnew.Base.BaseActivity;
import com.sjtu.handtohandnew.Base.BaseInterface;
import com.sjtu.handtohandnew.Base.Model;
import com.sjtu.handtohandnew.Bean.CommodityBean;
import com.sjtu.handtohandnew.Bean.UserInfoBean;
import com.sjtu.handtohandnew.Fragment.ContactListFragment;
import com.sjtu.handtohandnew.R;

import java.util.LinkedList;

public class OrderDetailActivity extends BaseActivity implements BaseInterface {
    private ListView list_orderDetail;
    private LinkedList<CommodityBean> mCommodity = null;
    private CommodityAdapter commodityAdapter = null;

    /*private TextView textViewOrderSendName;*/


    @ViewInject(R.id.orderDetail)
    public static LinearLayout linearLayoutOrderDetail;

    @OnClick(R.id.orderConnect)
    private void connect(View view){




        TextView textViewOrderSendName = findViewById(R.id.OrderSendName);
        String name = textViewOrderSendName.getText().toString();
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                UserInfoBean userInfo = new UserInfoBean(name);

                try {
                    EMClient.getInstance().contactManager().addContact(userInfo.getName(),"添加好友");

                    /*runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(OrderDetailActivity.this,"添加好友成功",Toast.LENGTH_SHORT).show();

                        }
                    });*/
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    /*runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(OrderDetailActivity.this, "添加好友失败" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });*/
                }
            }
        });
        /*startAct(ConnectActivity.class);*/
        /*startAct(HomeActivity.class);*/
//        Intent intent = new Intent(OrderDetailActivity.this,HomeActivity.class);
//        intent.putExtra("id",1);
//        startActivity(intent);

        Intent intent = new Intent( OrderDetailActivity.this, ChatActivity.class );

        //传递参数
        intent.putExtra( "userId", name );
        startActivity( intent );
        Log.d("dsfdsf","----------------------------------------------");


    }

    @OnClick(R.id.orderMap)
    private void  showMap(View view){
        startAct(MapTestActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        /*textViewOrderSendName = findViewById(R.id.OrderSendName);*/
        InitView();
        InitData();
        InitViewOper();
    }


    @Override
    public void InitView() {
        ViewUtils.inject(this);

    }

    @Override
    public void InitData() {
        list_orderDetail = findViewById(R.id.orderDetailListView);
        mCommodity = new LinkedList<CommodityBean>();
        mCommodity.add(new CommodityBean("大葱馄饨","1","15",R.drawable.dacong));
        mCommodity.add(new CommodityBean("全家福餐","1","17",R.drawable.quanjiafu));
        mCommodity.add(new CommodityBean("蟹黄鲜肉","1","15",R.drawable.danhuangxianrou));
        mCommodity.add(new CommodityBean("蛋黄烧麦","1","15",R.drawable.shaomai));
        commodityAdapter = new CommodityAdapter(mCommodity,this);
        list_orderDetail.setAdapter(commodityAdapter);

    }

    @Override
    public void InitViewOper() {

    }
}
