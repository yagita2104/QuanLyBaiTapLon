package com.nhom1.qlbtl.model;

import java.util.ArrayList;
import java.util.List;

public class Nhom {
    protected String maNhom;
    protected String tenNhom;
    protected String tenDeTai;
    protected String tenTruongNhom;
    protected int soThanhVien;
    protected float diemNhom;
    protected String maMH;
    protected String maLH;
    protected String trangThai;
    protected String tienDo;

    private List<BaoCaoTienDoModel> baoCaoTienDoModelList = new ArrayList<>();


    public Nhom(String maNhom, String tenNhom, String tenDeTai, String tenTruongNhom, int soThanhVien, float diemNhom, String trangThai, String tienDo) {
        this.maNhom = maNhom;
        this.tenNhom = tenNhom;
        this.tenDeTai = tenDeTai;
        this.tenTruongNhom = tenTruongNhom;
        this.soThanhVien = soThanhVien;
        this.diemNhom = diemNhom;
        this.trangThai = trangThai;
        this.tienDo = tienDo;
    }

    public Nhom() {
    }

    public Nhom(String maNhom, String tenNhom, String tenDeTai, String tenTruongNhom, int soThanhVien, float diemNhom, String maMH, String maLH, String trangThai, String tienDo) {
        this.maNhom = maNhom;
        this.tenNhom = tenNhom;
        this.tenDeTai = tenDeTai;
        this.tenTruongNhom = tenTruongNhom;
        this.soThanhVien = soThanhVien;
        this.diemNhom = diemNhom;
        this.maMH = maMH;
        this.maLH = maLH;
        this.trangThai = trangThai;
        this.tienDo = tienDo;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getMaLH() {
        return maLH;
    }

    public void setMaLH(String maLH) {
        this.maLH = maLH;
    }

    public String getMaNhom() {
        return maNhom;
    }

    public void setMaNhom(String maNhom) {
        this.maNhom = maNhom;
    }

    public String getTenNhom() {
        return tenNhom;
    }

    public void setTenNhom(String tenNhom) {
        this.tenNhom = tenNhom;
    }

    public String getTenDeTai() {
        return tenDeTai;
    }

    public void setTenDeTai(String tenDeTai) {
        this.tenDeTai = tenDeTai;
    }

    public String getTenTruongNhom() {
        return tenTruongNhom;
    }

    public void setTenTruongNhom(String tenTruongNhom) {
        this.tenTruongNhom = tenTruongNhom;
    }

    public int getSoThanhVien() {
        return soThanhVien;
    }

    public void setSoThanhVien(int soThanhVien) {
        this.soThanhVien = soThanhVien;
    }

    public float getDiemNhom() {
        return diemNhom;
    }

    public void setDiemNhom(float diemNhom) {
        this.diemNhom = diemNhom;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTienDo() {
        return tienDo;
    }

    public void setTienDo(String tienDo) {
        this.tienDo = tienDo;
    }
    public List<BaoCaoTienDoModel> getBaoCaoTienDoList() {
        return baoCaoTienDoModelList;
    }

    public void setBaoCaoTienDoList(List<BaoCaoTienDoModel> baoCaoTienDoModelList) {
        this.baoCaoTienDoModelList = baoCaoTienDoModelList;
    }

    public boolean kiemTraTruotMon(){
        int count=0;
        for(BaoCaoTienDoModel a: this.getBaoCaoTienDoList()){
            if(a.getTrangThai() != null){
                if(a.getTrangThai().equalsIgnoreCase("Not complete")){
                    count++;
                }
            }
            if (count>2) return false;
        }
        return true;

    }
}