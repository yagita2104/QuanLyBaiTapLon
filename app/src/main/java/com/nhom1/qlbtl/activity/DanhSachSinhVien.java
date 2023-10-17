package com.nhom1.qlbtl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.nhom1.qlbtl.R;
import com.nhom1.qlbtl.adapter.LopHocAdapter;
import com.nhom1.qlbtl.adapter.NhomAdapter;
import com.nhom1.qlbtl.adapter.SinhVienAdapter;
import com.nhom1.qlbtl.database.LopHocDao;
import com.nhom1.qlbtl.database.NhomDao;
import com.nhom1.qlbtl.database.SinhVienDao;
import com.nhom1.qlbtl.model.LopHoc;
import com.nhom1.qlbtl.model.MonHoc;
import com.nhom1.qlbtl.model.Nhom;
import com.nhom1.qlbtl.model.SinhVien;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DanhSachSinhVien extends AppCompatActivity {
    RecyclerView rvSinhVien;
    SinhVienAdapter sinhVienAdapter;
    NhomAdapter nhomAdapter;
    List<SinhVien> sinhVienList = new ArrayList<>();
    List<SinhVien> allSinhVien = new ArrayList<>();
    SinhVienDao sinhVienDao = new SinhVienDao(this);
    List<Nhom> allNhom = new ArrayList<>();
    List<Nhom> nhomList = new ArrayList<>();
    NhomDao nhomDao = new NhomDao(this);
    SwitchCompat swChuyenDang;
    List<SinhVien> listPhanNhom = new ArrayList<>();
    List<Nhom> listNhom = new ArrayList<>();
    String maMH;
    String maLH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_sinh_vien);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mapping();
        addAction();
    }

    private void mapping() {
        swChuyenDang = findViewById(R.id.btnChuyenDang);
        rvSinhVien = findViewById(R.id.rvSinhVien);
        allSinhVien = sinhVienDao.getAllListSinhVien();
        allNhom = nhomDao.getAllListNhom();

        Intent intent = getIntent();
        maMH = intent.getStringExtra("maMH");
        maLH = intent.getStringExtra("maLH");
        sinhVienList = sinhVienDao.listSinhVienTheoNhom(maMH, maLH);
        nhomList = nhomDao.getListNhom(maMH, maLH);

        sinhVienAdapter = new SinhVienAdapter(this, sinhVienList);
        nhomAdapter = new NhomAdapter(this, nhomList);

        rvSinhVien.setAdapter(sinhVienAdapter);
        rvSinhVien.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        listPhanNhom = sinhVienList;
    }
    private void addAction() {
        swChuyenDang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(swChuyenDang.isChecked()){
                    swChuyenDang.setText("Theo nhóm: ON ");
                    rvSinhVien.setAdapter(nhomAdapter);
                    rvSinhVien.setLayoutManager(new LinearLayoutManager(DanhSachSinhVien.this, RecyclerView.VERTICAL, false));
                    nhomAdapter.notifyDataSetChanged();

                }
                else {
                    swChuyenDang.setText("Theo nhóm: OFF ");
                    rvSinhVien.setAdapter(sinhVienAdapter);
                    rvSinhVien.setLayoutManager(new LinearLayoutManager(DanhSachSinhVien.this, RecyclerView.VERTICAL, false));
                    sinhVienAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    public void themSinhvienVaoNhom(List<SinhVien> sinhVienList, String maNhom){
        for (int i = 0; i < sinhVienList.size(); i++) {
            sinhVienDao.themSinhVienVaoNhom(sinhVienList.get(i).getMaSV(), maNhom);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_phan_nhom, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            DanhSachLop.lopHocAdapter.notifyDataSetChanged();
            finish();
            return true;
        }
        if(id == R.id.mnuPhanNhomThuCong){
            Intent intent = new Intent(this, PhanNhom.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("sinhvien", (Serializable) sinhVienList);
            intent.putExtras(bundle);
            startActivity(intent);
            return true;
        }
        if(id == R.id.mnuPhanNhomTuDong){
            String maNhom = sinhVienList.get(0).getMaNhom();
            if(maNhom.equals("0")){
                Toast.makeText(this, "Phân nhóm thành công", Toast.LENGTH_SHORT).show();
                int count = 0;
                listNhom.clear();
                List<SinhVien> sinhVienThua = new ArrayList<>();
                for (int i = 0; i < listPhanNhom.size(); i+=5) {
                    count++;
                    List<SinhVien> sinhViens = new ArrayList<>();
                    if(i+5 > listPhanNhom.size()){
                        sinhVienThua = themSinhVienConLai(i);

                        for (int j = 0; j < sinhVienThua.size(); j++) {
                            int dem = j+1;
                            sinhVienDao.themSinhVienVaoNhom(String.valueOf(dem), sinhVienThua.get(j).getMaSV());
                        }
                        break;
                    }else{
                        sinhViens = listPhanNhom.subList(i, i+5);
                        Nhom nhom = new Nhom(String.valueOf(count), "Nhóm " + count, "","", 5, -1, sinhViens.get(0).getMaMH(), sinhViens.get(0).getMaLop(), "" ,"");
                        nhom.setTenTruongNhom(sinhVienDao.tenTruongNhom(sinhViens.get(0).getMaSV()));
                        nhomDao.themNhom(nhom);
                        themSinhvienVaoNhom(sinhViens, nhom.getMaNhom());
                        for (SinhVien sv: sinhViens) {
                            sinhVienDao.themSinhVienVaoNhom(nhom.getMaNhom(), sv.getMaSV());
                        }
                    }
                }
                sinhVienList = sinhVienDao.listSinhVienTheoNhom(maMH, maLH);
                nhomList = nhomDao.getListNhom(maMH, maLH);
                sinhVienAdapter.notifyDataSetChanged();
                nhomAdapter.notifyDataSetChanged();
            }else Toast.makeText(this, "Nhóm đã được phân từ trước", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private List<SinhVien> themSinhVienConLai(int i) {
        List<SinhVien> sinhViens = new ArrayList<>();
        for (int j = i; j < listPhanNhom.size(); j++) {
            sinhViens.add(listPhanNhom.get(j));
        }
        return sinhViens;
    }
}