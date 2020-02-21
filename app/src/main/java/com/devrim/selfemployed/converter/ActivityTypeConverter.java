package com.devrim.selfemployed.converter;

import androidx.room.TypeConverter;

import com.devrim.selfemployed.model.ActivityType;

public class ActivityTypeConverter {
    @TypeConverter
    public static ActivityType fromString(final String string) {
        return string == null ? null : ActivityType.valueOf(string);
    }

    @TypeConverter
    public static String ActivityTypeToString(final ActivityType activityType) {
        return activityType == null ? null : activityType.name();
    }
}
