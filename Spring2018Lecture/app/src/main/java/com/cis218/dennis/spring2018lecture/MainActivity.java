package com.cis218.dennis.spring2018lecture;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private AppDatabase eventDatabase;
    private EditText edtEventName;
    private EditText edtEventDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEventName = findViewById( R.id.edtEventName );
        edtEventDescription = findViewById( R.id.edtEventDescription );

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

        Event event = new Event();


        new Thread(new Runnable(){

            @Override
            public void run(){
                Event event = new Event();
                event.setName(eventName);
                event.setDescription(eventDescription);

                eventDatabase.eventDao().addEvent(event);
            }
        }).start();
    }



}
