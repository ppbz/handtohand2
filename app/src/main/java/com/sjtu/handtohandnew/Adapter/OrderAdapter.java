package com.sjtu.handtohandnew.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjtu.handtohandnew.Activity.OrderDetailActivity;
import com.sjtu.handtohandnew.Bean.OrderBean;
import com.sjtu.handtohandnew.R;

import java.util.LinkedList;

public class OrderAdapter extends BaseAdapter {
    private LinkedList<OrderBean> mOrder;
    private Context mContext;
    private LayoutInflater inflater;

    public OrderAdapter(LinkedList<OrderBean> mOrder, Context mContext) {
        this.mOrder = mOrder;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mOrder.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_order, null);
            viewHolder = new ViewHolder();
            viewHolder.orderPicture = convertView.findViewById(R.id.imgItem2);
            viewHolder.orderName = convertView.findViewById(R.id.shopNameItem02);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (OrderAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.orderPicture.setBackgroundResource(mOrder.get(position).getOrderIcon());
        viewHolder.orderName.setText(mOrder.get(position).getOrderName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, OrderDetailActivity.class));
            }
        });

        return convertView;
    }

    static class ViewHolder{
        public TextView orderName;
        public ImageView orderPicture;
    }
}
