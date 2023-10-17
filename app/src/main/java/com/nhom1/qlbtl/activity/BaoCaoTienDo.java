package com.nhom1.qlbtl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom1.qlbtl.R;
import com.nhom1.qlbtl.adapter.TienDoAdapter;
import com.nhom1.qlbtl.database.BaoCaoTienDoDao;
import com.nhom1.qlbtl.database.LopHocDao;
import com.nhom1.qlbtl.database.MonHocDao;
import com.nhom1.qlbtl.database.NhomDao;
import com.nhom1.qlbtl.model.BaoCaoTienDoModel;
import com.nhom1.qlbtl.model.LopHoc;
import com.nhom1.qlbtl.model.MonHoc;
import com.nhom1.qlbtl.model.Nhom;

import java.util.ArrayList;
import java.util.List;


public class BaoCaoTienDo extends AppCompatActivity {
    final static String TAG_NAME_PROCESS = "Văn Minh Dương";
    private Spinner spinnerSubject;
    private Spinner spinnerClass;
    private Spinner spinnerProcess;
    private RecyclerView recyclerViewGroups;
    private List<Nhom> groupList;
    private TienDoAdapter groupAdapter;
    private TextView txtClass, txtSubject, txtProcess;
    private Button updataBaoCao;

    private List<BaoCaoTienDoModel> listTienDo = new ArrayList<>();
    // biến dữ danh sách
    private List<MonHoc> subjectList;
    private List<LopHoc> classList;
    private List<String> processList;

    // khởi tạo biến để thực hiện truy vấn cơ sở dữ liệu

    private MonHocDao monHocDao = new MonHocDao(this);

    private LopHocDao lopHocDao = new LopHocDao(this);

    private NhomDao nhomDao = new NhomDao(this);
    List<String> nameSubject = new ArrayList<>();
    List<String> nameClass = new ArrayList<>();


    /*Một số hàm phụ trợ lấy dữ liệu **/

    public List<String> getDanhSachTenLop (List<LopHoc> lopHocList) {
        List<String> strings = new ArrayList<>();
        for(LopHoc a: lopHocList){
            String tmp = a.getTenLop();
            strings.add(tmp);

        }
        return  strings;

    }
    public List<String> getDanhSachTenMonHoc (List<MonHoc> monHocList) {
        List<String> strings = new ArrayList<>();
        for(MonHoc a: monHocList){
            String tmp = a.getTenMon();
            strings.add(tmp);

        }
        return  strings;

    }


    public String getMaLopTheoTen(String tenLop, List<LopHoc> lopHocList) {
        String maLop="";
        for(LopHoc a: lopHocList){
            if( a.getTenLop().equals(tenLop)){
                maLop = a.getMaLop();
                break;
            }
        }
        return  maLop;

    }

    public String getMaMonTheoTen(String tenMon, List<MonHoc> monHocListList) {
        String maMon="";
        for(MonHoc a: monHocListList){
            if( a.getTenMon().equals(tenMon)){
                maMon = a.getMaMon();
                break;
            }
        }
        return  maMon;

    }

    public List<LopHoc> getLopHocTheoTenMon(String tenMon, List<LopHoc> classList, List<MonHoc> listMonHocList){

        List<LopHoc> danhSach = new ArrayList<>();
        String maMon = getMaMonTheoTen(tenMon, listMonHocList);
        System.out.println("MA MON"+ maMon);
        for(LopHoc a: classList){
            if(a.getMaMH().equals(maMon)){
                danhSach.add(a);
            }
        }
        return danhSach;
    }




    // hàm khởi tạo view
    private void initView(){
        spinnerSubject = findViewById(R.id.spinnerSubject);
        spinnerClass = findViewById(R.id.spinnerClass);
        spinnerProcess = findViewById(R.id.spinnerProcess);
        recyclerViewGroups = findViewById(R.id.recyclerViewGroup);

        txtClass = findViewById(R.id.txtClass);
        txtSubject = findViewById(R.id.txtSubject);
        txtProcess = findViewById(R.id.txtProcess);
        updataBaoCao = findViewById(R.id.btnCapNhatTienDo);


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_cao_tien_do);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize views
        initView();

        // Initialize data from database

        subjectList = new ArrayList<>();
        classList = new ArrayList<>();
        groupList = new ArrayList<>();


        try {
            subjectList = monHocDao.getListMonHoc();
            classList = lopHocDao.getAllListLopHoc();

            nameSubject =  getDanhSachTenMonHoc(subjectList);
            nameClass = getDanhSachTenLop(classList);
        }
        catch (SQLiteException e){
            e.printStackTrace();
            Log.e(TAG_NAME_PROCESS, e.getMessage());
        }
        processList = new ArrayList<>();
        processList.add("Week 1");
        processList.add("Week 2");
        processList.add("Week 3");
        processList.add("Week 4");
        processList.add("Week 5");

        // Cài đặt spinner
        ArrayAdapter<String> subjectAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nameSubject);
        ArrayAdapter<String> classAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nameClass);
        ArrayAdapter<String> processAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, processList);

        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        processAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSubject.setAdapter(subjectAdapter);
        spinnerClass.setAdapter(classAdapter);
        spinnerProcess.setAdapter(processAdapter);
        // Spinner khi click và thực hiện xử lí dữ liệu
        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                filterGroups();

                ArrayList<LopHoc> lopHocs = new ArrayList<>();
                nameClass.clear();
                spinnerClass.setSelection(0);
                int item = position + 1;
                lopHocs = lopHocDao.getListLopHocTheoTen("MH"+item);
                for (int j = 0; j < lopHocs.size(); j++) {
                    nameClass.add(lopHocs.get(j).getTenLop());
                }
                classAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                filterGroups();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinnerProcess.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                filterGroups();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        // Set up cho recycleview
        groupList = new ArrayList<>();
        groupList = nhomDao.getAllListNhom();

        // Create and set the adapter for the RecyclerView
        groupAdapter = new TienDoAdapter(groupList);
        groupAdapter.setGroupList(groupList);
        recyclerViewGroups.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewGroups.setAdapter(groupAdapter);


        // thực hiện chức năng khi cập nhật
        updataBaoCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    UpateRecord();
                    Log.i(TAG_NAME_PROCESS, "đã thực hiện thành công cập nhật");
                    Toast.makeText(BaoCaoTienDo.this, "Đã cập nhật dữ liệu thành công", Toast.LENGTH_SHORT).show();

                }catch (SQLiteException e){
                    Log.e(TAG_NAME_PROCESS, "Lỗi");

                }


            }
        });

    }

    // hàm thực hiện chuyển đối dữ liệu nhóm theo spinner
    private void filterGroups() {
        // Get the selected filter values
        String selectedSubject = spinnerSubject.getSelectedItem().toString();
        String selectedClass = spinnerClass.getSelectedItem().toString();
        String selectedProcess = spinnerProcess.getSelectedItem().toString();
        // Apply filters and update the RecyclerView
        List<Nhom> filteredGroups = new ArrayList<>();

        String maMonHoc = getMaMonTheoTen(selectedSubject, subjectList);
        String maLopHoc = getMaLopTheoTen(selectedClass, classList);
        List<Nhom> tmp = new ArrayList<>();
        try {
            tmp = nhomDao.getAllListNhom();

        }catch (SQLiteException e){
            Log.e(TAG_NAME_PROCESS, e.getMessage());
        }


        for (Nhom group : tmp) {
            if (group.getMaMH().equals(maMonHoc) && group.getMaLH().equals(maLopHoc)){
                filteredGroups.add(group);
                group.setTienDo(selectedProcess);
            }
        }
        groupAdapter.setGroupList(filteredGroups);

    }

    private void UpateRecord(){
        String selectedSubject = spinnerSubject.getSelectedItem().toString();
        String selectedClass = spinnerClass.getSelectedItem().toString();
        String selectedProcess = spinnerProcess.getSelectedItem().toString();
        for(Nhom group: groupList){
            String maMonHoc = getMaMonTheoTen(selectedSubject, subjectList);
            String maLopHoc = getMaLopTheoTen(selectedClass, classList);
            if (group.getMaMH().equals(maMonHoc) && group.getMaLH().equals(maLopHoc)){
                group.setTienDo(selectedProcess);
//                nhomDao.capNhatTienDo(group);

                BaoCaoTienDoModel a = new BaoCaoTienDoModel(group.getMaNhom(), group.getTenNhom(), group.getTenDeTai(),
                        group.getTenTruongNhom(), group.getSoThanhVien(), group.getDiemNhom(),group.getMaMH(), group.getMaLH(), group.getTienDo());



                if(group.getBaoCaoTienDoList().contains(a)){
                    try {
                        new BaoCaoTienDoDao(this).capNhatTienDo(group);

                    }catch (SQLiteException e){
                        e.printStackTrace();
                        Log.e(TAG_NAME_PROCESS, e.getMessage());

                    }
                }
                else{
                    try {
                        new BaoCaoTienDoDao(this).themBanGhi(group);

                    }catch (SQLiteException e){
                        Log.e(TAG_NAME_PROCESS, e.getMessage());
                        e.printStackTrace();

                    }
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}