package com.cis218.dennis.spring2018lecture;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends BaseActivity {

    EditText edtEditName, edtEditDescription, edtEditAttendees;
    LiveData<Event> event;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edtEditName = findViewById( R.id.edtEditName);
        edtEditDescription = findViewById( R.id.edtEditDescription);
        edtEditAttendees = findViewById( R.id.edtEditAttendees);

        // LiveData
        event = eventDatabase.eventDao().findByRecordNum(2);
        event.observe( this, new Observer<Event>(){
            @Override
            public void onChanged(@Nullable Event event){
                edtEditName.setText( event.getName() );
                edtEditDescription.setText( event.getDescription() );
                edtEditAttendees.setText( event.getAttendees() );
            }
        });
    }
}
