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
import com.sjtu.handtohandnew.Bean.AnimalBean;
import com.sjtu.handtohandnew.R;

import java.util.LinkedList;

public class TestAdapter extends BaseAdapter {
    private LinkedList<AnimalBean> mAnimal;
    private Context mContext;
    private LayoutInflater inflater;

    public TestAdapter(LinkedList<AnimalBean> mAnimal, Context mContext) {
        this.mAnimal = mAnimal;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    static class ViewHolder{
        public TextView tvName,tvSpeak;
        public ImageView imageView;
    }

    @Override
    public int getCount() {
        return mAnimal.size();
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
       /* ImageView imageView = new ImageView();
        TextView text_aName =
        //这段话的含义：*/
       ViewHolder holder = null;
       if (convertView == null) {
           convertView = inflater.inflate(R.layout.item_test, null);
           holder = new ViewHolder();
           holder.imageView = convertView.findViewById(R.id.imgtou);
           holder.tvName = convertView.findViewById(R.id.name);
           holder.tvSpeak = convertView.findViewById(R.id.says);
           convertView.setTag(holder);
           //绑定item
        /*ImageView imageView = convertView.findViewById(R.id.imgtou);
        TextView text_aName = convertView.findViewById(R.id.name);
        TextView text_aSpeak = convertView.findViewById(R.id.says);*/
           //绑定内容
        /*imageView.setBackgroundResource(mData.get(position).getaIcon());
        text_aName.setText(mData.get(position).getaName());
        text_aSpeak.setText(mData.get(position).getaSpeak());*/
       }else{
           holder = (ViewHolder) convertView.getTag();
       }
       holder.imageView.setBackgroundResource(mAnimal.get(position).getaIcon());
       holder.tvName.setText(mAnimal.get(position).getaName());
       holder.tvSpeak.setText(mAnimal.get(position).getaSpeak());
       convertView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mContext.startActivity(new Intent(mContext, OrderDetailActivity.class));
           }
       });

       return convertView;
    }
}
