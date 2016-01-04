package com.mm.bill;

import java.util.ArrayList;

public class BillData {

    private int mTotalNum; // 总人数

    private float mTotalCost; // 总花费

    private float mTotalPay; // 总出资

    private float mEachFinalCost; // 个人总花费

    private float mEachFinalPay; // 个人总出资

    private float mEach; // 个人结算

    private ArrayList<String> mPerson;
    
    private ArrayList<BaseBillData> mEachBill; 

    public ArrayList<BaseBillData> getEachBill() {
        return mEachBill;
    }

    public void setmEachBill(ArrayList<BaseBillData> eachBill) {
        this.mEachBill = eachBill;
    }

    public int getTotalNum() {
        return mTotalNum;
    }

    public void setTotalNum(int totalNum) {
        this.mTotalNum = totalNum;
    }

    public float getTotalCost() {
        return mTotalCost;
    }

    public void setTotalCost(float totalCost) {
        this.mTotalCost = totalCost;
    }

    public float getTotalPay() {
        return mTotalPay;
    }

    public void setTotalPay(float totalPay) {
        this.mTotalPay = totalPay;
    }

    public float getEachFinalCost() {
        return mEachFinalCost;
    }

    public void setEachFinalCost(float eachFinalCost) {
        this.mEachFinalCost = eachFinalCost;
    }

    public float getEachFinalPay() {
        return mEachFinalPay;
    }

    public void setEachFinalPay(float eachFinalPay) {
        this.mEachFinalPay = eachFinalPay;
    }

    public float getEach() {
        return mEach;
    }

    public void setEach(float each) {
        this.mEach = each;
    }

    public ArrayList<String> getPerson() {
        return mPerson;
    }

    public void setPerson(ArrayList<String> person) {
        this.mPerson = person;
    }
}
