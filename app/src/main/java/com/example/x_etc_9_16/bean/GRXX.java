package com.example.x_etc_9_16.bean;

import java.util.List;

public class GRXX {


    /**
     *     "RESULT": "S",
     *     "id": 1,
     *     "name": "张三",
     *     "sex": "男",
     *     "idnumber": "385622201809083326",
     *     "registered_time": "1998.01.26",
     *     "tel": "15625895632",
     *     "username": "user1",
     *     "root": "管理员",
     *     "plate": [
     *         "鲁A10001",
     *         "鲁A10003",
     *         "鲁A10004"
     *     ]
     */

    private String id,name,sex,idnumber,registered_time,tel,username,root;
    private List<String> plate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getRegistered_time() {
        return registered_time;
    }

    public void setRegistered_time(String registered_time) {
        this.registered_time = registered_time;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public List<String> getPlate() {
        return plate;
    }

    public void setPlate(List<String> plate) {
        this.plate = plate;
    }
}
