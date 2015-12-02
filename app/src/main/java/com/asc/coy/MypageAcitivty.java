package com.asc.coy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by songmho on 2015-10-31.
 */
public class MypageAcitivty extends AppCompatActivity {
    TextView gender;
    TextView department;
    TextView stu_num;
    TextView club;
    ImageView profile;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        intent=getIntent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsing_toolbar=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        getSupportActionBar().setTitle(intent.getStringExtra("name"));

        gender=(TextView)findViewById(R.id.gender);
        department=(TextView)findViewById(R.id.department);
        stu_num=(TextView)findViewById(R.id.stu_num);
        club=(TextView)findViewById(R.id.club);
        profile=(ImageView)findViewById(R.id.profile);


        String tempPath="data/data/com.asc.coy/files/profile.jpg";
        Bitmap bm = BitmapFactory.decodeFile(tempPath);
        if(bm!=null){
            Glide.with(getApplicationContext()).load(bitmapTobyte(bm)).
                    bitmapTransform(new CropCircleTransformation(getApplicationContext())).into(profile);
        }
        else{        Glide.with(getApplicationContext()).load(R.drawable.ss).
                bitmapTransform(new CropCircleTransformation(getApplicationContext())).into(profile);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(intent.getBooleanExtra("gender",true))
            gender.setText("남");
        else
            gender.setText("여");

        department.setText(intent.getStringExtra("department"));
        stu_num.setText(intent.getStringExtra("stu_num"));

        ParseRelation<ParseObject> q= ParseUser.getCurrentUser().getRelation("my_club");
        q.getQuery().findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                String buf="";
                for(ParseObject o :list){
                    buf=buf+o.getString("Club_name") + "  ";
                }
                club.setText(buf);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit,menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_edit){
            Intent intent_edit=new Intent(MypageAcitivty.this,My_edit_Activity.class);
            intent_edit.putExtra("name", intent.getStringExtra("name"));
            intent_edit.putExtra("gender", intent.getBooleanExtra("gender",true));
            intent_edit.putExtra("department", intent.getStringExtra("department"));
            intent_edit.putExtra("stu_num", intent.getStringExtra("stu_num"));

            startActivity(intent_edit);
        }
        return super.onOptionsItemSelected(item);
    }
    private byte[] bitmapTobyte(Bitmap bm) {
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] bytes=stream.toByteArray();
        return bytes;
    }
}
