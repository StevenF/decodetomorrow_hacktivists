package com.frio.steven.decodetomorrow;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.frio.steven.decodetomorrow.NavigationDrawer.NavFragmentFive;
import com.frio.steven.decodetomorrow.NavigationDrawer.NavFragmentFour;
import com.frio.steven.decodetomorrow.NavigationDrawer.NavFragmentOne;
import com.frio.steven.decodetomorrow.NavigationDrawer.NavFragmentSix;
import com.frio.steven.decodetomorrow.NavigationDrawer.NavFragmentThree;
import com.frio.steven.decodetomorrow.NavigationDrawer.NavFragmentTwo;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavFragmentOne navFragmentOne = new NavFragmentOne();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.navcontainer, navFragmentOne);
        fragmentTransaction.commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.navigation, menu);
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

        if (id == R.id.nav_one) {

            NavFragmentOne navFragmentOne = new NavFragmentOne();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.navcontainer, navFragmentOne);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_two) {

            NavFragmentTwo navFragmentTwo = new NavFragmentTwo();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.navcontainer, navFragmentTwo);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_three) {

            NavFragmentThree navFragmentThree = new NavFragmentThree();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.navcontainer, navFragmentThree);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_four) {

            NavFragmentFour navFragmentFour = new NavFragmentFour();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.navcontainer, navFragmentFour);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_five) {

            NavFragmentFive navFragmentFive = new NavFragmentFive();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.navcontainer, navFragmentFive);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_six) {

            NavFragmentSix navFragmentSix = new NavFragmentSix();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.navcontainer, navFragmentSix);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
