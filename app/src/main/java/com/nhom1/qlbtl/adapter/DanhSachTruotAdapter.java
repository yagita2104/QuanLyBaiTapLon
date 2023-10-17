package com.nhom1.qlbtl.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom1.qlbtl.R;
import com.nhom1.qlbtl.model.DanhSachTruot;

import java.util.List;

public class DanhSachTruotAdapter extends RecyclerView.Adapter<DanhSachTruotAdapter.StudentViewHolder> {
    private final List<DanhSachTruot> mListStudent;

    public DanhSachTruotAdapter(List<DanhSachTruot> mListStudent) {
        this.mListStudent = mListStudent;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_danh_sach_truot,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        DanhSachTruot student = mListStudent.get(position);
        if (student == null){
            return;
        }
        holder.maSV.setText(String.valueOf(student.getMaSV()));
        holder.tenSV.setText(student.getHoVaTen());
        holder.tenMon.setText(student.getTenMon());
        holder.lyDo.setText(student.getLyDo());


    }

    @Override
    public int getItemCount() {
        if (mListStudent != null){
            return mListStudent.size();
        }
        return 0;
    }
    public static class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView maSV, tenSV, tenMon, lyDo;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            maSV = itemView.findViewById(R.id.tv_ma);
            tenSV = itemView.findViewById(R.id.tv_ten);
            tenMon = itemView.findViewById(R.id.tv_mon);
            lyDo = itemView.findViewById(R.id.tv_lyDo);
        }
    }
}
