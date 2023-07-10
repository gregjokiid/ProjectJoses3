package com.example.mcsjoses;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView tvUserId, tvId, tvTitle, tvBody;

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvUserId = findViewById(R.id.tv_userId);
        tvId = findViewById(R.id.tv_id);
        tvTitle = findViewById(R.id.tv_title);
        tvBody = findViewById(R.id.tv_body);

        String userId = getIntent().getStringExtra("userId");
        String id = getIntent().getStringExtra("id");
        String title = getIntent().getStringExtra("title");
        String body = getIntent().getStringExtra("body");

        tvUserId.setText("User Id : " + userId);
        tvId.setText("Id : " + id);
        tvTitle.setText(title);
        tvBody.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        tvBody.setText(body);
    }
}