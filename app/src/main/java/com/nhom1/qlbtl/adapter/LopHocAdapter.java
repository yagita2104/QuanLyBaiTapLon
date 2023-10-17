package com.nhom1.qlbtl.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nhom1.qlbtl.R;
import com.nhom1.qlbtl.activity.DanhSachLop;
import com.nhom1.qlbtl.activity.DanhSachSinhVien;
import com.nhom1.qlbtl.database.LopHocDao;
import com.nhom1.qlbtl.model.LopHoc;

import java.util.List;

public class LopHocAdapter extends RecyclerView.Adapter<LopHocAdapter.ViewHolder> {
    List list;
    Context context;
    public LopHocAdapter(Context context, List list) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_lop, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LopHoc lopHoc = (LopHoc) list.get(position);
        holder.txtTenLop.setText(lopHoc.getTenLop());
        holder.txtLopTruong.setText(lopHoc.getLoptruong());

        int soNhom = new LopHocDao(context).tongNhom(lopHoc);
        lopHoc.setSoNhom(soNhom);
        holder.txtSoNhom.setText("Có: " + soNhom+ " nhóm");

        int soSV = new LopHocDao(context).tongSoSVLop(lopHoc);
        lopHoc.setSoLuongSV(soSV);
        holder.txtSoLuongSV.setText("Có:  " + soSV + " sinh viên");

        holder.dongLopHoc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, DanhSachSinhVien.class);
                intent.putExtra("maMH", lopHoc.getMaMH());
                intent.putExtra("maLH", lopHoc.getMaLop());
                context.startActivity(intent);
                return false;
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenLop, txtLopTruong, txtSoNhom, txtSoLuongSV;
        LinearLayout dongLopHoc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenLop = itemView.findViewById(R.id.txtTenLopHoc);
            txtLopTruong = itemView.findViewById(R.id.txtLopTruong);
            txtSoNhom = itemView.findViewById(R.id.txtSoNhom);
            txtSoLuongSV = itemView.findViewById(R.id.txtSoLuongSinhVien);
            dongLopHoc = itemView.findViewById(R.id.dongLop);
        }
    }
}
