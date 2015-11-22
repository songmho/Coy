package com.asc.coy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
 * Created by songmho on 15. 11. 22..
 */
public class Club_Add_Activity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<Add_item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_club);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("내 동아리 추가");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        items=new ArrayList<>();

        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        final ArrayList<String> my_club=new ArrayList<>();
        ParseRelation<ParseObject> relation=ParseUser.getCurrentUser().getRelation("my_club");
        relation.getQuery().findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                for(ParseObject o : list)
                    my_club.add(o.getString("Club_name"));
            }
        });
        ParseQuery<ParseObject> query=new ParseQuery<>("club");
        query.whereNotContainedIn("Club_name",my_club);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                for(ParseObject o:list){
                    Add_item item=new Add_item(o.getString("Club_name"),false);
                    items.add(item);
                }
                recyclerView.setAdapter(new Add_Adapter(getApplicationContext(), items, R.layout.item_add));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_add){

            Add_Adapter a=(Add_Adapter)recyclerView.getAdapter();
            ArrayList<String> s=a.getClub_names();

            final ParseUser current=ParseUser.getCurrentUser();
            final ParseRelation<ParseObject> relation=current.getRelation("my_club");
            ParseQuery<ParseObject> query=new ParseQuery<>("club");
            query.whereContainedIn("Club_name",s);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    for (ParseObject o : list) {
                        relation.add(o);
                        Log.d("ddd",o.getString("Club_name"));
                    }
                    current.saveInBackground();
                }
            });
            Toast.makeText(Club_Add_Activity.this, "추가", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
