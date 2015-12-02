package com.asc.coy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songmho on 2015-10-12.
 */
public class List_Fragment extends android.support.v4.app.Fragment {

    List<All_club_Item> items=new ArrayList<>();
    String cur_sub;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout linearLayout=(LinearLayout)inflater.inflate(R.layout.fragment_list,container,false);

        final RecyclerView recyclerView=(RecyclerView)linearLayout.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext().getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        Bundle bundle=this.getArguments();
        cur_sub =bundle.getString("cur_sub","");
        final byte[] d = new byte[10];

        items.clear();

        ParseQuery<ParseObject> query=new ParseQuery<>("club");
        query.addAscendingOrder("Club_name");
        query.whereEqualTo("Club_sub", cur_sub);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                for (ParseObject o : list) {
                    All_club_Item item = new All_club_Item(d, o.getString("Club_name"), o.getString("Club_lotate"), o.getString("Club_intro")
                                                            ,o.getString("Club_leader"),o.getString("Club_phone"),o.getString("Club_sub"));
                    items.add(item);
                }

                recyclerView.setAdapter(new All_club_adapter(getActivity(), items, R.layout.item_all_club));
            }
        });

        return linearLayout;
    }
}
