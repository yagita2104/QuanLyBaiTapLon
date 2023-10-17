package com.nhom1.qlbtl.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.nhom1.qlbtl.model.BaoCaoTienDoModel;
import com.nhom1.qlbtl.model.DanhSachTruot;
import com.nhom1.qlbtl.model.Nhom;
import com.nhom1.qlbtl.model.SystemConstant;

import java.util.ArrayList;



public class BaoCaoTienDoDao {
    Context context;
    DBQuanLyBTL database;
    ArrayList<Nhom> nhomList = new ArrayList<>();
    ArrayList<BaoCaoTienDoModel> baoCaoTienDoList = new ArrayList<>();
    public BaoCaoTienDoDao(Context context){
        database = new DBQuanLyBTL(context);
        this.context = context;
    }
    public void themBanGhi(Nhom nhom){
        try{
            String sql = "INSERT OR IGNORE INTO tblBaoCaoTienDo VALUES (null, '"+nhom.getTienDo()+"', '"+ nhom.getMaNhom()+"', '"+ nhom.getTenNhom()+"', '"+nhom.getTenDeTai()+"', '"+nhom.getTenTruongNhom()+"', '"+nhom.getSoThanhVien()+"', '"+ nhom.getTrangThai()+"', '"+ nhom.getTienDo()+"', '"+nhom.getMaMH()+"', '"+nhom.getMaLH()+"')";
            database.queryData(sql);
            Log.i(SystemConstant.TAG_NAME_BAO_CAO_TIEN_DO, "thực hiện thành công insert báo cáo tiến độ");
        }catch (SQLiteException e){
            Log.i(SystemConstant.TAG_NAME_BAO_CAO_TIEN_DO, e.getMessage());
        }
    }
    public void suaBanGhi(Nhom nhom){
        try{
            String sql = "UPDATE tblBaoCaoTienDo SET TrangThai = '"+nhom.getTrangThai()+"' WHERE MaMH = '"+nhom.getMaMH()+"' AND MaLH = '"+nhom.getMaLH()+"' AND MaNhom = '"+nhom.getMaNhom()+"'";
            database.queryData(sql);
            Log.i(SystemConstant.TAG_NAME_BAO_CAO_TIEN_DO, "thực hiện thành công cập nhật bản ghi báo cáo tiến độ");
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_BAO_CAO_TIEN_DO, e.getMessage());
        }
    }
    public void capNhatTienDo(Nhom nhom){
        try{
            String sql = "UPDATE tblBaoCaoTienDo SET TrangThai = '"+nhom.getTrangThai()+"' WHERE MaMH = '"+nhom.getMaMH()+"' AND MaLH = '"+nhom.getMaLH()+"' AND MaNhom = '"+nhom.getMaNhom()+"' AND TenTienDo = '"+nhom.getTienDo()+"'" ;
            database.queryData(sql);
            Log.i(SystemConstant.TAG_NAME_BAO_CAO_TIEN_DO, "thực hiện thành công cập nhật tiến độ báo cáo tiến độ");
        }catch (SQLiteException e){
            Log.e(SystemConstant.TAG_NAME_BAO_CAO_TIEN_DO, e.getMessage());
        }
    }
}
