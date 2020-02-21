package com.devrim.selfemployed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.devrim.selfemployed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.add_activity)
    Button addActivity;
    @BindView(R.id.review_project)
    Button reviewProject;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        addActivity.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddProjectActivityActivity.class)));
        reviewProject.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ReviewProjectActivity.class)));
    }
}
