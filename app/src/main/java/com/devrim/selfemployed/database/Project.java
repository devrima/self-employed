package com.devrim.selfemployed.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Project {
    @PrimaryKey
    @NonNull
    public String name;
}
