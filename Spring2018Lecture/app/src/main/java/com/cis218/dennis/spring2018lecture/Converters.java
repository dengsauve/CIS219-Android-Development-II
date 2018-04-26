package com.cis218.dennis.spring2018lecture;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by dennis on 4/25/18.
 */

public class Converters {

    @TypeConverter
    public Date fromTimeStamp( Long value ){
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public Long dateToTimestamp( Date date ){
        return date == null ? null : date.getTime();
    }
}
