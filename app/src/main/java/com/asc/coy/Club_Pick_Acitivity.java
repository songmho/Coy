package com.asc.coy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songmho on 2015-10-07.
 */
public class Club_Pick_Acitivity extends AppCompatActivity {
    FloatingActionButton fab;
    List<Club_Pick_Item> items=new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_pick);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("내 동아리");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        fab=(FloatingActionButton)findViewById(R.id.fab);




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Club_Pick_Acitivity.this,Club_Add_Activity.class));
            }
        });
        }

    @Override
    protected void onResume() {
        super.onResume();
        items.clear();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        ParseRelation<ParseObject> relation=ParseUser.getCurrentUser().getRelation("my_club");
        relation.getQuery().findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (list==null || list.size() == 0) {
                    Toast.makeText(Club_Pick_Acitivity.this, "목록이 없습니다", Toast.LENGTH_SHORT).show();
                }
                else {
                    byte[] d = new byte[10];
                    for (ParseObject o : list) {
                        Club_Pick_Item item = new Club_Pick_Item(d, o.getString("Club_name"), o.getString("Club_lotate"), o.getString("Club_intro")
                                , o.getString("Club_leader"), o.getString("Club_phone"), o.getString("Club_sub"));
                        items.add(item);

                    }

                    recyclerView.setAdapter(new Pick_Adapter(getApplicationContext(), items, R.layout.item_club_pick));
                }
            }
        });
    }
}
