package com.sjtu.handtohandnew.Activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sjtu.handtohandnew.Base.BaseActivity;
import com.sjtu.handtohandnew.Base.BaseInterface;
import com.sjtu.handtohandnew.Base.Model;
import com.sjtu.handtohandnew.R;

public class OrderDetailActivity extends BaseActivity implements BaseInterface {
    private TextView textViewOrderSendName;

    @ViewInject(R.id.orderDetail)
    public static LinearLayout linearLayoutOrderDetail;

    @OnClick(R.id.orderConnect)
    private void connect(View view){
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().contactManager().addContact(textViewOrderSendName.getText().toString(),"添加好友");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(OrderDetailActivity.this,"添加好友成功",Toast.LENGTH_SHORT).show();

                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(OrderDetailActivity.this, "添加好友失败" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        startAct(ConnectActivity.class);
    }

    @OnClick(R.id.orderMap)
    private void  showMap(View view){
        startAct(MapActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        textViewOrderSendName = findViewById(R.id.OrderSendName);
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

    }

    @Override
    public void InitViewOper() {

    }
}
