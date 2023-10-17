package com.nhom1.qlbtl.model;

public class DanhSachTruot {
    private String maSV;
    private String HoVaTen;
    private String maMon;
    private String TenMon;
    private String LyDo;

    public DanhSachTruot(String maSV, String hoVaTen, String maMon, String tenMon, String lyDo) {
        this.maSV = maSV;
        HoVaTen = hoVaTen;
        this.maMon = maMon;
        TenMon = tenMon;
        LyDo = lyDo;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoVaTen() {
        return HoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        HoVaTen = hoVaTen;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public String getLyDo() {
        return LyDo;
    }

    public void setLyDo(String lyDo) {
        LyDo = lyDo;
    }
}
