package com.cis218.dennis.spring2018lecture;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends BaseActivity {

    private EditText edtEventName;
    private EditText edtEventDescription;
    private EditText edtStartDate;
    private ListView lstViewEvents;
    List<String> tempItems = new ArrayList<String>();
    ArrayAdapter adapter;
    Date startDate;
    LiveData<List<String>>items;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEventName = findViewById( R.id.edtEventName );
        edtEventDescription = findViewById( R.id.edtEventDescription );
        edtStartDate = findViewById( R.id.edtStartDate );
        lstViewEvents = findViewById( R.id.lstViewEvents );

        tempItems.add( "Computer" );
        tempItems.add( "Keyboard" );
        tempItems.add( "Mouse" );

        // Create array adapter
        adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, tempItems);
        lstViewEvents.setAdapter(adapter);

        // Create listener for the scrolling list
        lstViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                toastIt("Reached Click Listener!!!");
            }
        });

        items = eventDatabase.eventDao().getAllNames();
        items.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                adapter = new ArrayAdapter<String>( getApplicationContext(), R.layout.activity_listview, strings);
                lstViewEvents.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                lstViewEvents.invalidateViews();
                lstViewEvents.refreshDrawableState();
            }
        });
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
