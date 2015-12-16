package com.asc.coy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.GrayscaleTransformation;

/**
 * Created by songmho on 15. 11. 26..
 */
public class Community_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("커뮤니티");

        Glide.with(this).load(R.mipmap.icon).bitmapTransform(new GrayscaleTransformation(getApplicationContext()))
                .into((ImageView)findViewById(R.id.image));

    }
}
