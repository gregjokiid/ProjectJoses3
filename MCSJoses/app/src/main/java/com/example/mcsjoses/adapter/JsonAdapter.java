package com.example.mcsjoses.adapter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcsjoses.R;
import com.example.mcsjoses.model.Json;
import java.util.ArrayList;

public class JsonAdapter extends RecyclerView.Adapter<com.example.mcsjoses.adapter.JsonAdapter.ListViewHolder> {
    private com.example.mcsjoses.adapter.JsonAdapter.OnItemClickCallBack onItemClickCallBack;

    public void setOnItemClickCallBack(com.example.mcsjoses.adapter.JsonAdapter.OnItemClickCallBack onItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack;
    }

    private final ArrayList<Json> newsList;

    public JsonAdapter(ArrayList<Json> list){
        this.newsList = list;
    }

    @NonNull
    @Override
    public com.example.mcsjoses.adapter.JsonAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_json, viewGroup, false);
        return new com.example.mcsjoses.adapter.JsonAdapter.ListViewHolder(view);
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final com.example.mcsjoses.adapter.JsonAdapter.ListViewHolder holder, int position) {
        Json news = newsList.get(position);
        holder.tvId.setText(String.valueOf(news.getId()));
        holder.tvTitle.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        holder.tvTitle.setText(news.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onItemClickCallBack.onItemClicked(newsList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvTitle;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_item_id);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
        }
    }

    public interface OnItemClickCallBack {
        void onItemClicked(Json data);
    }
}
