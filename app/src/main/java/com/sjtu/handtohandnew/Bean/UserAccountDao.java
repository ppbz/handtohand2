package com.sjtu.handtohandnew.Bean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tencent.connect.UserInfo;

//用户账号数据库的操作类
public class UserAccountDao {
    private final UserAccountDB mHelper;
    public UserAccountDao(Context context) {
        mHelper = new UserAccountDB(context);
    }
    //添加用戶到數據庫
    public void addAccount(UserInfoBean user){
        //获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();

        //执行添加操作
        ContentValues values = new ContentValues();
        values.put(UserAccountTable.COL_HXID,user.getHxid());
        values.put(UserAccountTable.COL_NAME,user.getName());
        values.put(UserAccountTable.COL_NICK,user.getNick());
        values.put(UserAccountTable.COL_PHOTO,user.getPhoto());
        db.replace(UserAccountTable.TAB_NAME,null,values);
    }

    //根据环信id获取所有用户信息
    public UserInfoBean getAccountByHxID(String hxID){
        //获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();
        //执行查询语句
        String sql = "select * from "+UserAccountTable.TAB_NAME+" where "+UserAccountTable.COL_HXID+ "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{hxID});
        UserInfoBean userInfoBean = null;
        if (cursor.moveToNext()){
            userInfoBean = new UserInfoBean();

            userInfoBean.setHxid(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_HXID)));
            userInfoBean.setName(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NAME)));
            userInfoBean.setNick(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NICK)));
            userInfoBean.setPhoto(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_PHOTO)));
        }
        //关闭资源
        cursor.close();
        //返回数据
        return userInfoBean;
    }
}
