package be.ordina.droidina.view;

import android.os.Bundle;
import android.view.Menu;

import be.ordina.droidina.R;

public class DroidinaActivity extends DrawerActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.droidina, menu);
        return true;
    }
    
}
