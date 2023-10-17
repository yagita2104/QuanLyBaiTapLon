package com.nhom1.qlbtl.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom1.qlbtl.R;
import com.nhom1.qlbtl.activity.DanhSachLop;
import com.nhom1.qlbtl.activity.DanhSachMonHoc;
import com.nhom1.qlbtl.model.MonHoc;

import org.w3c.dom.Text;

import java.util.List;

public class MonHocAdapter extends RecyclerView.Adapter<MonHocAdapter.ViewHolder> {
    List list;
    Context context;

    public MonHocAdapter(Context context, List list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_mon_hoc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonHocAdapter.ViewHolder holder, int position) {
        MonHoc monHoc = (MonHoc) list.get(position);
        holder.txtTenMonHoc.setText(monHoc.getTenMon());
        holder.txtThoiGianHoc.setText("Thời gian học: "  + monHoc.getThoiGianHoc() + "");
        holder.txtSoLop.setText("Số lớp học: " + monHoc.getSoLuongLop() + "");
        holder.dongMonHoc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, DanhSachLop.class);
                intent.putExtra("maMH", monHoc.getMaMon());
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
        TextView txtTenMonHoc, txtThoiGianHoc, txtSoLop;
        LinearLayout dongMonHoc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenMonHoc = itemView.findViewById(R.id.txtTenMonHoc);
            txtThoiGianHoc = itemView.findViewById(R.id.txtThoiGianHoc);
            txtSoLop = itemView.findViewById(R.id.txtSoLop);
            dongMonHoc = itemView.findViewById(R.id.dongMonHoc);

        }
    }
}
