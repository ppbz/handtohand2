package com.sjtu.handtohandnew.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sjtu.handtohandnew.Bean.UserInfoBean;
import com.sjtu.handtohandnew.Db.DBHelper;

import java.util.ArrayList;
import java.util.List;

//联系人表的操作类
public class ContactTableDao {
    private DBHelper mHelper;

    public ContactTableDao(DBHelper helper) {
        mHelper = helper;
    }

    //获取所有联系人
    public List<UserInfoBean> getContacts() {
        //获取数据库连接
        SQLiteDatabase db = mHelper.getReadableDatabase();

        //执行查询语句
        String sql = "select * from " + ContactTable.TAB_NAME + " where " + ContactTable.COL_IS_CONTACT + "=1";
        Cursor cursor = db.rawQuery(sql, null);

        List<UserInfoBean> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            UserInfoBean userInfo = new UserInfoBean();
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(ContactTable.COL_HXID)));
            userInfo.setName(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NAME)));
            userInfo.setNick(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NICK)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(ContactTable.COL_PHOTO)));

            users.add(userInfo);
        }
        //关闭资源
        cursor.close();
        //返回数据
        return users;
    }

    //通过环信Id获取联系人单个信息
    public UserInfoBean getContactByHx(String hxId) {
        if (hxId == null) {
            return null;
        }
        //获取数据库连接
        SQLiteDatabase db = mHelper.getReadableDatabase();
        //执行查询语句
        String sql = "select * from " + ContactTable.TAB_NAME + " where " + ContactTable.COL_HXID + "=?";
        Cursor cursor = db.rawQuery(sql,new String[]{hxId});

        UserInfoBean userInfo = null;
        if (cursor.moveToNext()) {

            userInfo = new UserInfoBean();
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(ContactTable.COL_HXID)));
            userInfo.setName(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NAME)));
            userInfo.setNick(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NICK)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(ContactTable.COL_PHOTO)));
        }
        //关闭资源
        cursor.close();
        //返回数据
        return userInfo;
    }

    //通过环信Id获取用户联系人信息
    public List<UserInfoBean> getContactByHx(List<String> hxIds) {
        if (hxIds == null || hxIds.size() <= 0) {
            return null;
        }

        List<UserInfoBean> contacts = new ArrayList<>();
        //遍历hxIds,来查找
        for (String hxid : hxIds) {
            UserInfoBean contact = getContactByHx(hxid);

            contacts.add(contact);
        }
        return contacts;  //返回查询的数据
    }

    //保存单个联系人
    public void saveContact(UserInfoBean user, boolean isMyContact) {
        if (user == null) {
            return;
        }

        //获取数据库连接
        SQLiteDatabase db = mHelper.getReadableDatabase();

        //执行保存语句
        ContentValues values = new ContentValues();
        values.put(ContactTable.COL_HXID, user.getHxid());
        values.put(ContactTable.COL_NAME, user.getName());
        values.put(ContactTable.COL_NICK, user.getNick());
        values.put(ContactTable.COL_PHOTO, user.getPhoto());
        values.put(ContactTable.COL_IS_CONTACT, isMyContact ? 1 : 0);
        db.replace(ContactTable.TAB_NAME, null, values);

    }

    //保存联系人信息
    public void saveContacts(List<UserInfoBean> contacts, boolean isMyContact) {
        if (contacts == null || contacts.size() <= 0) {
            return;
        }
        for (UserInfoBean contact : contacts) {
            saveContact(contact, isMyContact);
        }
    }

    //删除联系人信息
    public void deleteContactByHxId(String hxId) {
        if (hxId == null) {
            return;
        }
        SQLiteDatabase db = mHelper.getReadableDatabase();
        db.delete(ContactTable.TAB_NAME, ContactTable.COL_HXID + "=?", new String[]{hxId});

    }
}
