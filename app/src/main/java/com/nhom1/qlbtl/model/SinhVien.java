package com.nhom1.qlbtl.model;

import java.io.Serializable;

public class SinhVien implements Serializable{
    private String maSV;
    private String tenSinhVien;
    private float diem = -1;
    private String maMH;
    private String maLop;
    private String maNhom;

    public SinhVien(String maSV, String tenSinhVien, String maNhom, float diem) {
        this.maSV = maSV;
        this.tenSinhVien = tenSinhVien;
        this.maNhom = maNhom;
        this.diem = diem;
    }
    public SinhVien(String maSV, String tenSinhVien, String maNhom) {
        this.maSV = maSV;
        this.tenSinhVien = tenSinhVien;
        this.maNhom = maNhom;
    }

    public SinhVien(String maSV, String tenSinhVien, String maMH, String maLop, String maNhom) {
        this.maSV = maSV;
        this.tenSinhVien = tenSinhVien;
        this.maMH = maMH;
        this.maLop = maLop;
        this.maNhom = maNhom;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getMaNhom() {
        return maNhom;
    }

    public void setMaNhom(String maNhom) {
        this.maNhom = maNhom;
    }

    public SinhVien() {
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
    }

    public String getThuocNhom() {
        return maNhom;
    }

    public void setThuocNhom(String thuocNhom) {
        this.maNhom = thuocNhom;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }
}
