package com.androidizate.clase6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FiveFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_first_fragment) {
            fragment = new FirstFragment();
        } else if (id == R.id.nav_second_fragment) {
            fragment = new SecondFragment();
        } else if (id == R.id.nav_third_fragment) {
            fragment = new ThirdFragment();
        } else if (id == R.id.nav_four_fragment) {
            fragment = new FourFragment();
        } else if (id == R.id.nav_maps_fragment) {
            fragment = new FiveFragment();
        }

        String loSimpleName = fragment.getClass().getSimpleName();

        if (fragment != null && !fragmentExists(loSimpleName)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, loSimpleName).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean fragmentExists(String pSimpleName) {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment != null && pSimpleName.equals(fragment.getClass().getSimpleName()))
                return true;
        }
        return false;
    }

    @Override
    public void onFragmentInteraction() {
        startActivity(new Intent(MainActivity.this, MapsActivity.class));
    }
}
