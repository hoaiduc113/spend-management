package com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass;

import java.io.Serializable;

/**
 * Created by hoaiduc on 2/20/2018.
 */

public class ObjectIntend implements Serializable
{
    private int curid,userid;

    public int getCurid() {
        return curid;
    }

    public void setCurid(int curid) {
        this.curid = curid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
