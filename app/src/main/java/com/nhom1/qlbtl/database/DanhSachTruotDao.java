package com.nhom1.qlbtl.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.nhom1.qlbtl.model.DanhSachTruot;
import com.nhom1.qlbtl.model.DeTai;
import com.nhom1.qlbtl.model.SinhVien;
import com.nhom1.qlbtl.model.SystemConstant;

import java.util.ArrayList;

public class DanhSachTruotDao {
    DBQuanLyBTL database;
    ArrayList<DanhSachTruot> danhSachTruotList = new ArrayList<>();
    public DanhSachTruotDao(Context context){
        database = new DBQuanLyBTL(context);
    }
    public ArrayList<DanhSachTruot> getListDanhSachTruot(){
        try{
            String sql = "select * from tblDanhSachTruot";
            danhSachTruotList.clear();
            Cursor cs = database.getData(sql);
            while(cs.moveToNext()){
                String maSV = cs.getString(0);
                String tenSV = cs.getString(1);
                String maMH = cs.getString(2);
                String tenMon = cs.getString(3);
                String lyDo = cs.getString(4);
                DanhSachTruot a = new DanhSachTruot(maSV, tenSV, maMH, tenMon,lyDo);
                danhSachTruotList.add(a);
            }
            Log.i(SystemConstant.TAG_NAME_DANH_SACH_TRUOT, "Lấy danh sách trượt thành công");
            return danhSachTruotList;
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_DANH_SACH_TRUOT, e.getMessage());
            return null;
        }

    }
    public void themSVTruot(SinhVien sinhVien, String lyDo){
        String sql = "INSERT INTO tblDanhSachTruot VALUES('"+sinhVien.getMaSV()+"', '"+sinhVien.getTenSinhVien()+"', '"+sinhVien.getMaLop()+"', '"+ sinhVien.getMaMH()+"', '"+lyDo+"')";
        database.queryData(sql);
        sql = "UPDATE tblSinhVien SET MaMH = '0', MaLH = '0' WHERE MaSV = '"+sinhVien.getMaSV()+"'";
        database.queryData(sql);
    }
}
