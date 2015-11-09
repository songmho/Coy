package com.asc.coy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    LinearLayout bt_club;
    LinearLayout bt_event;
    LinearLayout bt_fav;
    LinearLayout bt_com;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences shpref=getSharedPreferences("myPref",0);
        int count=shpref.getInt("Count",-100);
        if(count==-100){
            startActivity(new Intent(MainActivity.this,SplashActivity.class));
            finish();
            count=1;
        }
        else{
            count++;
        }SharedPreferences.Editor editor=shpref.edit();
        editor.putInt("Count", count);
        editor.commit();


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

        bt_club =(LinearLayout)findViewById(R.id.bt_club);
        bt_event =(LinearLayout)findViewById(R.id.bt_event);
        bt_fav=(LinearLayout)findViewById(R.id.bt_fav);
        bt_com=(LinearLayout)findViewById(R.id.bt_com);

        bt_club.setOnClickListener(this);
        bt_event.setOnClickListener(this);
        bt_fav.setOnClickListener(this);
        bt_com.setOnClickListener(this);


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
                startActivity(new Intent(MainActivity.this, MypageAcitivty.class));
                drawerLayout.closeDrawers();
                return true;
            case R.id.club_pick:
                startActivity(new Intent(MainActivity.this,Club_Pick_Acitivity.class));
                drawerLayout.closeDrawers();
                return true;
            case R.id.all_club:
                startActivity(new Intent(MainActivity.this, All_Club_Acitivity.class));
                drawerLayout.closeDrawers();
                return true;
            case R.id.club_event:
                startActivity(new Intent(MainActivity.this, CalendarActivity.class));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_club:
                startActivity(new Intent(MainActivity.this, All_Club_Acitivity.class));
                break;
            case R.id.bt_event:
                startActivity(new Intent(MainActivity.this, CalendarActivity.class));
                break;
            case R.id.bt_fav:
                startActivity(new Intent(MainActivity.this,Club_Pick_Acitivity.class));
                break;
            case R.id.bt_com:
                Toast.makeText(getApplicationContext(),"진행중...",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
