package com.nhom1.qlbtl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.nhom1.qlbtl.R;
import com.nhom1.qlbtl.adapter.PhanNhomAdapter;
import com.nhom1.qlbtl.database.SinhVienDao;
import com.nhom1.qlbtl.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class PhanNhom extends AppCompatActivity {

    RecyclerView rvPhanNhom;
    PhanNhomAdapter phanNhomAdapter;
    List<SinhVien> sinhVienList = new ArrayList<>();
    SinhVienDao sinhVienDao = new SinhVienDao(this);
    Button phanNhom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_nhom);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mapping();
        addAction();
    }

    private void mapping() {
        rvPhanNhom = findViewById(R.id.rvPhanNhom);
        phanNhom = findViewById(R.id.btnPhanNhom);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            sinhVienList = (List<SinhVien>) bundle.getSerializable("sinhvien");

        }


        phanNhomAdapter = new PhanNhomAdapter(this, sinhVienList);


        rvPhanNhom.setAdapter(phanNhomAdapter);
        rvPhanNhom.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
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