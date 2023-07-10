package com.example.mcsjoses.adapter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcsjoses.R;
import com.example.mcsjoses.model.Data;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ListViewHolder> {
    private OnItemClickCallBack onItemClickCallBack;

    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack;
    }

    private final ArrayList<Data> medicineList;

    public DataAdapter(ArrayList<Data> list, OnItemClickCallBack onItemClickCallBack){
        this.medicineList = list;
        this.onItemClickCallBack = onItemClickCallBack;
    }

    @NonNull
    @Override
    public DataAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_data, viewGroup, false);
        return new DataAdapter.ListViewHolder(view);
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Data medicine = medicineList.get(position);
        holder.tvUserId.setText(medicine.getUserId());
        holder.tvId.setText(medicine.getId());
        holder.tvTitle.setText(medicine.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallBack.onItemClicked(medicineList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserId, tvId, tvTitle;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserId = itemView.findViewById(R.id.tv_item_userId);
            tvId = itemView.findViewById(R.id.tv_item_id);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
        }
    }

    public interface OnItemClickCallBack {
        void onItemClicked(Data data);
    }
}