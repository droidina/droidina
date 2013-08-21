package be.ordina.droidina.view;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import be.ordina.droidina.R;
import be.ordina.droidina.fsm.view.SchedulesFragment;

/**
 * Created by ordinamobile on 27/06/13.
 */
public abstract class DrawerActivity extends FragmentActivity {

    private String[] drawerListItems;
    private ListView drawerListView;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_activity_layout);

        //Initialize the drawer with listitems
        drawerListItems = getResources().getStringArray(R.array.drawer_menu_items);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, drawerListItems);
        drawerListView = (ListView)findViewById(R.id.left_drawer);
        drawerListView.setAdapter(adapter);

        //Initialize the drawerActivity with a default fragment
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.content_frame);
        if(fragment==null){
            fragment = MainDroidinaFragment.newInstance();
            fm.beginTransaction().add(R.id.content_frame, fragment).commit();
        }

        // Set the list's click listenerss
        drawerListView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                //getActionBar().setTitle();
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerListView);
        //Set visibility of your menu items here, depending on open or closed state of drawer
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        String selectedDrawerItem = drawerListItems[position];
        Fragment fragment=null;
        if("Droidina".equals(selectedDrawerItem)){
            fragment = MainDroidinaFragment.newInstance();
            setContentFragment(fragment);
        }else if("Field service management".equals(selectedDrawerItem)){
            //Create the Field Service Management fragment and display it...
            fragment = SchedulesFragment.newInstance();
            setContentFragment(fragment);
        }

//        // Highlight the selected item, update the title, and close the drawer
//        String title="menuItem"+(position+1);
//        Toast.makeText(DrawerActivity.this, title, Toast.LENGTH_SHORT).show();

        drawerListView.setItemChecked(position, true);
        setTitle(drawerListItems[position]);
        drawerLayout.closeDrawer(drawerListView);
    }

    private void setContentFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

    @TargetApi(11)
    @Override
    public void setTitle(CharSequence title) {
        //mTitle = title;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            getActionBar().setTitle(title);
        }
    }
}
