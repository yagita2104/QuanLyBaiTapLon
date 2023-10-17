package com.nhom1.qlbtl.model;

public class BaoCaoTienDoModel extends Nhom{
    private String maTienDo;
    private String tenTienDo;
    private String trangThai;

    public String getMaTienDo() {
        return maTienDo;
    }

    public void setMaTienDo(String maTienDo) {
        this.maTienDo = maTienDo;
    }

    public String getTenTienDo() {
        return tenTienDo;
    }

    public void setTenTienDo(String tenTienDo) {
        this.tenTienDo = tenTienDo;
    }

    @Override
    public String getTrangThai() {
        return trangThai;
    }

    @Override
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public BaoCaoTienDoModel() {
    }

    public BaoCaoTienDoModel(String maNhom, String tenNhom, String tenDeTai, String tenTruongNhom, int soThanhVien, float diemNhom, String maMH, String maLH , String tenTienDo) {
        super(maNhom, tenNhom, tenDeTai, tenTruongNhom, soThanhVien, diemNhom, maMH, maLH);
        this.tenTienDo = tenTienDo;
    }
}