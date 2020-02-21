package com.devrim.selfemployed.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.devrim.selfemployed.model.ActivityType;

@Entity
public class Activity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String projectName;

    public int timeSpent;

    public ActivityType activityType;
}
