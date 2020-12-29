package com.example.x_etc_9_16.bean;

import org.litepal.crud.LitePalSupport;

public class CZJL extends LitePalSupport {

    private String time1,czr,cph,chongzhitime;
    private int chongzhi,yue;

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getCzr() {
        return czr;
    }

    public void setCzr(String czr) {
        this.czr = czr;
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public String getChongzhitime() {
        return chongzhitime;
    }

    public void setChongzhitime(String chongzhitime) {
        this.chongzhitime = chongzhitime;
    }

    public int getChongzhi() {
        return chongzhi;
    }

    public void setChongzhi(int chongzhi) {
        this.chongzhi = chongzhi;
    }

    public int getYue() {
        return yue;
    }

    public void setYue(int yue) {
        this.yue = yue;
    }
}
