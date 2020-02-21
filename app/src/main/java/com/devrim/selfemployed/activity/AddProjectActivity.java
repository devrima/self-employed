package com.devrim.selfemployed.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.devrim.selfemployed.R;
import com.devrim.selfemployed.database.AppDatabase;
import com.devrim.selfemployed.database.Project;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddProjectActivity extends AppCompatActivity {

    @BindView(R.id.activity_name)
    EditText activityName;
    @BindView(R.id.submit)
    Button submit;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        ButterKnife.bind(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (TextUtils.isEmpty(activityName.getText())) {
                    Toast.makeText(AddProjectActivity.this, R.string.name_error, Toast.LENGTH_LONG).show();
                    return;
                }
                new Thread(() -> {
                    final Project project = new Project();
                    project.name = activityName.getText().toString();
                    AppDatabase.getDatabase(AddProjectActivity.this).getProjectDao().insertProject(project);
                    runOnUiThread(() -> Toast.makeText(AddProjectActivity.this, R.string.activity_created, Toast.LENGTH_LONG).show());
                    supportFinishAfterTransition();
                }).start();
            }
        });
    }
}
