package com.devrim.selfemployed.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.devrim.selfemployed.converter.ActivityTypeConverter;

@Database(entities = {Project.class, Activity.class}, version = 1)
@TypeConverters({ActivityTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract ActivityDao getActivityDao();

    public abstract ProjectDao getProjectDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
