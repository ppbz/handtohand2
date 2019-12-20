package com.sjtu.handtohandnew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sjtu.handtohandnew.Bean.CommodityBean;
import com.sjtu.handtohandnew.R;

import java.util.LinkedList;

public class CommodityAdapter extends BaseAdapter {
    private LinkedList<CommodityBean> mCommodity;
    private Context mContext;
    private LayoutInflater inflater;

    public CommodityAdapter(LinkedList<CommodityBean> mCommodity, Context mContext) {
        this.mCommodity = mCommodity;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mCommodity.size();
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
            convertView = inflater.inflate(R.layout.item_commodity, null);
            viewHolder = new ViewHolder();
            viewHolder.commodityPicture = convertView.findViewById(R.id.imgItem3);
            viewHolder.commodityMoney = convertView.findViewById(R.id.moneySingleItem03);
            viewHolder.commodityName = convertView.findViewById(R.id.foodNameItem03);
            viewHolder.commodityNumber = convertView.findViewById(R.id.numberItem03);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (CommodityAdapter.ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    static class ViewHolder{
        public TextView commodityName,commodityNumber,commodityMoney;
        public ImageView commodityPicture;
    }
}
