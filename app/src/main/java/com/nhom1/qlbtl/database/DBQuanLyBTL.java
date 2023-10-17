package com.nhom1.qlbtl.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DBQuanLyBTL extends SQLiteOpenHelper {
    Context context;
    public DBQuanLyBTL(Context context) {
        super(context, "qlbtl.sqlite", null, 1);
        this.context = context;
    }
    //truy vấn không trả kết quả
    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    //truy vấn trả kết quả
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Tạo các bảng liên quan
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tblMonHoc(MaMH VARCHAR(20) PRIMARY KEY, TenMH VARCHAR(30), ThoiGianHoc INTEGER,  SoLuongLop INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tblLopHoc(MaLH VARCHAR(20), TenLH VARCHAR(30), LopTruong VARCHAR(25), SoNhom INTEGER, SoLuongSinhVien INTEGER, MaMH VARCHAR(20), PRIMARY KEY(MaLH, MaMH),FOREIGN KEY (MaMH) REFERENCES tblMonHoc(MaMH))");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tblSinhVien(MaSV VARCHAR(20) PRIMARY KEY, TenSV VARCHAR(30), Diem FLOAT,  MaMH VARCHAR(20), MaLH VARCHAR(20), MaNhom VARCHAR(20), FOREIGN KEY (MaMH) REFERENCES tblMonHoc(MaMH), FOREIGN KEY (MaLH) REFERENCES tblLopHoc(MaLH), FOREIGN KEY (MaLH) REFERENCES tblLopHoc(MaLH))");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tblNhom(MaNhom VARCHAR(20), TenNhom VARCHAR(30), TenDeTai VARCHAR(100), TruongNhom VARCHAR(20), SoThanhVien INTEGER, DiemNhom FLOAT, TrangThai VARCHAR(20), TienDo VARCHAR(20), MaMH VARCHAR(20), MaLH VARCHAR(20), PRIMARY KEY(MaNhom, MaLH, MaMH), FOREIGN KEY (MaMH) REFERENCES tblMonHoc(MaMH), FOREIGN KEY (MaLH) REFERENCES tblLopHoc(MaLH))");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tblDeTai(MaDeTai VARCHAR(20), TenDeTai VARCHAR(100), MaMH VARCHAR(20),TenMH VARCHAR(50), Nam VARCHAR(4), PRIMARY KEY(MaDeTai, MaMH, Nam), FOREIGN KEY (MaMH) REFERENCES tblMonHoc(MaMH))");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tblDanhSachTruot(MaSV VARCHAR(20) PRIMARY KEY, TenSV VARCHAR(30), MaLop VARCHAR(20), MaMH VARCHAR(20), LyDo VARCHAR(100),  FOREIGN KEY (MaMH) REFERENCES tblMonHoc(MaMH), FOREIGN KEY (MaLop) REFERENCES tblLopHoc(MaLop), FOREIGN KEY (MaSV) REFERENCES tblSinhVien(MaSV))");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tblBaoCaoTienDo(MaTienDo INTEGER PRIMARY KEY AUTOINCREMENT,TenTienDo VARCHAR(40), MaNhom VARCHAR(20), TenNhom VARCHAR(30), TenDeTai VARCHAR(100), TruongNhom VARCHAR(20), SoThanhVien INTEGER, TrangThai VARCHAR(20), TienDo VARCHAR(20), MaMH VARCHAR(20), MaLH VARCHAR(20),UNIQUE(TenTienDo, MaMH, MaLH, MaNhom) , FOREIGN KEY (MaMH) REFERENCES tblMonHoc(MaMH), FOREIGN KEY (MaLH) REFERENCES tblLopHoc(MaLH))");
        //Dữ liệu môn học
        sqLiteDatabase.execSQL("INSERT INTO tblMonHoc VALUES ('MH1', 'Lập trình Java', 15, 2)");
        sqLiteDatabase.execSQL("INSERT INTO tblMonHoc VALUES ('MH2', 'Lập trình Android', 15, 2)");
        sqLiteDatabase.execSQL("INSERT INTO tblMonHoc VALUES ('MH3', 'Kiểm thử phần mềm', 15, 3)");
        sqLiteDatabase.execSQL("INSERT INTO tblMonHoc VALUES ('MH4', 'Học máy', 15, 2)");
        //Dữ liệu lớp học
        //MH1
        sqLiteDatabase.execSQL("INSERT INTO tblLopHoc VALUES ('LH1', 'Lớp học chiều T5, tiết 7-8-9', 'Lớp trưởng 1', 7, 70, 'MH1')");
        sqLiteDatabase.execSQL("INSERT INTO tblLopHoc VALUES ('LH2', 'Lớp học sáng T5, tiết 1-2-3', 'Lớp trưởng 2', 7, 70, 'MH1')");
        //MH2
        sqLiteDatabase.execSQL("INSERT INTO tblLopHoc VALUES ('LH1', 'Lớp học chiều T2, tiết 7-8-9', 'Lớp trưởng 1', 8, 40, 'MH2')");
        sqLiteDatabase.execSQL("INSERT INTO tblLopHoc VALUES ('LH2', 'Lớp học sáng T2, tiết 3-4-5', 'Lớp trưởng 2', 8, 40, 'MH2')");
        //MH3
        sqLiteDatabase.execSQL("INSERT INTO tblLopHoc VALUES ('LH1', 'Lớp học chiều T3, tiết 7-8-9', 'Lớp trưởng 1', 8, 40, 'MH3')");
        sqLiteDatabase.execSQL("INSERT INTO tblLopHoc VALUES ('LH2', 'Lớp học sáng T3, tiết 1-2-3', 'Lớp trưởng 2', 8, 40, 'MH3')");
        sqLiteDatabase.execSQL("INSERT INTO tblLopHoc VALUES ('LH3', 'Lớp học sáng T4, tiết 1-2-3', 'Lớp trưởng 3', 8, 40, 'MH3')");
        //MH4
        sqLiteDatabase.execSQL("INSERT INTO tblLopHoc VALUES ('LH1', 'Lớp học chiều T7, tiết 7-8-9', 'Lớp trưởng 1', 14, 70, 'MH4')");
        sqLiteDatabase.execSQL("INSERT INTO tblLopHoc VALUES ('LH2', 'Lớp học tối T5, tiết 12-13-14', 'Lớp trưởng 2', 14, 70, 'MH4')");
        //Thêm dữ liệu cho bảng sinh viên, ở đây sử dụng 480 sinh viên nên đọc từ file
        try {
            InputStream inputStream = context.getAssets().open("SinhVien.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split("\\|");
                String maSV = columns[0];
                String tenSV = columns[1];
                int diem = Integer.parseInt(columns[2]);
                String maMH = columns[3];
                String maLH = columns[4];
                String maNhom = columns[5];
                sqLiteDatabase.execSQL("INSERT INTO tblSinhVien VALUES ('"+maSV+"', '"+tenSV+"', "+diem+", '"+maMH+"', '"+maLH+"', '"+maNhom+"')");
            }
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Danh sách nhóm
        //sqLiteDatabase.execSQL("INSERT INTO tblNhom VALUES ('1', 'Nhóm 1', 'ĐT1', '2020608515', 10, -1, 'Hoàn thành', 'Đã nộp tuần 1', 'MH3', 'LH2')");
        //Danh sách đề tài
        sqLiteDatabase.execSQL("INSERT INTO tblDeTai VALUES ('ĐT1', 'Tên đề tài 1', 'MH1', 'Lập trình Java', '2000')");
        sqLiteDatabase.execSQL("INSERT INTO tblDeTai VALUES ('ĐT2', 'Tên đề tài 2', 'MH1', 'Lập trình Java', '2000')");
        sqLiteDatabase.execSQL("INSERT INTO tblDeTai VALUES ('ĐT3', 'Tên đề tài 3', 'MH2', 'Lập trình Android', '2002')");
        sqLiteDatabase.execSQL("INSERT INTO tblDeTai VALUES ('ĐT4', 'Tên đề tài 4', 'MH2', 'Lập trình Android', '2002')");
        sqLiteDatabase.execSQL("INSERT INTO tblDeTai VALUES ('ĐT5', 'Tên đề tài 5', 'MH3', 'Kiểm thử phần mềm', '2004')");
        sqLiteDatabase.execSQL("INSERT INTO tblDeTai VALUES ('ĐT6', 'Tên đề tài 6', 'MH4', 'Học máy', '2004')");
        sqLiteDatabase.execSQL("INSERT INTO tblDeTai VALUES ('ĐT7', 'Tên đề tài 7', 'MH4', 'Học máy', '2004')");

        //Danh sách trượt
        sqLiteDatabase.execSQL("INSERT INTO tblDanhSachTruot VALUES ('2020601910', 'Văn Minh Dương', 'MH2', 'Lập trình Android ', 'Điểm không đủ')");
        sqLiteDatabase.execSQL("INSERT INTO tblDanhSachTruot VALUES ('2020603679', 'Phạm Hoàng Đức Tài', 'MH2', 'Lập trình Android', 'Nghỉ quá số tiết')");
        sqLiteDatabase.execSQL("INSERT INTO tblDanhSachTruot VALUES ('2000000000', 'Test', 'MH2', 'Lập trình Android', 'Test')");

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}