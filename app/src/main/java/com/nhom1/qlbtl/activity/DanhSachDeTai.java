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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.nhom1.qlbtl.R;
import com.nhom1.qlbtl.adapter.DeTaiAdapter;
import com.nhom1.qlbtl.database.DeTaiDao;
import com.nhom1.qlbtl.database.MonHocDao;
import com.nhom1.qlbtl.model.DeTai;
import com.nhom1.qlbtl.model.MonHoc;

import java.util.ArrayList;
import java.util.List;

public class DanhSachDeTai extends AppCompatActivity {

    // các biến view
    private Spinner spinnerMonHoc;
    private Spinner spinnerNamHoc;

    private RecyclerView recyclerViewGroups;
    private TextView txttenDT;
    private EditText edittenDT;

    // các biến danh sách
    private ArrayList<DeTai> baitaplonList;
    private DeTaiAdapter BTLAdapter;

    private List<String> MonHocList = new ArrayList<>();
    private List<String> MaMonList = new ArrayList<>();



    // các biến xây dựng truy vấn dữ liệu
    private MonHocDao monHocDao = new MonHocDao(this);
    private List<String> NamHocList;

    public void getWidget(){
        spinnerMonHoc = findViewById(R.id.spinnerMonHoc);
        spinnerNamHoc = findViewById(R.id.spinnerNamHoc);
        recyclerViewGroups = findViewById(R.id.recyclerViewGroup);
        txttenDT = findViewById(R.id.txtDeTai);
        edittenDT = findViewById(R.id.edtDeTai);
        add = findViewById(R.id.add);

    }


    ArrayList<DeTai> baiTapLonTuyChinh;
    DeTaiDao deTaiDao = new DeTaiDao(this);
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ten_de_tai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Initialize views
        getWidget();

        /** thực hiện lấy ra danh sách tên môn học để thực hiện hiện thị đề tài theo môn học*/
        for (int i = 0; i < monHocDao.getListMonHoc().size(); i++) {
            MonHocList.add(monHocDao.getListMonHoc().get(i).getTenMon());
            MaMonList.add(monHocDao.getListMonHoc().get(i).getMaMon());
        }

        /** xây dựng dữ liệu về các năm*/
        NamHocList = new ArrayList<>();
        NamHocList.add("2000");
        NamHocList.add("2002");
        NamHocList.add("2004");


        /**thực hiện cài dặt cho spinner, xây dựng các bộ chuyển đôi spinner*/
        ArrayAdapter<String> subjectAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, MonHocList);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, NamHocList);

        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMonHoc.setAdapter(subjectAdapter);
        spinnerNamHoc.setAdapter(yearAdapter);

        spinnerr();

        /**thực hiện xây dựng sự kiện cho button
         * mục đích: thêm đề tài vào cơ sở dữ liệu và danh sách đề */
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeTai a = new DeTai();
                String selectedSubject = spinnerMonHoc.getSelectedItem().toString();
                String selectedYear = spinnerNamHoc.getSelectedItem().toString();
                String tenDT = edittenDT.getText().toString();
                //Chuẩn bị đầy đủ các thuộc tính của đề tài
                int stt = baitaplonList.size() - 1;
                String maBTL = baitaplonList.get(stt).getMaBTL();
                int stt_thuc = Integer.parseInt(Character.toString(maBTL.charAt(maBTL.length() - 1))) + 1;
                a.setMaBTL("ĐT" + stt_thuc);
                a.setTenBTL(tenDT);
                for (int i = 0; i < MonHocList.size(); i++) {
                    if(selectedSubject.equals(MonHocList.get(i))){
                        a.setMaMonHoc(MaMonList.get(i)); //xử lý để lấy mã môn
                    }
                }
                a.setTenMonHoc(selectedSubject);
                a.setNamHoc(selectedYear);
                deTaiDao.themDeTai(a);
                baitaplonList = deTaiDao.getListDeTai();
                try {
                    baiTapLonTuyChinh = deTaiDao.getListDeTaiTheoTen(spinnerMonHoc.getSelectedItem().toString(), spinnerNamHoc.getSelectedItem().toString());

                }catch (SQLiteException e){


                }

                BTLAdapter.notifyDataSetChanged();
                spinnerr();
                edittenDT.setText("");
            }
        });
        // Set up RecyclerView
        baitaplonList = new ArrayList<>();
        baitaplonList = deTaiDao.getListDeTai();

        baiTapLonTuyChinh = deTaiDao.getListDeTaiTheoTen(spinnerMonHoc.getSelectedItem().toString(), spinnerNamHoc.getSelectedItem().toString());

        // Create and set the adapter for the RecyclerView
        BTLAdapter = new DeTaiAdapter(baiTapLonTuyChinh, this);
        BTLAdapter.setBtls(baiTapLonTuyChinh);
        recyclerViewGroups.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewGroups.setAdapter(BTLAdapter);
    }
    private void reset(){
        baiTapLonTuyChinh = deTaiDao.getListDeTaiTheoTen(spinnerMonHoc.getSelectedItem().toString(), spinnerNamHoc.getSelectedItem().toString());
        BTLAdapter = new DeTaiAdapter(baiTapLonTuyChinh, this);
        recyclerViewGroups.setAdapter(BTLAdapter);
        recyclerViewGroups.setLayoutManager(new LinearLayoutManager(this));
    }
    private void spinnerr() {
        spinnerMonHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                //filterGroups();
                reset();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerNamHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                reset();
                //filterGroups();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
    // mennu
}