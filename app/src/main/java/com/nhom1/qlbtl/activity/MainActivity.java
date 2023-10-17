package com.nhom1.qlbtl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom1.qlbtl.R;

public class MainActivity extends AppCompatActivity {
    Button btnMonHoc, btnChamDiem, btnBaoCaoTienDo, btnBTL, btnDanhSachTruot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        addAction();
    }
    public void mapping(){
        btnMonHoc = findViewById(R.id.btnMonHoc);
        btnChamDiem = findViewById(R.id.btnChamDiem);
        btnBaoCaoTienDo = findViewById(R.id.btnBaoCaoTienDo);
        btnBTL = findViewById(R.id.btnBTL);
        btnDanhSachTruot = findViewById(R.id.btnDanhSachTruot);
    }
    private void addAction() {
        btnMonHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DanhSachMonHoc.class));

            }
        });
        btnChamDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ChamDiemActivity.class));
                ;
            }
        });
        btnBaoCaoTienDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BaoCaoTienDo.class));

            }
        });
        btnBTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DanhSachDeTai.class));

            }
        });
        btnDanhSachTruot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DanhSachTruotActivity.class));

            }
        });
    }


}