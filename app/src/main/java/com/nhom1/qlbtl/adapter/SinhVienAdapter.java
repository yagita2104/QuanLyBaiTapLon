package com.nhom1.qlbtl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom1.qlbtl.R;
import com.nhom1.qlbtl.database.SinhVienDao;
import com.nhom1.qlbtl.model.SinhVien;

import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.ViewHolder> {
    Context context;
    List list;

    public SinhVienAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List getList() {
        return list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_sinh_vien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SinhVien sinhVien = (SinhVien) list.get(position);
        holder.txtTenSinhVien.setText(sinhVien.getTenSinhVien());
        holder.txtThuocNhom.setText("Thuộc nhóm: " + sinhVien.getThuocNhom());
        float diem = new SinhVienDao(context).layDiem(sinhVien);
        holder.txtDiem.setText("Điểm: " + diem);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenSinhVien, txtThuocNhom, txtDiem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSinhVien = itemView.findViewById(R.id.txtTenSinhVien);
            txtThuocNhom = itemView.findViewById(R.id.txtThuocNhom);
            txtDiem = itemView.findViewById(R.id.txtDiemSinhVien);
        }
    }
}
