package com.asc.coy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by songmho on 15. 11. 17..
 */
public class Club_Activity extends AppCompatActivity {

    TextView sub;
    TextView intro;
    TextView lotate;
    TextView leader;
    TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);


        Intent intent=getIntent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsing_toolbar=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        getSupportActionBar().setTitle(intent.getStringExtra("title"));

        sub=(TextView)findViewById(R.id.sub);
        intro=(TextView)findViewById(R.id.intro);
        lotate=(TextView)findViewById(R.id.locate);
        leader=(TextView)findViewById(R.id.leader);
        phone=(TextView)findViewById(R.id.phone);

        sub.setText(intent.getStringExtra("sub"));
        intro.setText(intent.getStringExtra("detail"));
        lotate.setText(intent.getStringExtra("place"));
        leader.setText(intent.getStringExtra("leader"));
        phone.setText(intent.getStringExtra("phone"));

    }
}
