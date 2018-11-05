package com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass;

/**
 * Created by hoaiduc on 2/18/2018.
 */

public class Currency
{
    private int success,curid;
    private String curcode,curname,cursymbol,image;

    public int getCurid() {
        return curid;
    }

    public void setCurid(int curid) {
        this.curid = curid;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getCurcode() {
        return curcode;
    }

    public void setCurcode(String curcode) {
        this.curcode = curcode;
    }

    public String getCurname() {
        return curname;
    }

    public void setCurname(String curname) {
        this.curname = curname;
    }

    public String getCursymbol() {
        return cursymbol;
    }

    public void setCursymbol(String cursymbol) {
        this.cursymbol = cursymbol;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
