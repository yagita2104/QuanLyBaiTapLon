package com.nhom1.qlbtl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.nhom1.qlbtl.R;
import com.nhom1.qlbtl.adapter.LopHocAdapter;
import com.nhom1.qlbtl.database.LopHocDao;
import com.nhom1.qlbtl.model.LopHoc;

import java.util.ArrayList;
import java.util.List;

public class DanhSachLop extends AppCompatActivity {
    RecyclerView rvLopHoc;
    public static LopHocAdapter lopHocAdapter;
    List<LopHoc> lopHocList = new ArrayList<>();
    List<LopHoc> allListLopHoc = new ArrayList<>();
    LopHocDao lopHocDao = new LopHocDao(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_lop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mapping();
        addAction();

    }

    private void mapping() {
        rvLopHoc = findViewById(R.id.rvLopHoc);
        allListLopHoc = lopHocDao.getAllListLopHoc();
        //
        Intent intent = getIntent();
        String maMH = intent.getStringExtra("maMH");
        lopHocList = lopHocDao.getListLopHoc(maMH);

        lopHocAdapter = new LopHocAdapter(this, lopHocList);
        rvLopHoc.setAdapter(lopHocAdapter);
        rvLopHoc.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }
    private void addAction() {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}