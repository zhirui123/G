package com.huagongwuliu.waybillelectronic.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "app_user_info")
@Data
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    // 用户名
    private String roleName;
    private String password;
    private String phone;
    private String addTime;


    private String isAuthentication;
    private String recommenderId;
    private String wechatOpenId;


//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public Date getCreated() {
//        return created;
//    }
//
//    public void setCreated(Date created) {
//        this.created = created;
//    }
//
//    public String getSalt() {
//        return salt;
//    }
//
//    public void setSalt(String salt) {
//        this.salt = salt;
//    }
//
//    public String getUserTime() {
//        return userTime;
//    }
//
//    public void setUserTime(String userTime) {
//        this.userTime = userTime;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", phone='" + phone + '\'' +
//                ", created=" + created +
//                ", salt='" + salt + '\'' +
//                ", userTime='" + userTime + '\'' +
//                '}';
//    }
}
