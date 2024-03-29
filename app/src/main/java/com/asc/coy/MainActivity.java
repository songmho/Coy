package com.asc.coy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    LinearLayout bt_club;
    LinearLayout bt_event;
    LinearLayout bt_fav;
    LinearLayout bt_com;

    String _name=null;
    boolean _gender;
    String _department;
    String _stu_num;
    boolean islogin=false;
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


        if (ParseUser.getCurrentUser()!=null) {
            TextView name = (TextView) navigationView.findViewById(R.id.name);
            ImageView profile=(ImageView)navigationView.findViewById(R.id.profile);

            String tempPath="data/data/com.asc.coy/files/profile.jpg";
            Bitmap bm = BitmapFactory.decodeFile(tempPath);
            if(bm!=null){
                Glide.with(getApplicationContext()).load(bitmapTobyte(bm)).
                        bitmapTransform(new CropCircleTransformation(getApplicationContext())).into(profile);
            }
            else{        Glide.with(getApplicationContext()).load(R.drawable.ss).
                    bitmapTransform(new CropCircleTransformation(getApplicationContext())).into(profile);

            }
            _name=ParseUser.getCurrentUser().getString("name");
            _department=ParseUser.getCurrentUser().getString("department");
            _gender=ParseUser.getCurrentUser().getBoolean("ismale");
            _stu_num=ParseUser.getCurrentUser().getString("stu_num");
            islogin=true;
            name.setText(_name);
        }

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

        ImageView ImagClub = (ImageView) findViewById(R.id.img_club);
        ImageView ImagEvent = (ImageView) findViewById(R.id.img_event);
        ImageView ImagFav = (ImageView) findViewById(R.id.img_fav);
        ImageView ImagCum = (ImageView) findViewById(R.id.img_cum);

        Glide.with(this).load(R.drawable.img_club).into(ImagClub);
        Glide.with(this).load(R.drawable.img_event).into(ImagEvent);
        Glide.with(this).load(R.drawable.img_fav).into(ImagFav);
        Glide.with(this).load(R.drawable.img_cum).into(ImagCum);
        
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
                if(islogin) {
                    final Intent intent_mypage = new Intent(MainActivity.this, MypageAcitivty.class);

                    intent_mypage.putExtra("name", _name);
                    intent_mypage.putExtra("gender", _gender);
                    intent_mypage.putExtra("department", _department);
                    intent_mypage.putExtra("stu_num", _stu_num);

                    startActivity(intent_mypage);
                }
                else{
                    Toast.makeText(MainActivity.this, "로그인해주세요", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(MainActivity.this,Login_Activity.class));
                        }
                    },1000);
                }
                drawerLayout.closeDrawers();
                return true;


            case R.id.club_pick:
                if(islogin)
                    startActivity(new Intent(MainActivity.this,Club_Pick_Acitivity.class));
                else{
                    Toast.makeText(MainActivity.this, "로그인해주세요", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(MainActivity.this,Login_Activity.class));
                        }
                    },1000);
                }
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
                startActivity(new Intent(MainActivity.this, Setup_Activity.class));
                drawerLayout.closeDrawers();
                return true;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (drawerLayout.isDrawerOpen(navigationView))
                    drawerLayout.closeDrawers();
                else {
                    moveTaskToBack(true);
                    finish();
                }
                break;
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
                if(ParseUser.getCurrentUser()!=null)
                    startActivity(new Intent(MainActivity.this,Club_Pick_Acitivity.class));
                else{
                    Toast.makeText(MainActivity.this, "로그인해주세요", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(MainActivity.this,Login_Activity.class));
                        }
                    },1000);
                }
                break;

            case R.id.bt_com:
                startActivity(new Intent(MainActivity.this, Community_Activity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ParseUser.getCurrentUser()!=null)
            islogin=true;
        else
            islogin=false;
    }

    private byte[] bitmapTobyte(Bitmap bm) {
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] bytes=stream.toByteArray();
        return bytes;
    }
}
