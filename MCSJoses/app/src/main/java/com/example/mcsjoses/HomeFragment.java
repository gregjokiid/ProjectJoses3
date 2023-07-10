package com.example.mcsjoses;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mcsjoses.adapter.JsonAdapter;
import com.example.mcsjoses.model.Json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView rvNews;
    private ArrayList<Json> list = new ArrayList<>();
    private JsonAdapter newsAdapter;
    private RequestQueue requestQueue;

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

        // Initialize the RequestQueue
        requestQueue = Volley.newRequestQueue(requireContext());

        // Make a request to fetch the JSON data
        fetchData();

        // Set up the RecyclerView
        rvNews.setLayoutManager(new LinearLayoutManager(requireContext()));
        newsAdapter = new JsonAdapter(list);
        rvNews.setAdapter(newsAdapter);

        newsAdapter.setOnItemClickCallBack(new JsonAdapter.OnItemClickCallBack() {
            @Override
            public void onItemClicked(Json data) {
                ShowNews(data);
            }
        });
    }

    private void fetchData() {
        String url = "https://jsonplaceholder.typicode.com/posts"; // Replace with your actual JSON data URL

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Clear the existing list
                            list.clear();

                            // Parse the JSON response
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                int userId = jsonObject.getInt("userId");
                                String title = jsonObject.getString("title");
                                String body = jsonObject.getString("body");

                                // Create a new Json object and add it to the list
                                Json json = new Json(id, userId, title, body);
                                list.add(json);
                            }

                            // Notify the adapter that the data has changed
                            newsAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(requireContext(), "Error retrieving data", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue
        requestQueue.add(request);
    }

    private void ShowNews(Json news) {
        Intent directintent = new Intent(getContext(), DetailActivity.class);
        Toast.makeText(getContext(), String.valueOf(news.getId()), Toast.LENGTH_SHORT).show();
        directintent.putExtra("userId", String.valueOf(news.getUserId()));
        directintent.putExtra("id", String.valueOf(news.getId()));
        directintent.putExtra("title", news.getTitle());
        directintent.putExtra("body", news.getBody());
        startActivity(directintent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Cancel any pending requests when the fragment is destroyed
        requestQueue.cancelAll(this);
    }
}
