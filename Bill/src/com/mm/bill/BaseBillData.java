package com.mm.bill;

import java.util.ArrayList;

public class BaseBillData {

    private float mEachTotalCost; // 单项总花费

    private float mEachAveCost; // 单项平均花费

    private String mCostItem; // 单项花费名目

    private ArrayList<String> mPayPerson; // 单项出资人

    private ArrayList<String> mCostPerson; // 单项花费人

    public float getEachTotalCost() {
        return mEachTotalCost;
    }

    public void setEachTotalCost(float eachTotalCost) {
        this.mEachTotalCost = eachTotalCost;
    }

    public float getEachAveCost() {
        return mEachAveCost;
    }

    public void setEachAveCost(float eachAveCost) {
        this.mEachAveCost = eachAveCost;
    }

    public String getCostItem() {
        return mCostItem;
    }

    public void setCostItem(String costItem) {
        this.mCostItem = costItem;
    }

    public ArrayList<String> getPayPerson() {
        return mPayPerson;
    }

    public void setPayPerson(ArrayList<String> payPerson) {
        this.mPayPerson = payPerson;
    }

    public ArrayList<String> getCostPerson() {
        return mCostPerson;
    }

    public void setCostPerson(ArrayList<String> costPerson) {
        this.mCostPerson = costPerson;
    }

}
