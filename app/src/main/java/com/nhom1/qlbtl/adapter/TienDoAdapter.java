package com.nhom1.qlbtl.adapter;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom1.qlbtl.R;
import com.nhom1.qlbtl.database.BaoCaoTienDoDao;
import com.nhom1.qlbtl.database.NhomDao;
import com.nhom1.qlbtl.model.Nhom;

import java.util.List;

public class TienDoAdapter extends RecyclerView.Adapter<TienDoAdapter.GroupViewHolder> implements View.OnCreateContextMenuListener{

    final  String TAG_NAME_PROCESS = "Văn Minh Dương";


    private List<Nhom> groupList;
    private int contextMenuPosition;
    public TienDoAdapter(List<Nhom> groupList) {

    }


    public List<Nhom> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Nhom> groupList) {
        this.groupList = groupList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dong_tien_do, parent, false);

        return new GroupViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        Nhom group = groupList.get(position);
        holder.textViewGroup.setText(String.valueOf(group.getTenNhom()));
        holder.textViewTopicData.setText(String.valueOf(group.getTenDeTai()));

        holder.textViewProcess.setText(String.valueOf(group.getTienDo()));
        holder.textViewStatus.setText(String.valueOf(group.getTrangThai()));

        holder.dongTienDo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.menu_status);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.action_completeProcess:
                                group.setTrangThai("Complete");
//                                new NhomDao(view.getContext()).capNhatTienDo(group);
                                try{
                                    new BaoCaoTienDoDao(view.getContext()).suaBanGhi(group);
                                    notifyDataSetChanged();
                                    Log.i(TAG_NAME_PROCESS, "thực hiện sủa trạng thái của bàn ghi thành công ");

                                }catch (SQLiteException e){
                                    Log.e(TAG_NAME_PROCESS, e.getMessage());
                                    e.printStackTrace();

                                }

                                break;
                            case R.id.action_notCompleteProcess:
                                group.setTrangThai("Not Complete");


                                try{
                                    new BaoCaoTienDoDao(view.getContext()).suaBanGhi(group);
                                    notifyDataSetChanged();
                                    Log.i(TAG_NAME_PROCESS, "thực hiện sủa trạng thái của bàn ghi thành công - not");

                                }catch (SQLiteException e){
                                    Log.e(TAG_NAME_PROCESS, e.getMessage());
                                    e.printStackTrace();

                                }
//                                new NhomDao(view.getContext()).capNhatTienDo(group);

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
        return groupList.size();
    }



    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Options");
        contextMenu.add(Menu.NONE, R.id.action_completeProcess, Menu.NONE, "Complete");
        contextMenu.add(Menu.NONE, R.id.action_notCompleteProcess, Menu.NONE, "Not Complete");

    }


    public class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView textViewGroup,  textViewProcess, textViewStatus, textViewTopic, textViewTopicData;
        LinearLayout dongTienDo;
        public GroupViewHolder(View itemView) {
            super(itemView);
            textViewGroup = itemView.findViewById(R.id.txtGroup);
            textViewGroup = itemView.findViewById(R.id.txtGroup);
            textViewTopicData = itemView.findViewById(R.id.txtTopicData);
            textViewProcess = itemView.findViewById(R.id.txtProcess);
            textViewStatus = itemView.findViewById(R.id.txtStatus);
            textViewTopic = itemView.findViewById(R.id.txtTopic);
            dongTienDo = itemView.findViewById(R.id.dongTienDo);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenuPosition = getAdapterPosition();

        }
    }

}