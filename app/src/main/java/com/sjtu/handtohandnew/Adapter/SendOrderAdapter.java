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
import com.sjtu.handtohandnew.Activity.SendOrderDetailActivity;
import com.sjtu.handtohandnew.Bean.OrderBean;
import com.sjtu.handtohandnew.R;

import java.util.LinkedList;

public class SendOrderAdapter extends BaseAdapter {
    private LinkedList<OrderBean> mOrder;
    private Context mContext;
    private LayoutInflater inflater;
    @Override
    public int getCount() {
        return mOrder.size();
    }

    public SendOrderAdapter(LinkedList<OrderBean> mOrder, Context mContext) {
        this.mOrder = mOrder;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    static class ViewHolder{
        public TextView sendOrderName;
        public ImageView sendOrderPicture;
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_order, null);
            viewHolder = new ViewHolder();
            viewHolder.sendOrderPicture = convertView.findViewById(R.id.imgItem2);
            viewHolder.sendOrderName = convertView.findViewById(R.id.shopNameItem02);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.sendOrderPicture.setBackgroundResource(mOrder.get(position).getOrderIcon());
        viewHolder.sendOrderName.setText(mOrder.get(position).getOrderName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, SendOrderDetailActivity.class));
            }
        });

        return convertView;
    }
}
