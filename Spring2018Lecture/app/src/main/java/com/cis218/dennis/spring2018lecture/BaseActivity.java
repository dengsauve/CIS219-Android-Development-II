package com.cis218.dennis.spring2018lecture;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by dennis on 4/25/18.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState,
                         @Nullable PersistableBundle persistentState){
        super.onCreate( savedInstanceState, persistentState);
        setContentView( R.layout.activity_base );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate Menu
        getMenuInflater().inflate( R.menu.menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch( item.getItemId() ){
            case R.id.menuEdit :
                // Switch to menu edit
                Intent intent = new Intent(this, EditActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuViewAll :
                // Switch to view all
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default :
                return super.onOptionsItemSelected( item );
        }
    }

    public void toastIt(String msg ){
        Toast.makeText(getApplicationContext(),
                msg,
                Toast.LENGTH_SHORT ).show();
    }

}
