package com.nhom1.qlbtl.model;

public class LopHoc {
    private String maLop;
    private String tenLop;
    private String loptruong;
    private int soNhom;
    private int soLuongSV;
    private String maMH;

    public LopHoc() {
    }

    public LopHoc(String tenLop, String loptruong, int soNhom, int soLuongSV) {
        this.tenLop = tenLop;
        this.loptruong = loptruong;
        this.soNhom = soNhom;
        this.soLuongSV = soLuongSV;
    }
    public LopHoc(String maLop, String tenLop, String loptruong, int soNhom, int soLuongSV, String maMH) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.loptruong = loptruong;
        this.soNhom = soNhom;
        this.soLuongSV = soLuongSV;
        this.maMH = maMH;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getLoptruong() {
        return loptruong;
    }

    public void setLoptruong(String loptruong) {
        this.loptruong = loptruong;
    }

    public int getSoNhom() {
        return soNhom;
    }

    public void setSoNhom(int soNhom) {
        this.soNhom = soNhom;
    }

    public int getSoLuongSV() {
        return soLuongSV;
    }

    public void setSoLuongSV(int soLuongSV) {
        this.soLuongSV = soLuongSV;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getMaMH() {
        return maMH;
    }
}
