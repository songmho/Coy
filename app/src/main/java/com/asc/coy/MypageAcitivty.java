package com.asc.coy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by songmho on 2015-10-31.
 */
public class MypageAcitivty extends AppCompatActivity {
    TextView gender;
    TextView department;
    TextView stu_num;
    TextView club;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        Intent intent=getIntent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsing_toolbar=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        getSupportActionBar().setTitle(intent.getStringExtra("name"));

        gender=(TextView)findViewById(R.id.gender);
        department=(TextView)findViewById(R.id.department);
        stu_num=(TextView)findViewById(R.id.stu_num);
        club=(TextView)findViewById(R.id.club);

        if(intent.getBooleanExtra("gender",true))
            gender.setText("남");
        else
            gender.setText("여");

        department.setText(intent.getStringExtra("department"));
        stu_num.setText(intent.getStringExtra("stu_num"));
    }
}
