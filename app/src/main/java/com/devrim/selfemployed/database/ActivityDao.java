package com.devrim.selfemployed.database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface ActivityDao {

    @Insert
    public void insertActivity(final Activity activity);
}
