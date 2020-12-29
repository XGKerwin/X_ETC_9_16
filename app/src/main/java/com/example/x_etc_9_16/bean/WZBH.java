package com.example.x_etc_9_16.bean;

import java.util.List;

public class WZBH {

    /**
     *     "id": [
     *         1,
     *         7
     *     ],
     */

    private List<String> id;
    private String cph;
    private int wcl;
    private int koufen,fakuan;
    private String time;
    private String daolu;
    private String msg;

    public int getWcl() {
        return wcl;
    }

    public void setWcl(int wcl) {
        this.wcl = wcl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDaolu() {
        return daolu;
    }

    public void setDaolu(String daolu) {
        this.daolu = daolu;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public int getKoufen() {
        return koufen;
    }

    public void setKoufen(int koufen) {
        this.koufen = koufen;
    }

    public int getFakuan() {
        return fakuan;
    }

    public void setFakuan(int fakuan) {
        this.fakuan = fakuan;
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }
}
