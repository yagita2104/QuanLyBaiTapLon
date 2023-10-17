package com.nhom1.qlbtl.adapter;

import android.content.Context;
import android.graphics.ColorSpace;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom1.qlbtl.R;
import com.nhom1.qlbtl.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class PhanNhomAdapter extends RecyclerView.Adapter<PhanNhomAdapter.ViewHolder>{
    Context context;
    List list;
    List<SinhVien> selectedStudents = new ArrayList<>();
    private SparseBooleanArray itemStateArray= new SparseBooleanArray();
    public PhanNhomAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_phan_nhom, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SinhVien sinhVien = (SinhVien) list.get(position);
        holder.txtTenSinhVien.setText(sinhVien.getTenSinhVien());
        holder.txtThuocNhom.setText("Thuộc nhóm: " + sinhVien.getThuocNhom());
        if(sinhVien.getDiem() == -1){
            holder.txtDiem.setText("Chưa có điểm");
        } else {
            holder.txtDiem.setText("Điểm: " + sinhVien.getDiem());
        }

        holder.cbPhanNhom.setOnCheckedChangeListener(null); //
        holder.cbPhanNhom.setChecked(itemStateArray.get(position, false));

        holder.cbPhanNhom.setOnCheckedChangeListener((buttonView, isChecked) -> {
            itemStateArray.put(position, isChecked);
        });
    }


    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    void loadItems(List<SinhVien> tournaments) {
        this.list = tournaments;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtTenSinhVien, txtThuocNhom, txtDiem;
        CheckBox cbPhanNhom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSinhVien = itemView.findViewById(R.id.txtTenSinhVienPN);
            txtThuocNhom = itemView.findViewById(R.id.txtThuocNhomPN);
            txtDiem = itemView.findViewById(R.id.txtDiemSinhVienPN);
            cbPhanNhom = itemView.findViewById(R.id.cbPhanNhom);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            if (!itemStateArray.get(adapterPosition, false)) {
                cbPhanNhom.setChecked(true);
                itemStateArray.put(adapterPosition, true);
            }
            else  {
                cbPhanNhom.setChecked(false);
                itemStateArray.put(adapterPosition, false);
            }
        }
        void bind(int position) {
            // use the sparse boolean array to check
            if (!itemStateArray.get(position, false)) {
                cbPhanNhom.setChecked(false);
            } else {
                cbPhanNhom.setChecked(true);
            }
        }
    }
}
