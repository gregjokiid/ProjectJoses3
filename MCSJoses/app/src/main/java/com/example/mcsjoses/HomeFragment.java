package com.example.mcsjoses;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mcsjoses.adapter.JsonAdapter;
import com.example.mcsjoses.model.Json;
import com.example.mcsjoses.model.JsonData;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView rvNews;
    private ArrayList<Json> list = new ArrayList<>();
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvNews = view.findViewById(R.id.rv_news);
        rvNews.setHasFixedSize(true);

        list.addAll(JsonData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList() {
        rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        JsonAdapter newsAdapter = new JsonAdapter(list);
        rvNews.setAdapter(newsAdapter);

        newsAdapter.setOnItemClickCallBack(new JsonAdapter.OnItemClickCallBack() {
            @Override
            public void onItemClicked(Json data) {
                ShowNews(data);
            }
        });
    }

    private void ShowNews(Json news) {
        Intent directintent = new Intent(getContext(), DetailActivity.class);
        Toast.makeText(getContext(), news.getId(), Toast.LENGTH_SHORT).show();
//        directintent.putExtra("news_image", news.getNewsImage());
//        directintent.putExtra("news_title", news.getNewsTitle());
//        directintent.putExtra("news_author", news.getNewsAuthor());
//        directintent.putExtra("news_detail", news.getNewsDetail());
        startActivity(directintent);
    }
}