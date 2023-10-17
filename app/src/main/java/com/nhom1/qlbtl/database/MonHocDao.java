package com.nhom1.qlbtl.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.nhom1.qlbtl.model.MonHoc;
import com.nhom1.qlbtl.model.SystemConstant;

import java.util.ArrayList;
import java.util.List;

public class MonHocDao {
    DBQuanLyBTL database;
    ArrayList<MonHoc> monHocList = new ArrayList<>();
    public MonHocDao(Context context){
        database = new DBQuanLyBTL(context);
    }
    public ArrayList<MonHoc> getListMonHoc(){
        try{
            String sql = "select * from tblMonHoc";
            monHocList.clear();
            Cursor cs = database.getData(sql);
            while(cs.moveToNext()){
                String maMH = cs.getString(0);
                String tenMH = cs.getString(1);
                int thoiGianHoc = cs.getInt(2);
                int soLuongLop = cs.getInt(3);
                MonHoc a = new MonHoc(maMH, tenMH, thoiGianHoc, soLuongLop);
                monHocList.add(a);
            }
            Log.i(SystemConstant.TAG_NAME_MON_HOC, "Lấy danh sách môn học thành công");
            return monHocList;
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_MON_HOC, e.getMessage());
            return null;
        }

    }

}
