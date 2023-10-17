package com.nhom1.qlbtl.model;

public class DeTai {
    private String maMonHoc;
    private String tenMonHoc;
    private String maBTL;
    private String tenBTL;
    private String namHoc;

    public DeTai(){

    }


    public DeTai(String maBTL, String tenBTL, String maMH, String tenMH, String namHoc) {
        this.maMonHoc = maMH;
        this.tenMonHoc = tenMH;
        this.maBTL = maBTL;
        this.tenBTL = tenBTL;
        this.namHoc = namHoc;
    }
    public DeTai(String maMonHoc, String maBTL, String tenBTL, String namHoc) {
        this.maMonHoc = maMonHoc;
        this.maBTL = maBTL;
        this.tenBTL = tenBTL;
        this.namHoc = namHoc;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }




    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getMaBTL() {
        return maBTL;
    }

    public void setMaBTL(String maBTL) {
        this.maBTL = maBTL;
    }

    public String getTenBTL() {
        return tenBTL;
    }

    public void setTenBTL(String tenBTL) {
        this.tenBTL = tenBTL;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }
}