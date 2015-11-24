package com.asc.coy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by songmho on 15. 11. 24..
 */
public class Plan_Detail_Activity extends AppCompatActivity {

    TextView club;
    TextView detail;
    TextView date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);
        Intent intent=getIntent();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsing_toolbar=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        getSupportActionBar().setTitle(intent.getStringExtra("title"));

        club=(TextView)findViewById(R.id.club);
        detail=(TextView)findViewById(R.id.detail);
        date=(TextView)findViewById(R.id.date);

        club.setText(intent.getStringExtra("club"));
        detail.setText(intent.getStringExtra("detail"));
        date.setText(intent.getStringExtra("date"));

    }
}
