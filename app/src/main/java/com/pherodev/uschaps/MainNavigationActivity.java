package com.pherodev.uschaps;

import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainNavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, InputEventFragment.OnFragmentInteractionListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        FloatingActionButton fabCareer = (FloatingActionButton) findViewById(R.id.fab_career);
        fabCareer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainNavigationActivity.this, "Career", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabParty = (FloatingActionButton) findViewById(R.id.fab_party);
        fabParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainNavigationActivity.this, "Party", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabFood = (FloatingActionButton) findViewById(R.id.fab_food);
        fabFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainNavigationActivity.this, "Food", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabClub = (FloatingActionButton) findViewById(R.id.fab_club);
        fabClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainNavigationActivity.this, "Club", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabSport = (FloatingActionButton) findViewById(R.id.fab_sport);
        fabSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainNavigationActivity.this, "Sport", Toast.LENGTH_SHORT).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        TextView emailTextView = (TextView) header.findViewById(R.id.textView_user_email);

        emailTextView.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment;
        Bundle args;


        if (id == R.id.nav_map) {
            // Handle the camera action
            // Create a new fragment and specify the planet to show based on position
            fragment = new InputEventFragment();
            args = new Bundle();
            fragment.setArguments(args);

            // Insert the fragment by replacing any existing fragment
            fragmentManager.beginTransaction()
                    .replace(R.id.main_nav_content, fragment)
                    .commit();

            // TODO: Highlight the selected item, update the title, and close the drawer


        } else if (id == R.id.nav_feed) {
            //
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_settings) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //TODO: Move the following function to the Maps fragment
    private void selectItem (String select) {

        if (select.equals("FOOD")) {

        } else if (select.equals("PARTY")) {

        } else if (select.equals("CAREER")) {

        } else if (select.equals("CLUB")) {

        } else if (select.equals("SPORT")) {

        } else {

        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment = new InputEventFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_nav_content, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        setTitle("BlankFragment");
        drawer.closeDrawer(drawer);
    }

    @Override
    public void setTitle(CharSequence title) {
        //getSupportActionBar().setTitle(title);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
