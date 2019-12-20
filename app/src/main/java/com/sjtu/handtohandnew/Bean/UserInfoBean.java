package com.sjtu.handtohandnew.Bean;
//账号信息
public class UserInfoBean {
    private String name;
    private String hxid;
    private String nick; //昵称
    private String photo;//头像

    public UserInfoBean() {
    }

    public UserInfoBean(String name) {
        this.name = name;
        this.hxid = name;
        this.nick = name;
        //this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHxid() {
        return hxid;
    }

    public void setHxid(String hxid) {
        this.hxid = hxid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "name='" + name + '\'' +
                ", hxid='" + hxid + '\'' +
                ", nick='" + nick + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
