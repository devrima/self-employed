package com.devrim.selfemployed.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.devrim.selfemployed.converter.ActivityTypeConverter;

@Database(entities = {Project.class, Activity.class}, version = 1)
@TypeConverters({ActivityTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract ActivityDao getActivityDao();
    public abstract ProjectDao getProjectDao();
}
