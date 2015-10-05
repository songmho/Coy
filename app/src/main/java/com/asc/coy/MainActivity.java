package com.asc.coy;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        navigationView=(NavigationView)findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                return changeDrawerMenu(menuItem);
            }
        });
        toolbar.setNavigationIcon(R.drawable.drawericon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        Button bt_dong=(Button)findViewById(R.id.bt_dong);
        Button bt_hang=(Button)findViewById(R.id.bt_hang);
        Button bt_fav=(Button)findViewById(R.id.bt_fav);
        Button bt_com=(Button)findViewById(R.id.bt_com);

        bt_dong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    private boolean changeDrawerMenu(MenuItem menuItem) {
        if(menuItem.getGroupId()==R.id.group_my){
            navigationView.getMenu().setGroupCheckable(R.id.group_my,true,true);
            navigationView.getMenu().setGroupCheckable(R.id.group_club,false,true);
            navigationView.getMenu().setGroupCheckable(R.id.group_setting,false,true);
        }
        else if(menuItem.getGroupId()==R.id.group_club){
            navigationView.getMenu().setGroupCheckable(R.id.group_club,true,true);
            navigationView.getMenu().setGroupCheckable(R.id.group_my,false,true);
            navigationView.getMenu().setGroupCheckable(R.id.group_setting,false,true);
        }
        else if(menuItem.getGroupId()==R.id.group_setting){
            navigationView.getMenu().setGroupCheckable(R.id.group_club,false,true);
            navigationView.getMenu().setGroupCheckable(R.id.group_my,false,true);
            navigationView.getMenu().setGroupCheckable(R.id.group_setting,true,true);
        }
        menuItem.setChecked(true);
        switch (menuItem.getItemId()){
            case R.id.mypage:
                drawerLayout.closeDrawers();
                return true;
            case R.id.club_pick:
                drawerLayout.closeDrawers();
                return true;
            case R.id.all_club:
                drawerLayout.closeDrawers();
                return true;
            case R.id.club_event:
                drawerLayout.closeDrawers();
                return true;
            case R.id.setting:
                drawerLayout.closeDrawers();
                return true;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
