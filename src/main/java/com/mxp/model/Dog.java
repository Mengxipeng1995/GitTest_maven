package com.mxp.model;

/**
 * Created by mxp on 2017/6/22.
 */
public class Dog {
    private String dname;
    private String dnumber;

    public Dog(String dname, String dnumber) {
        this.dname = dname;
        this.dnumber = dnumber;
    }

    public Dog() {
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDnumber() {
        return dnumber;
    }

    public void setDnumber(String dnumber) {
        this.dnumber = dnumber;
    }
}
