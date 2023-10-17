package com.nhom1.qlbtl.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom1.qlbtl.R;
import com.nhom1.qlbtl.database.DeTaiDao;
import com.nhom1.qlbtl.model.DeTai;

import java.util.ArrayList;

public class DeTaiAdapter extends RecyclerView.Adapter<DeTaiAdapter.ViewHolder> {
    private ArrayList<DeTai> btls;
    private Context context;

    public ArrayList<DeTai> getBtls() {
        return btls;
    }

    public void setBtls(ArrayList<DeTai> btls) {
        this.btls = btls;
        notifyDataSetChanged();
    }

    public DeTaiAdapter(ArrayList<DeTai> btls, Context context) {
        this.btls = btls;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.dong_de_tai,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {
        DeTai deTai = btls.get(pos);
        holder.txtTenMonHoc.setText(deTai.getTenMonHoc());
        holder.txtTenBTL.setText(deTai.getTenBTL());
        holder.dongDeTai.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.menu_de_tai);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.mnuXoaDeTai:
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setTitle("Cảnh báo");
                                builder.setMessage("Bạn có chắc chắn muốn xoá đề tài này chứ?");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        new DeTaiDao(context).xoaDeTai(deTai);
                                        btls.remove(holder.getAdapterPosition());
                                        notifyDataSetChanged();
                                    }
                                });
                                builder.setNegativeButton("Cancel", null);
                                builder.create().show();
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
        return btls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenMonHoc;
        TextView txtTenBTL;
        LinearLayout dongDeTai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenMonHoc = itemView.findViewById(R.id.txtTenMon);
            txtTenBTL = itemView.findViewById(R.id.txtTenBTL);
            dongDeTai = itemView.findViewById(R.id.dongDeTai);
        }
    }
}
