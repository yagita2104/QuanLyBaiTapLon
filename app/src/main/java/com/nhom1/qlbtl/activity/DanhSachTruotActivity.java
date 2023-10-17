package com.nhom1.qlbtl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.nhom1.qlbtl.R;
import com.nhom1.qlbtl.adapter.DanhSachTruotAdapter;
import com.nhom1.qlbtl.database.DanhSachTruotDao;
import com.nhom1.qlbtl.model.BaoCaoTienDoModel;
import com.nhom1.qlbtl.model.DanhSachTruot;

import java.util.ArrayList;
import java.util.List;

public class DanhSachTruotActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DanhSachTruotAdapter danhSachTruotAdapter;
    List<DanhSachTruot> danhSachTruotList = new ArrayList<>();
    DanhSachTruotDao danhSachTruotDao = new DanhSachTruotDao(this);

    List<BaoCaoTienDoModel> baoCaoList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_truot);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);

        danhSachTruotList = danhSachTruotDao.getListDanhSachTruot();
        danhSachTruotAdapter = new DanhSachTruotAdapter(danhSachTruotList);
        recyclerView.setAdapter(danhSachTruotAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


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