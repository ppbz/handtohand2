package com.sjtu.handtohandnew.Activity;

import android.view.View;
import android.widget.LinearLayout;
import android.os.Bundle;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sjtu.handtohandnew.Base.BaseActivity;
import com.sjtu.handtohandnew.Base.BaseInterface;
import com.sjtu.handtohandnew.R;

public class ConnectActivity extends BaseActivity implements BaseInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        InitView();
        InitData();
        InitViewOper();
    }


    @Override
    public void InitView() {
        /*ViewUtils.inject(this);*/
    }

    @Override
    public void InitData() {

    }

    @Override
    public void InitViewOper() {

    }
}
