package com.cis218.dennis.spring2018lecture;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cis218.dennis.spring2018lecture.Event;
import java.util.List;

@Dao
public interface EventDao{

    @Query( "select * from events" )
    List<Event> getAll();

    @Query( "select * from events where name=:event_name limit 1")
    Event findByName( String event_name );

    @Insert
    void addEvent(Event event);

    @Update
    void updateEvent(Event event);

    @Delete
    void deleteEvent(Event event);


}