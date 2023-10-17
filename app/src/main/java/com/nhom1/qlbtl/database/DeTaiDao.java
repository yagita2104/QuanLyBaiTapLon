package com.nhom1.qlbtl.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.nhom1.qlbtl.model.DeTai;
import com.nhom1.qlbtl.model.LopHoc;
import com.nhom1.qlbtl.model.MonHoc;
import com.nhom1.qlbtl.model.SystemConstant;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DeTaiDao {
    DBQuanLyBTL database;
    ArrayList<DeTai> deTaiList = new ArrayList<>();
    ArrayList<DeTai> allDeTai = new ArrayList<>();
    public DeTaiDao(Context context){
        database = new DBQuanLyBTL(context);
    }
    //Lấy hàm lấy dữ liệu
    public ArrayList<DeTai> getListDeTai(){
        try{
            String sql = "select * from tblDeTai";
            allDeTai.clear();
            Cursor cs = database.getData(sql);
            while(cs.moveToNext()){
                String maDeTai = cs.getString(0);
                String tenDeTai = cs.getString(1);
                String maMH = cs.getString(2);
                String tenMH = cs.getString(3);
                String nam = cs.getString(4);
                DeTai a = new DeTai(maDeTai, tenDeTai, maMH,tenMH, nam);
                allDeTai.add(a);
            }
            Log.i(SystemConstant.TAG_NAME_DE_TAI, "Lấy danh sách đề tài thành công");
            return allDeTai;
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_DE_TAI, e.getMessage());
            return null;
        }

    }
    public ArrayList<DeTai> getListDeTaiTheoTen(String tenMon, String namm){
        try{
            String sql = "select * from tblDeTai WHERE TenMH = '"+tenMon+"' AND Nam = '"+namm+"'";
            deTaiList.clear();
            Cursor cs = database.getData(sql);
            while(cs.moveToNext()){
                String maDeTai = cs.getString(0);
                String tenDeTai = cs.getString(1);
                String maMH = cs.getString(2);
                String tenMH = cs.getString(3);
                String nam = cs.getString(4);
                DeTai a = new DeTai(maDeTai, tenDeTai, maMH,tenMH, nam);
                deTaiList.add(a);
            }
            Log.i(SystemConstant.TAG_NAME_DE_TAI, "Lấy danh sách đề tài theo tên môn và năm thành công");
            return deTaiList;
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_DE_TAI, e.getMessage());
            return null;
        }

    }
    public ArrayList<DeTai> getListDeTaiTheoMa(String maMon){
        try{
            String sql = "select * from tblDeTai WHERE maMH = '"+maMon+"'";
            deTaiList.clear();
            Cursor cs = database.getData(sql);
            while(cs.moveToNext()){
                String maDeTai = cs.getString(0);
                String tenDeTai = cs.getString(1);
                String maMH = cs.getString(2);
                String tenMH = cs.getString(3);
                String nam = cs.getString(4);
                DeTai a = new DeTai(maDeTai, tenDeTai, maMH,tenMH, nam);
                deTaiList.add(a);
            }
            Log.i(SystemConstant.TAG_NAME_DE_TAI, "Lấy danh sách đề tài theo tên môn và năm thành công");
            return deTaiList;
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_DE_TAI, e.getMessage());
            return null;
        }

    }
    //Thêm đề tài
    public void themDeTai(DeTai deTai){
        try{
            String sql = "INSERT INTO tblDeTai VALUES ('"+deTai.getMaBTL()+"', '"+deTai.getTenBTL()+"', '"+deTai.getMaMonHoc()+"', '"+deTai.getTenMonHoc()+"', '"+deTai.getNamHoc()+"')";
            database.queryData(sql);
            Log.i(SystemConstant.TAG_NAME_DE_TAI, "Thêm đề tài thành công");
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_DE_TAI, e.getMessage());
        }
    }
    //Xoá đề tài
    public void xoaDeTai(DeTai deTai){
        try{
            String sql = "DELETE FROM tblDeTai WHERE MaDeTai = '"+deTai.getMaBTL()+"'";
            database.queryData(sql);
            Log.i(SystemConstant.TAG_NAME_DE_TAI, "Xoá đề tài thành công");
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_DE_TAI, e.getMessage());
        }
    }
}