package com.rm.easy.ro.roastorder.jsonBean;

/**
 * Created by Easy.D on 2016/11/6.
 */
public class Data {


    //class

    private int classId;
    private String country;
    private String farm;
    private String classes;
    private int classRemarks;

    //order
    private int orderId;
    private int cId;
    private String weight;
    private int flag;
    private int orderRemarks;


    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public int getClassRemarks() {
        return classRemarks;
    }

    public void setClassRemarks(int classRemarks) {
        this.classRemarks = classRemarks;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getOrderRemarks() {
        return orderRemarks;
    }

    public void setOrderRemarks(int orderRemarks) {
        this.orderRemarks = orderRemarks;
    }
}

