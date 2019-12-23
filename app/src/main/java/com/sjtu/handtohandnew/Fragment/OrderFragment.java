package com.sjtu.handtohandnew.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sjtu.handtohandnew.Adapter.OrderAdapter;
import com.sjtu.handtohandnew.Adapter.SendOrderAdapter;
import com.sjtu.handtohandnew.Base.BaseFragment;
import com.sjtu.handtohandnew.Base.BaseInterface;
import com.sjtu.handtohandnew.Bean.OrderBean;
import com.sjtu.handtohandnew.R;

import java.util.LinkedList;

public class OrderFragment extends BaseFragment implements BaseInterface {
    private ListView list_send_order;
    private SendOrderAdapter orderAdapter = null;
    private LinkedList<OrderBean> mOrder = null;


    @Override
    protected void init() {

    }

    @Override
    protected View initContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_order,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list_send_order = view.findViewById(R.id.listView_Order);
        mOrder = new LinkedList<OrderBean>();
        mOrder.add(new OrderBean("吉祥馄饨",R.drawable.huntun));
        orderAdapter = new SendOrderAdapter(mOrder,activity);
        list_send_order.setAdapter(orderAdapter);
    }

    @Override
    public void InitView() {

    }

    @Override
    public void InitData() {

    }

    @Override
    public void InitViewOper() {

    }
}
