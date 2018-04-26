package com.cis218.dennis.spring2018lecture;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by dennis on 4/25/18.
 */

@Database( entities = {Event.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract EventDao eventDao();


}
