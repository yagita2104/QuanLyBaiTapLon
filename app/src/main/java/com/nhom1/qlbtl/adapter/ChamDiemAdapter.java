package com.nhom1.qlbtl.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom1.qlbtl.R;
import com.nhom1.qlbtl.database.DanhSachTruotDao;
import com.nhom1.qlbtl.database.NhomDao;
import com.nhom1.qlbtl.database.SinhVienDao;
import com.nhom1.qlbtl.model.Nhom;
import com.nhom1.qlbtl.model.SinhVien;

import java.util.List;

public class ChamDiemAdapter extends RecyclerView.Adapter<ChamDiemAdapter.NhomViewHolder>{
    private List nListNhom;
    Context context;
    public ChamDiemAdapter(List nListNhom, Context context) {
        this.nListNhom = nListNhom;
        this.context = context;
    }

    public List getnListNhom() {
        return nListNhom;
    }

    public void setnListNhom(List nListNhom) {
        this.nListNhom = nListNhom;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NhomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Triển khai logic để tạo ViewHolder cho mỗi item trong RecyclerView
        View view = LayoutInflater.from(context).inflate(R.layout.dong_cham_diem,parent,false);
        return new NhomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull NhomViewHolder holder, int position) {
        // Triển khai logic để gán dữ liệu từ đối tượng Nhom vào ViewHolder
        Nhom nhom = (Nhom) nListNhom.get(position);
        holder.txt_ten.setText(nhom.getTenNhom());
        holder.txt_diem.setText(String.valueOf(nhom.getDiemNhom()));

        holder.btn_item_diem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_diem, null);
                EditText edtDiem = dialogView.findViewById(R.id.edtDiem);
                builder.setView(dialogView);
                builder.setTitle("Cập nhật điểm");
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            if (!edtDiem.getText().toString().equals("")) {
                                Float diem = Float.valueOf(edtDiem.getText().toString());
                                if (0 <= diem && diem <= 10) {
                                    // Cập nhật điểm vào dữ liệu hoặc thực hiện các xử lý khác
                                    holder.txt_diem.setText(diem + "");
                                    nhom.setDiemNhom(diem);
                                    new NhomDao(context).suaDiem(nhom.getMaNhom(), nhom.getMaMH(), nhom.getMaLH(), diem);
                                    new SinhVienDao(context).choDiem(nhom);
                                    if(diem < 4 && diem >= 0){
                                        List<SinhVien> sinhvienTruot = new SinhVienDao(context).listSinhVienNhom(nhom.getMaMH(), nhom.getMaLH(), nhom.getMaNhom());
                                        try{
                                            for (SinhVien sv: sinhvienTruot) {
                                                new DanhSachTruotDao(context).themSVTruot(sv, "Điểm kiểm tra thấp");
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }


                                    }
                                } else {
                                    Toast.makeText(context, "Điểm phải nhập trong khoảng từ 0 - 10", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(context, "Điểm không được để trống", Toast.LENGTH_SHORT).show();
                            }

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
        });

    }
    @Override
    public int getItemCount() {
        if(nListNhom !=null){
            return nListNhom.size();
        }
        return 0;
    }
    public static class NhomViewHolder extends RecyclerView.ViewHolder{
        // Khai báo các thành phần giao diện trong ViewHolder
        private TextView txt_ten, txt_diem;
        Button btn_item_diem;
        public NhomViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ các thành phần giao diện trong ViewHolder
            txt_ten = itemView.findViewById(R.id.txt_ten);
            txt_diem = itemView.findViewById(R.id.txt_diem);
            btn_item_diem = itemView.findViewById(R.id.btnItemDiem);
        }

    }
}
