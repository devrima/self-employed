package com.devrim.selfemployed.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.devrim.selfemployed.R;
import com.devrim.selfemployed.adapter.ActivityAdapter;
import com.devrim.selfemployed.adapter.ProjectAdapter;
import com.devrim.selfemployed.database.AppDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewProjectActivity extends AppCompatActivity {

    @BindView(R.id.projects)
    RecyclerView projects;

    @BindView(R.id.activities)
    RecyclerView activities;
    private ProjectAdapter projectAdapter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_project);
        ButterKnife.bind(this);

        final ActivityAdapter activityAdapter = new ActivityAdapter();
        activities.setAdapter(activityAdapter);

        projectAdapter = new ProjectAdapter(project -> {
            new Thread(() -> {
                //TODO: a new query for specific project?
                //activities.setActivities(AppDatabase.getDatabase(this).getProjectDao().getProjectsWithActivities());
                runOnUiThread(() -> activityAdapter.notifyDataSetChanged());
            }).start();
        });
        projects.setAdapter(projectAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO: LiveData
        new Thread(() -> {
            projectAdapter.setProjects(AppDatabase.getDatabase(this).getProjectDao().loadAllProjects());
            runOnUiThread(() -> projectAdapter.notifyDataSetChanged());
        }).start();
    }
}
