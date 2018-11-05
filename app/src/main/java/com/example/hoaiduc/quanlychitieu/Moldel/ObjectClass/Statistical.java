package com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass;

/**
 * Created by hoaiduc on 4/19/2018.
 */

public class Statistical
{
    private String curSymbol;
    private int money;
    private int group;
    private int userid;
    private int yeard;
    private int firstMonth;
    private int endMonth;
    private String creteday;

    public String getCurSymbol() {
        return curSymbol;
    }

    public void setCurSymbol(String curSymbol) {
        this.curSymbol = curSymbol;
    }

    public Statistical() {
    }

    public String getCreteday() {
        return creteday;
    }

    public void setCreteday(String creteday) {
        this.creteday = creteday;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getYeard() {
        return yeard;
    }

    public void setYeard(int yeard) {
        this.yeard = yeard;
    }

    public int getFirstMonth() {
        return firstMonth;
    }

    public void setFirstMonth(int firstMonth) {
        this.firstMonth = firstMonth;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }
}
