package com.devrim.selfemployed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.devrim.selfemployed.R;
import com.devrim.selfemployed.adapter.ActivityTypeAdapter;
import com.devrim.selfemployed.adapter.ProjectAdapter;
import com.devrim.selfemployed.database.Activity;
import com.devrim.selfemployed.database.AppDatabase;
import com.devrim.selfemployed.database.Project;
import com.devrim.selfemployed.model.ActivityType;

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

        final ActivityTypeAdapter activityTypeAdapter = new ActivityTypeAdapter();
        activityTypes.setAdapter(activityTypeAdapter);

        addProject.setOnClickListener(v -> {
           startActivity(new Intent(AddProjectActivityActivity.this, AddProjectActivity.class));
        });

        submit.setOnClickListener(v -> {
            new Thread( () -> {
                final Project selectedProject = projectAdapter.getSelectedProject();
                final ActivityType selectedActivityType = activityTypeAdapter.getSelectedActivityType();
                if (selectedProject == null || selectedActivityType == null) {
                    runOnUiThread(() -> Toast.makeText(this, R.string.choose_error, Toast.LENGTH_LONG).show());
                    return;
                }
                Integer timeSpentAmount;
                try {
                    timeSpentAmount = Integer.valueOf(timeSpent.getText().toString());
                } catch (NumberFormatException nfe) {
                    runOnUiThread(() -> Toast.makeText(this, R.string.time_error, Toast.LENGTH_LONG).show());
                    return;
                }
                Activity activity = new Activity();
                activity.projectName = selectedProject.name;
                activity.activityType = selectedActivityType;
                activity.timeSpent = timeSpentAmount;
                AppDatabase.getDatabase(this).getActivityDao().insertActivity(activity);
                runOnUiThread(() -> Toast.makeText(this, R.string.saved, Toast.LENGTH_LONG).show());

                //TODO: kill or reset?
            }).start();
        });
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
