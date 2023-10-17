package com.nhom1.qlbtl.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.nfc.Tag;
import android.util.Log;

import com.nhom1.qlbtl.model.MonHoc;
import com.nhom1.qlbtl.model.Nhom;
import com.nhom1.qlbtl.model.SinhVien;
import com.nhom1.qlbtl.model.SystemConstant;

import java.util.ArrayList;

public class SinhVienDao {
    DBQuanLyBTL database;
    ArrayList<SinhVien> sinhVienList = new ArrayList<>();
    public SinhVienDao(Context context){
        database = new DBQuanLyBTL(context);
    }
    public ArrayList<SinhVien> getAllListSinhVien(){
        try{
            String sql = "select * from tblSinhVien";
            sinhVienList.clear();
            Cursor cs = database.getData(sql);
            while(cs.moveToNext()){
                String maSV = cs.getString(0);
                String tenSV = cs.getString(1);
                int diem = cs.getInt(2);
                String maMH = cs.getString(3);
                String maLH = cs.getString(4);
                String maNhom = cs.getString(5);

                SinhVien a = new SinhVien(maSV, tenSV , maMH, maLH, maNhom);
                sinhVienList.add(a);
            }
            Log.i(SystemConstant.TAG_NAME_MON_HOC, "Lấy danh sách sinh viên thành công");
            return sinhVienList;

        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_MON_HOC, e.getMessage());
            return null;
        }

    }
//    public ArrayList<SinhVien> getSinhVienList(String monHoc, String lopHoc){
//        //Câu lệnh truy vấn dữ liệu và sắp xếp dữ liệu theo tên
//        String sql = "SELECT * FROM tblSinhVien WHERE MaMH = '" + monHoc + "' AND MaLH = '" + lopHoc + "'";
//        sinhVienList.clear();
//        Cursor cs = database.getData(sql);
//        while(cs.moveToNext()){
//            String maSV = cs.getString(0);
//            String tenSV = cs.getString(1);
//            int diem = cs.getInt(2);
//            String maMH = cs.getString(3);
//            String maLH = cs.getString(4);
//            String maNhom = cs.getString(5);
//
//            SinhVien a = new SinhVien(maSV, tenSV , maMH, maLH, maNhom);
//            sinhVienList.add(a);
//        }
//        return sinhVienList;
//    }
    public void themSinhVienVaoNhom(String maNhom, String maSV){
        try{
            String sql = "UPDATE tblSinhVien SET MaNhom = '"+maNhom+"' WHERE MaSV = '"+maSV+"'";
            database.queryData(sql);
            Log.i(SystemConstant.TAG_NAME_MON_HOC, "Thêm sinh viên vào nhóm thành công");
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_MON_HOC, e.getMessage());
        }
    }
    public String tenTruongNhom(String maSV){
        try{
            String sql = "SELECT * FROM tblSinhVien WHERE MaSV = '" + maSV + "'";
            Cursor cursor = database.getData(sql);
            String tenSV = "";
            if (cursor.moveToNext()) {
                tenSV = cursor.getString(1);
            }
            cursor.close();
            Log.i(SystemConstant.TAG_NAME_MON_HOC, "Lấy tên trưởng nhóm thành công");
            return tenSV;
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_MON_HOC, e.getMessage());
            return null;
        }

    }
    public ArrayList<SinhVien> listSinhVienNhom(String maMon, String maLop, String maN){
        try{
            String sql = "SELECT * FROM tblSinhVien WHERE MaMH = '" + maMon + "' AND MaLH = '" + maLop + "'AND maNhom = '"+maN+"'";
            sinhVienList.clear();
            Cursor cs = database.getData(sql);
            while(cs.moveToNext()){
                String maSV = cs.getString(0);
                String tenSV = cs.getString(1);
                int diem = cs.getInt(2);
                String maMH = cs.getString(3);
                String maLH = cs.getString(4);
                String maNhom = cs.getString(5);
                SinhVien a = new SinhVien(maSV, tenSV , maMH, maLH, maNhom);
                sinhVienList.add(a);
            }
            Log.i(SystemConstant.TAG_NAME_MON_HOC, "Lấy danh sách sinh viên theo nhóm thành công");
            return sinhVienList;
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_MON_HOC, e.getMessage());
            return null;
        }
    }
    public ArrayList<SinhVien> listSinhVienTheoNhom(String maMon, String maLop){
        try{
            String sql = "SELECT * FROM tblSinhVien WHERE MaMH = '" + maMon + "' AND MaLH = '" + maLop + "' ORDER BY CAST(MaNhom AS SIGNED) ASC";
            sinhVienList.clear();
            Cursor cs = database.getData(sql);
            while(cs.moveToNext()){
                String maSV = cs.getString(0);
                String tenSV = cs.getString(1);
                int diem = cs.getInt(2);
                String maMH = cs.getString(3);
                String maLH = cs.getString(4);
                String maNhom = cs.getString(5);
                SinhVien a = new SinhVien(maSV, tenSV , maMH, maLH, maNhom);
                sinhVienList.add(a);
            }
            Log.i(SystemConstant.TAG_NAME_MON_HOC, "Lấy danh sách sinh viên theo nhóm thành công");
            return sinhVienList;
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_MON_HOC, e.getMessage());
            return null;
        }
    }
    public void choDiem(Nhom nhom){
        try{
            String sql = "UPDATE tblSinhVien SET Diem = "+nhom.getDiemNhom()+" WHERE MaMH = '"+nhom.getMaMH()+"' AND MaLH = '"+nhom.getMaLH()+"' AND MaNhom = '"+nhom.getMaNhom()+"'";
            database.queryData(sql);
            Log.i(SystemConstant.TAG_NAME_CHAM_DIEM, "Cho điểm nhóm thành công");
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_CHAM_DIEM, e.getMessage());
        }

    }
    public float layDiem(SinhVien sinhVien){
        try{
            String sql = "SELECT Diem FROM tblSinhVien WHERE MaSV = '"+sinhVien.getMaSV()+"'";
            Cursor cursor = database.getData(sql);
            float diem = 0;
            if (cursor.moveToFirst()) {
                int diemIndex = cursor.getColumnIndex("Diem");
                diem = cursor.getFloat(diemIndex);
            }
            cursor.close();
            Log.i(SystemConstant.TAG_NAME_CHAM_DIEM, "Lấy điểm sinh viên thành công");
            return diem;
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_CHAM_DIEM, e.getMessage());
            return -1;
        }
    }



}
