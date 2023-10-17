package com.nhom1.qlbtl.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom1.qlbtl.R;
import com.nhom1.qlbtl.database.BaoCaoTienDoDao;
import com.nhom1.qlbtl.database.DeTaiDao;
import com.nhom1.qlbtl.database.NhomDao;
import com.nhom1.qlbtl.database.SinhVienDao;
import com.nhom1.qlbtl.model.DeTai;
import com.nhom1.qlbtl.model.Nhom;

import java.util.ArrayList;
import java.util.List;

public class NhomAdapter extends RecyclerView.Adapter<NhomAdapter.ViewHolder> {
    Context context;
    List list;

    public NhomAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_nhom, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhomAdapter.ViewHolder holder, int position) {
        Nhom nhom = (Nhom) list.get(position);
        holder.txtTenNhom.setText(nhom.getTenNhom());
        holder.txtTenDeTai.setText("Đề tài: " + nhom.getTenDeTai());
        holder.txtTruongNhom.setText(nhom.getTenTruongNhom());
        int soThanhVien = new NhomDao(context).tongThanhVien(nhom);
        holder.txtSoThanhVienNhom.setText("Số thành viên: " + soThanhVien);

        holder.dongNhom.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.menu_chon_de_tai);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.mnuPhanDeTai:
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                View dialogView = LayoutInflater.from(context).inflate(R.layout.phan_de_tai, null);
                                ListView listChonDeTai = dialogView.findViewById(R.id.listChonDeTai);
                                List<DeTai> deTaiList = new DeTaiDao(context).getListDeTaiTheoMa(nhom.getMaMH());
                                List<String> listDeTai = new ArrayList<>();
                                for (int i = 0; i < deTaiList.size(); i++) {
                                    listDeTai.add(deTaiList.get(i).getTenBTL());
                                }
                                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_checked, listDeTai);
                                listChonDeTai.setAdapter(adapter);
                                builder.setView(dialogView)
                                        .setTitle("Phân đề tài")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                int checkedItemCount = listChonDeTai.getCheckedItemCount();
                                                if (checkedItemCount > 0) {
                                                    SparseBooleanArray checkedItemPositions = listChonDeTai.getCheckedItemPositions();
                                                    for (int i = 0; i < checkedItemPositions.size(); i++) {
                                                        int position = checkedItemPositions.keyAt(i);
                                                        if (checkedItemPositions.valueAt(i)) {
                                                            String tenDeTai = listDeTai.get(position);
                                                            holder.txtTenDeTai.setText(tenDeTai);
                                                            nhom.setTenDeTai(tenDeTai);
                                                            new NhomDao(context).capNhatDeTai(nhom);
                                                            // Thực hiện các xử lý khác với tên đề tài đã chọn
                                                        }
                                                    }
                                                } else {
                                                    // Xử lý khi không có mục nào được chọn
                                                }
                                            }
                                        })
                                        .setNegativeButton("Cancel", null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                                break;
                            default:
                                return false;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenNhom, txtTenDeTai, txtTruongNhom, txtSoThanhVienNhom;
        LinearLayout dongNhom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenNhom = itemView.findViewById(R.id.txtTenNhom);
            txtTenDeTai = itemView.findViewById(R.id.txtTenDeTai);
            txtTruongNhom = itemView.findViewById(R.id.txtTruongNhom);
            txtSoThanhVienNhom = itemView.findViewById(R.id.txtSoThanhVienNhom);
            dongNhom = itemView.findViewById(R.id.dongNhom);
        }
    }
}
