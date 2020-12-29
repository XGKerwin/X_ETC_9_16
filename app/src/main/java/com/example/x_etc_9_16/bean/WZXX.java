package com.example.x_etc_9_16.bean;

public class WZXX {

    /**
     *             "infoid": "10101",
     *             "road": "学院路",
     *             "message": "在交叉路口不按导向标线行驶在相应车道。",
     *             "deduct": 1,
     *             "fine": 0
     */

    private String infoid,road,message;
    private int deduct,fine;

    public String getInfoid() {
        return infoid;
    }

    public void setInfoid(String infoid) {
        this.infoid = infoid;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDeduct() {
        return deduct;
    }

    public void setDeduct(int deduct) {
        this.deduct = deduct;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }
}
