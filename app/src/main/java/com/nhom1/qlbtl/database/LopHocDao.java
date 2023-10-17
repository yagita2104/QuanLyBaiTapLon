package com.nhom1.qlbtl.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.nhom1.qlbtl.model.LopHoc;
import com.nhom1.qlbtl.model.MonHoc;
import com.nhom1.qlbtl.model.Nhom;
import com.nhom1.qlbtl.model.SystemConstant;

import java.util.ArrayList;

public class LopHocDao {
    DBQuanLyBTL database;
    ArrayList<LopHoc> lopHocList = new ArrayList<>();
    public LopHocDao(Context context){
        database = new DBQuanLyBTL(context);
    }
    public ArrayList<LopHoc> getAllListLopHoc(){
        try{
            String sql = "select * from tblLopHoc";
            lopHocList.clear();
            Cursor cs = database.getData(sql);
            while(cs.moveToNext()){
                String maLop = cs.getString(0);
                String tenLop = cs.getString(1);
                String lopTruong = cs.getString(2);
                int soNhom = cs.getInt(3);
                int soSV = cs.getInt(4);
                String maMH = cs.getString(5);
                LopHoc a = new LopHoc(maLop, tenLop, lopTruong, soNhom, soSV, maMH);
                lopHocList.add(a);
            }
            Log.i(SystemConstant.TAG_NAME_MON_HOC, "Lấy danh sách tất cả lớp học thành công");
            return lopHocList;
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_MON_HOC, e.getMessage());
            return null;
        }

    }
    public ArrayList<LopHoc> getListLopHoc(String x){
        try{
            String sql = "select * from tblLopHoc where MaMH = '"+x+"'";
            lopHocList.clear();
            Cursor cs = database.getData(sql);
            while(cs.moveToNext()){
                String maLop = cs.getString(0);
                String tenLop = cs.getString(1);
                String lopTruong = cs.getString(2);
                int soNhom = cs.getInt(3);
                int soSV = cs.getInt(4);
                String maMH = cs.getString(5);
                LopHoc a = new LopHoc(maLop, tenLop, lopTruong, soNhom, soSV, maMH);
                lopHocList.add(a);
            }
            Log.i(SystemConstant.TAG_NAME_MON_HOC, "Lấy danh sách lớp học theo môn thành công");
            return lopHocList;
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_MON_HOC, e.getMessage());
            return null;
        }

    }
    public ArrayList<LopHoc> getListLopHocTheoTen(String x){
        try{
            String sql = "select * from tblLopHoc where MaMH = '"+x+"'";
            lopHocList.clear();
            Cursor cs = database.getData(sql);
            while(cs.moveToNext()){
                String maLop = cs.getString(0);
                String tenLop = cs.getString(1);
                String lopTruong = cs.getString(2);
                int soNhom = cs.getInt(3);
                int soSV = cs.getInt(4);
                String maMH = cs.getString(5);
                LopHoc a = new LopHoc(maLop, tenLop, lopTruong, soNhom, soSV, maMH);
                lopHocList.add(a);
            }
            Log.i(SystemConstant.TAG_NAME_MON_HOC, "Lấy danh sách lớp học theo mã thành công");
            return lopHocList;
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_MON_HOC, e.getMessage());
            return null;
        }

    }
    public int tongSoSVLop(LopHoc lopHoc) {
        try{
            String sql = "SELECT COUNT(*) FROM tblSinhVien WHERE MaMH = '"+lopHoc.getMaMH()+"' AND MaLH = '"+lopHoc.getMaLop()+"'";
            Cursor cursor = database.getData(sql);
            int count = 0;
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            cursor.close();
            Log.i(SystemConstant.TAG_NAME_MON_HOC, "Lấy tổng số sinh viên lớp học thành công");
            return count;
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_MON_HOC, e.getMessage());
            return -1;
        }

    }
    public int tongNhom(LopHoc lopHoc) {
        try{
            String sql = "SELECT COUNT(DISTINCT MaNhom) FROM tblNhom WHERE MaLH = '"+lopHoc.getMaLop()+"' AND MaMH = '"+lopHoc.getMaMH()+"' AND MaNhom <> '0'";
            Cursor cursor = database.getData(sql);
            int count = 0;
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            cursor.close();
            Log.i(SystemConstant.TAG_NAME_MON_HOC, "Lấy tổng số nhóm trong lớp thành công");
            return count;
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_MON_HOC, e.getMessage());
            return -1;
        }

    }
}
