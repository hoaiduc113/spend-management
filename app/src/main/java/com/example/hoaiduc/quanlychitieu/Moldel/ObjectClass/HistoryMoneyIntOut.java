package com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass;

/**
 * Created by hoaiduc on 4/17/2018.
 */

public class HistoryMoneyIntOut
{
    private String name;
    private String createday;
    private int moneyInput;
    private int moneyOutput;
    private int totalMoney;
    private int money;
    private String cursymbol;
    private String image;
    private int walletmoney;
    private String note;

    public int getWalletmoney() {
        return walletmoney;
    }

    public void setWalletmoney(int walletmoney) {
        this.walletmoney = walletmoney;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCursymbol() {
        return cursymbol;
    }

    public void setCursymbol(String cursymbol) {
        this.cursymbol = cursymbol;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateday() {
        return createday;
    }

    public void setCreateday(String createday) {
        this.createday = createday;
    }

    public int getMoneyInput() {
        return moneyInput;
    }

    public void setMoneyInput(int moneyInput) {
        this.moneyInput = moneyInput;
    }

    public int getMoneyOutput() {
        return moneyOutput;
    }

    public void setMoneyOutput(int moneyOutput) {
        this.moneyOutput = moneyOutput;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
