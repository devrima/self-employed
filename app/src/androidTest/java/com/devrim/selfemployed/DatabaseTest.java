package com.devrim.selfemployed;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.devrim.selfemployed.database.Activity;
import com.devrim.selfemployed.database.ActivityDao;
import com.devrim.selfemployed.database.AppDatabase;
import com.devrim.selfemployed.database.Project;
import com.devrim.selfemployed.database.ProjectDao;
import com.devrim.selfemployed.database.ProjectWithActivities;
import com.devrim.selfemployed.model.ActivityType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private ProjectDao projectDao;
    private ActivityDao activityDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        projectDao = db.getProjectDao();
        activityDao = db.getActivityDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeProjectAndReadInList() throws Exception {
        final Project project = new Project();
        project.name = "Test Project";
        projectDao.insertProject(project);
        List<Project> projects = projectDao.loadAllProjects();
        assertThat(projects.size(), equalTo(1));
        assertThat(projects.get(0).name, equalTo("Test Project"));
    }

    @Test
    public void writeActivitiesAndReadInList() throws Exception {
        final Project project = new Project();
        project.name = "Test Project";
        projectDao.insertProject(project);
        createActivity(project.name);
        createActivity(project.name);
        createActivity(project.name);
        final List<ProjectWithActivities> projectsWithActivities = projectDao.getProjectsWithActivities();
        assertThat(projectsWithActivities.size(), equalTo(1));
        assertThat(projectsWithActivities.get(0).activities.size(), equalTo(3));
    }

    private void createActivity(final String projectName) {
        final Activity activity = new Activity();
        activity.activityType = ActivityType.DEVELOPMENT;
        activity.projectName = projectName;
        activity.timeSpent = 1;
        activityDao.insertActivity(activity);
    }
}
