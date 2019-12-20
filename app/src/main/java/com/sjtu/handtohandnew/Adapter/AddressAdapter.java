package com.sjtu.handtohandnew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sjtu.handtohandnew.Bean.AddressBean;
import com.sjtu.handtohandnew.R;

import java.util.LinkedList;

public class AddressAdapter extends BaseAdapter {
    private LinkedList<AddressBean> mAddress;
    private Context mContext;
    private LayoutInflater inflater;

    public AddressAdapter(LinkedList<AddressBean> mAddress, Context mContext) {
        this.mAddress = mAddress;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    static class ViewHolder{
        public TextView addressName,addressNumber,addressSex,address;
    }

    @Override
    public int getCount() {
        return mAddress.size();
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
            convertView = inflater.inflate(R.layout.item_address, null);
            viewHolder = new ViewHolder();
            viewHolder.addressName = convertView.findViewById(R.id.item_address_nameTv);
            viewHolder.addressNumber= convertView.findViewById(R.id.item_address_phoneTv);
            viewHolder.addressSex= convertView.findViewById(R.id.item_address_sexTv);
            viewHolder.address= convertView.findViewById(R.id.item_address_addressTv);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }
}
