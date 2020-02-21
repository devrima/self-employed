package com.devrim.selfemployed.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ProjectWithActivities {
    @Embedded
    public Project project;
    @Relation(parentColumn = "name",
            entityColumn = "projectName")
    public List<Activity> activities;
}
