package com.nhom1.qlbtl.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import com.nhom1.qlbtl.model.LopHoc;
import com.nhom1.qlbtl.model.Nhom;
import com.nhom1.qlbtl.model.SystemConstant;

import java.util.ArrayList;
import java.util.List;

public class NhomDao {
    Context context;
    DBQuanLyBTL database;
    ArrayList<Nhom> allnhomList = new ArrayList<>();
    ArrayList<Nhom> nhomList = new ArrayList<>();
    public NhomDao(Context context){
        database = new DBQuanLyBTL(context);
        this.context = context;
    }
    public ArrayList<Nhom> getAllListNhom(){
        try{
            String sql = "select * from tblNhom";
            allnhomList.clear();
            Cursor cs = database.getData(sql);
            while(cs.moveToNext()){
                String maNhom = cs.getString(0);
                String tenNhom = cs.getString(1);
                String tenDeTai = cs.getString(2);
                String truongNhom = cs.getString(3);
                int soThanhVien = Integer.parseInt(cs.getString(4));
                float diemNhom = Integer.parseInt(cs.getString(5));
                String maMH = cs.getString(6);
                String maLH = cs.getString(7);
                String trangThai = cs.getString(8);
                String tienDo = cs.getString(9);
                Nhom a = new Nhom(maNhom, tenNhom, tenDeTai, truongNhom, soThanhVien, diemNhom, trangThai, tienDo, maMH, maLH);
                allnhomList.add(a);
            }
            Log.i(SystemConstant.TAG_NAME_MON_HOC, "Lấy danh sách nhóm thành công");
            return allnhomList;
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_MON_HOC, e.getMessage());
            return null;
        }
    }
    public ArrayList<Nhom> getListNhom(String monHoc, String lopHoc){
        try{
            String sql = "SELECT * FROM tblNhom WHERE MaMH = '" + monHoc + "' AND MaLH = '" + lopHoc + "'";
            nhomList.clear();
            Cursor cs = database.getData(sql);
            while(cs.moveToNext()){
                String maNhom = cs.getString(0);
                String tenNhom = cs.getString(1);
                String tenDeTai = cs.getString(2);
                String truongNhom = cs.getString(3);
                int soThanhVien = Integer.parseInt(cs.getString(4));
                float diemNhom = Integer.parseInt(cs.getString(5));
                String maMH = cs.getString(6);
                String maLH = cs.getString(7);
                String trangThai = cs.getString(8);
                String tienDo = cs.getString(9);
                Nhom a = new Nhom(maNhom, tenNhom, tenDeTai, truongNhom, soThanhVien, diemNhom, trangThai, tienDo, maMH, maLH);
                nhomList.add(a);
            }
            Log.i(SystemConstant.TAG_NAME_MON_HOC, "Lấy danh sách nhóm thành công");
            return nhomList;
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_MON_HOC, e.getMessage());
            return null;
        }

    }
//    public ArrayList<Nhom> getListNhomTheoTen(String monHoc, String lopHoc){
//        String sql = "SELECT * FROM tblNhom WHERE TenMH = '" + monHoc + "' AND TenLH = '" + lopHoc + "'";
//        nhomList.clear();
//        Cursor cs = database.getData(sql);
//        while(cs.moveToNext()){
//            String maNhom = cs.getString(0);
//            String tenNhom = cs.getString(1);
//            String tenDeTai = cs.getString(2);
//            String truongNhom = cs.getString(3);
//            int soThanhVien = Integer.parseInt(cs.getString(4));
//            float diemNhom = Integer.parseInt(cs.getString(5));
//            String maMH = cs.getString(6);
//            String maLH = cs.getString(7);
//            String trangThai = cs.getString(8);
//            String tienDo = cs.getString(9);
//            Nhom a = new Nhom(maNhom, tenNhom, tenDeTai, truongNhom, soThanhVien, diemNhom, trangThai, tienDo, maMH, maLH);
//            nhomList.add(a);
//        }
//        return nhomList;
//    }
    public void themNhom(Nhom nhom) {
        try{
            String sql = "INSERT INTO tblNhom VALUES ('" + nhom.getMaNhom() + "', '" + nhom.getTenNhom() + "', '" + nhom.getTenDeTai() + "', '" + nhom.getTenTruongNhom() + "', " + nhom.getSoThanhVien() + ", " + nhom.getDiemNhom() + ", '" + nhom.getTrangThai() + "', '" + nhom.getTienDo() + "', '" + nhom.getMaMH() + "', '" + nhom.getMaLH() + "')";
            database.queryData(sql);
            Log.i(SystemConstant.TAG_NAME_MON_HOC, "Thêm nhóm thành công");
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_MON_HOC, e.getMessage());
        }
    }
    public int tongThanhVien(Nhom nhom) {
        try{
            String sql = "SELECT COUNT(*) FROM tblSinhVien WHERE MaNhom = '"+nhom.getMaNhom()+"' AND MaMH = '"+nhom.getMaMH()+"' AND MaLH = '"+nhom.getMaLH()+"'";
            Cursor cursor = database.getData(sql);
            int count = 0;
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            cursor.close();
            Log.i(SystemConstant.TAG_NAME_MON_HOC, "Lấy tổng thành viên nhóm thành công");
            return count;
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_MON_HOC, e.getMessage());
            return -1;
        }


    }
//    public void capNhatTienDo(Nhom nhom){
//        String sql = "UPDATE tblNhom SET TienDo = '"+nhom.getTienDo()+"', TrangThai = '"+nhom.getTrangThai()+"' WHERE MaMH = '"+nhom.getMaMH()+"' AND MaLH = '"+nhom.getMaLH()+"' AND MaNhom = '"+nhom.getMaNhom()+"'";
//        database.queryData(sql);
//    }
    public void suaDiem(String maNhom, String maMH, String maLH, Float diemNhom){
        try{
            String sql = "UPDATE tblNhom SET DiemNhom = "+diemNhom+" WHERE MaMH = '"+maMH+"' AND MaLH = '"+maLH+"' AND MaNhom = '"+maNhom+"'";
            database.queryData(sql);
            Log.i(SystemConstant.TAG_NAME_MON_HOC, "Sửa điểm thành công");
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_MON_HOC, e.getMessage());
        }
    }
    public void capNhatDeTai(Nhom nhom){
        try{
            String sql = "UPDATE tblNhom SET TenDeTai = '"+nhom.getTenDeTai()+"' WHERE MaMH = '"+nhom.getMaMH()+"' AND MaLH = '"+nhom.getMaLH()+"' AND MaNhom = '"+nhom.getMaNhom()+"'";
            database.queryData(sql);
            sql = "UPDATE tblBaoCaoTienDo SET TenDeTai = '"+nhom.getTenDeTai()+"' WHERE MaMH = '"+nhom.getMaMH()+"' AND MaLH = '"+nhom.getMaLH()+"' AND MaNhom = '"+nhom.getMaNhom()+"'";
            database.queryData(sql);
            Log.i(SystemConstant.TAG_NAME_MON_HOC, "Cập nhật đề tài thành công");
            Log.i(SystemConstant.TAG_NAME_BAO_CAO_TIEN_DO, "Cập nhật đề tài thành công");
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_MON_HOC, e.getMessage());
        }

    }
}
