package com.devrim.selfemployed.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface ProjectDao {
    @Transaction
    @Query("SELECT * FROM Project")
    public List<ProjectWithActivities> getProjectsWithActivities();

    @Insert
    public void insertProject(final Project project);

    @Query("SELECT * FROM project")
    public List<Project> loadAllProjects();
}
