package com.asc.coy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songmho on 15. 11. 26..
 */
public class Search_Activity extends AppCompatActivity {
    List<All_club_Item> items=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("검색결과");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        final byte[] d = new byte[10];
        items.clear();

        ParseQuery<ParseObject> query=new ParseQuery<>("club");
        query.whereContains("Club_name", getIntent().getStringExtra("query"));
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (list==null || list.size() == 0) {
                    Toast.makeText(Search_Activity.this, "목록이 없습니다", Toast.LENGTH_SHORT).show();
                } else  {
                    for (ParseObject o : list) {
                        All_club_Item item = new All_club_Item(d, o.getString("Club_name"), o.getString("Club_lotate"), o.getString("Club_intro")
                                , o.getString("Club_leader"), o.getString("Club_phone"), o.getString("Club_sub"));
                        items.add(item);
                    }

                    recyclerView.setAdapter(new All_club_adapter(getApplicationContext(), items, R.layout.item_all_club));
                }
            }
        });
    }
}
