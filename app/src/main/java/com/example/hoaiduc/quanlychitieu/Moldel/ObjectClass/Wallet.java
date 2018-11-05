package com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass;

/**
 * Created by hoaiduc on 2/20/2018.
 */

public class Wallet
{
    private int walletid,userid,money;
    private String name;
    private String createday,modifydate;
    private int curid;
    private int success;

    public int getSuccess()
    {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getWalletid()


    {
        return walletid;
    }

    public void setWalletid(int walletid) {
        this.walletid = walletid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCreateday() {
        return createday;
    }

    public void setCreateday(String createday) {
        this.createday = createday;
    }

    public String getModifydate() {
        return modifydate;
    }

    public void setModifydate(String modifydate) {
        this.modifydate = modifydate;
    }

    public int getCurid() {
        return curid;
    }

    public void setCurid(int curid) {
        this.curid = curid;
    }
}
