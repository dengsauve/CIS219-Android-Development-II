package com.cis218.dennis.spring2018lecture;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AppDatabase eventDatabase;
    private EditText edtEventName;
    private EditText edtEventDescription;
    private EditText edtStartDate;
    Date startDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEventName = findViewById( R.id.edtEventName );
        edtEventDescription = findViewById( R.id.edtEventDescription );
        edtStartDate = findViewById( R.id.edtStartDate );

        eventDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "events.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    public void saveEventOnClick( View v ){
        final String eventName = edtEventName.getText().toString();
        final String eventDescription = edtEventDescription.getText().toString();
        String startDateStr = edtStartDate.getText().toString();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);

        try{
            startDate = sdf.parse( startDateStr);
        }catch (ParseException e){
            startDate = new Date();
        }

        Event event = new Event();


        new Thread(new Runnable(){

            @Override
            public void run(){
                Event event = new Event();
                event.setName(eventName);
                event.setDescription(eventDescription);
                event.setAttendees( "Dennis, Andrew, etc.");
                event.setStartDate( startDate );
                event.setEndDate( new Date() );

                eventDatabase.eventDao().addEvent(event);
            }
        }).start();
    }



}
