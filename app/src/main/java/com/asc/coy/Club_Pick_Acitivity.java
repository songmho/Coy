package com.asc.coy;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songmho on 2015-10-07.
 */
public class Club_Pick_Acitivity extends AppCompatActivity {
    List<Club_Pick_Item> items=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_pick);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("관심있는 동아리");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        byte[] d=new byte[10];
        Club_Pick_Item item=new Club_Pick_Item(d,"TEST","INTRO");
        items.add(item);
        recyclerView.setAdapter(new Pick_Adapter(getApplicationContext(),items,R.layout.item_club_pick));
    }
}
