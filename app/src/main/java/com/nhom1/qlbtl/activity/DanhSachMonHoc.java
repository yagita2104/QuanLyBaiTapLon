package com.nhom1.qlbtl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.nhom1.qlbtl.R;
import com.nhom1.qlbtl.adapter.MonHocAdapter;
import com.nhom1.qlbtl.database.MonHocDao;
import com.nhom1.qlbtl.model.MonHoc;

import java.util.ArrayList;
import java.util.List;

public class DanhSachMonHoc extends AppCompatActivity {
    MonHocAdapter monHocAdapter;
    List<MonHoc> monHocList = new ArrayList<>();
    RecyclerView rvMonHoc;
    MonHocDao monHocDao = new MonHocDao(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_mon_hoc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mapping();
        addAction();
    }
    private void mapping() {
        rvMonHoc = findViewById(R.id.rvMonHoc);
        //fakeData();
        monHocList = monHocDao.getListMonHoc();
        monHocAdapter = new MonHocAdapter(this, monHocList);
        rvMonHoc.setAdapter(monHocAdapter);
        rvMonHoc.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
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