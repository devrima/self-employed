package com.devrim.selfemployed.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.devrim.selfemployed.R;
import com.devrim.selfemployed.adapter.ActivityTypeAdapter;
import com.devrim.selfemployed.adapter.ProjectAdapter;
import com.devrim.selfemployed.database.AppDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddProjectActivityActivity extends AppCompatActivity {
    @BindView(R.id.add_project)
    Button addProject;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.projects)
    RecyclerView projects;
    @BindView(R.id.activity_types)
    RecyclerView activityTypes;
    @BindView(R.id.time_spent)
    EditText timeSpent;
    private ProjectAdapter projectAdapter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project_activity);
        ButterKnife.bind(this);

        projectAdapter = new ProjectAdapter();
        projects.setAdapter(projectAdapter);

        activityTypes.setAdapter(new ActivityTypeAdapter());

        //TODO: implement the selection logic

        addProject.setOnClickListener(v -> {
            //TODO: implement data retrieval and database insertion
            /*new Thread( () -> {
                Activity activity = new Activity();
                activity.
                AppDatabase.getDatabase(this).getActivityDao().insertActivity(););
                runOnUiThread(() -> projectAdapter.notifyDataSetChanged());
            }).start();*/
        });

        submit.setOnClickListener(v -> {
            //TODO: add to data
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO: LiveData
        new Thread( () -> {
            projectAdapter.setProjects(AppDatabase.getDatabase(this).getProjectDao().loadAllProjects());
            runOnUiThread(() -> projectAdapter.notifyDataSetChanged());
        }).start();
    }
}
