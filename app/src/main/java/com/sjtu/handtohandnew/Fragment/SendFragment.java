package com.sjtu.handtohandnew.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sjtu.handtohandnew.Adapter.OrderAdapter;
import com.sjtu.handtohandnew.Adapter.TestAdapter;
import com.sjtu.handtohandnew.Base.BaseFragment;
import com.sjtu.handtohandnew.Base.BaseInterface;
import com.sjtu.handtohandnew.Bean.AnimalBean;
import com.sjtu.handtohandnew.Bean.OrderBean;
import com.sjtu.handtohandnew.R;

import java.util.LinkedList;

public class SendFragment extends BaseFragment implements BaseInterface {

    private LinkedList<AnimalBean> mData = null;
    private Context context;
    private TestAdapter mAdapter = null;
    private ListView list_animal;
    private ListView list_order;
    private OrderAdapter orderAdapter = null;
    private LinkedList<OrderBean> mOrder = null;

    @Override
    protected void init() {

    }

    @Override
    protected View initContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_send,null);
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


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*list_animal = view.findViewById(R.id.listView_send);
        mData = new LinkedList<AnimalBean>();
        mData.add(new AnimalBean("狗说", "你是狗么?", R.drawable.new_icon));
        mData.add(new AnimalBean("牛说", "你是牛么?", R.drawable.search_icon));
        mData.add(new AnimalBean("鸭说", "你是鸭么?", R.drawable.shopicon));
        mData.add(new AnimalBean("鱼说", "你是鱼么?", R.drawable.order_usericon));
        mData.add(new AnimalBean("马说", "你是马么?", R.drawable.appicon));
        mAdapter = new TestAdapter(mData, activity);
        list_animal.setAdapter(mAdapter);*/

        list_order = view.findViewById(R.id.listView_send);
        mOrder = new LinkedList<OrderBean>();
        mOrder.add(new OrderBean("吉祥馄饨",R.drawable.huntun));
        orderAdapter = new OrderAdapter(mOrder,activity);
        list_order.setAdapter(orderAdapter);
    }
}
