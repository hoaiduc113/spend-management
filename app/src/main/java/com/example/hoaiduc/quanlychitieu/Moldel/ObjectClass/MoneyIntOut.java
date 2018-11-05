package com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass;

/**
 * Created by hoaiduc on 4/7/2018.
 */

public class MoneyIntOut
{
    private String createDay;
    private int  moneyInt;
    private int moneyOut;
    private int walletid;
    private int moneyIntOutId;
    private int group;

    public int getGroup()
    {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public MoneyIntOut()
    {

    }
    public MoneyIntOut(String createDay, int moneyInt, int moneyOut, int walletid, int moneyIntOutId) {
        this.createDay = createDay;
        this.moneyInt = moneyInt;
        this.moneyOut = moneyOut;
        this.walletid = walletid;
        this.moneyIntOutId = moneyIntOutId;
    }

    public String getCreateDay() {
        return createDay;
    }

    public void setCreateDay(String createDay) {
        this.createDay = createDay;
    }

    public int getMoneyInt() {
        return moneyInt;
    }

    public void setMoneyInt(int moneyInt) {
        this.moneyInt = moneyInt;
    }

    public int getMoneyOut() {
        return moneyOut;
    }

    public void setMoneyOut(int moneyOut) {
        this.moneyOut = moneyOut;
    }

    public int getWalletid() {
        return walletid;
    }

    public void setWalletid(int walletid) {
        this.walletid = walletid;
    }

    public int getMoneyIntOutId() {
        return moneyIntOutId;
    }

    public void setMoneyIntOutId(int moneyIntOutId) {
        this.moneyIntOutId = moneyIntOutId;
    }
}
