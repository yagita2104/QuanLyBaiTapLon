package com.nhom1.qlbtl.model;

public class MonHoc {
    private String maMon;
    private String tenMon;
    private int thoiGianHoc;
    private int soLuongLop;

    public MonHoc(String maMon, String tenMon, int thoiGianHoc, int soLuongLop) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.thoiGianHoc = thoiGianHoc;
        this.soLuongLop = soLuongLop;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getThoiGianHoc() {
        return thoiGianHoc;
    }

    public void setThoiGianHoc(int thoiGianHoc) {
        this.thoiGianHoc = thoiGianHoc;
    }

    public int getSoLuongLop() {
        return soLuongLop;
    }

    public void setSoLuongLop(int soLuongLop) {
        this.soLuongLop = soLuongLop;
    }
}
