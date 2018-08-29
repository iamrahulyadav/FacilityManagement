package com.shoaibnwar.facilitymanagement.Activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.shoaibnwar.facilitymanagement.ChatSystem.ComplexChat.MainActivity;
import com.shoaibnwar.facilitymanagement.ChatSystem.ComplexChat.ui.*;
import com.shoaibnwar.facilitymanagement.ChatSystem.SimgleChat.Login;
import com.shoaibnwar.facilitymanagement.R;

public class BaseActvitvityForDrawer extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    MenuItem nav_item_username;
    MenuItem nav_item_chat;
    MenuItem nav_item_chat_rrom;


    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_actvitvity_for_drawer);
        view = new View(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;
        mNavigationView.setItemIconTintList(null);

        // get menu from navigationView
        Menu menu = mNavigationView.getMenu();


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();


                if (menuItem.getItemId() == R.id.nav_item_username) {
                }
                if (menuItem.getItemId() == R.id.nav_item_chat) {
                    Intent i = new Intent(BaseActvitvityForDrawer.this, LiveSupportActivity.class);
                    startActivity(i);
                }
                if (menuItem.getItemId() == R.id.nav_item_chat_rrom) {
                    Intent i = new Intent(BaseActvitvityForDrawer.this, com.shoaibnwar.facilitymanagement.ChatSystem.ComplexChat.ui.LoginActivity.class);
                    startActivity(i);
                }

                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


    }//end on Create

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}