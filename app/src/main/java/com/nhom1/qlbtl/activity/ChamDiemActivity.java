package com.nhom1.qlbtl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.nhom1.qlbtl.R;
import com.nhom1.qlbtl.adapter.ChamDiemAdapter;
import com.nhom1.qlbtl.database.LopHocDao;
import com.nhom1.qlbtl.database.MonHocDao;
import com.nhom1.qlbtl.database.NhomDao;
import com.nhom1.qlbtl.model.LopHoc;
import com.nhom1.qlbtl.model.MonHoc;
import com.nhom1.qlbtl.model.Nhom;

import java.util.ArrayList;
import java.util.List;

public class ChamDiemActivity extends AppCompatActivity {
    private Spinner spmon,splop;
    private RecyclerView rcv_chamdiem;
    ChamDiemAdapter adtNhom;
    NhomDao nhomDao = new NhomDao(this);
    List<Nhom> nhomList = null;
    List<Nhom> chamDiemNhomList = new ArrayList<>();
    MonHocDao monHocDao = new MonHocDao(this);
    LopHocDao lopHocDao = new LopHocDao(this);
    ArrayAdapter<String> monAdapter;
    ArrayAdapter<String> lopAdapter;
    ArrayList<MonHoc> allMonHoc;
    List<Nhom> listNhomHienTai = new ArrayList<>();
    int item=0;
    int item1=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cham_diem);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rcv_chamdiem = findViewById(R.id.rcv_chamdiem);

        adtNhom = new ChamDiemAdapter(chamDiemNhomList, this);
        rcv_chamdiem.setAdapter(adtNhom);
        rcv_chamdiem.setLayoutManager(new LinearLayoutManager(this));


        spmon = findViewById(R.id.spmon);
        splop = findViewById(R.id.splop);

        allMonHoc = new ArrayList<>();
        allMonHoc = monHocDao.getListMonHoc();

        ArrayList<String> mon = new ArrayList<>();
        ArrayList<String> lop = new ArrayList<>();

        for (int i = 0; i < allMonHoc.size(); i++) {
            mon.add(allMonHoc.get(i).getTenMon());
        }

        monAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mon);
        monAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        lopAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lop);
        lopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spmon.setAdapter(monAdapter);


        splop.setAdapter(lopAdapter);
        spmon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<LopHoc> lopHocs = new ArrayList<>();
                lop.clear();
                item = i + 1;
                splop.setSelection(0);
                lopHocs = lopHocDao.getListLopHocTheoTen("MH"+item);

                for (int j = 0; j < lopHocs.size(); j++) {
                    lop.add(lopHocs.get(j).getTenLop());
                }
                lopAdapter.notifyDataSetChanged();

                chamDiemNhomList.clear();
                String maLH = "LH1";
                String maMH = "MH" + (i+1);
                System.out.println(maMH + maLH);
                try {
                    chamDiemNhomList = nhomDao.getListNhom(maMH, maLH);

                }catch (SQLiteException e){

                }

//                System.out.println(chamDiemNhomList.get(0).getTenNhom());
                adtNhom.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        splop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                chamDiemNhomList.clear();
                item1 = i+1;
                String maLH = "LH" + (item1);
                String maMH = "MH" + (item);
                try {
                    chamDiemNhomList = nhomDao.getListNhom(maMH, maLH);

                }catch (SQLiteException e){
                    e.printStackTrace();
                }
                adtNhom.setnListNhom(chamDiemNhomList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void inputData() {

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