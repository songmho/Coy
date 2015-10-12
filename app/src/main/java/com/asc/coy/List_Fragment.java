package com.asc.coy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songmho on 2015-10-12.
 */
public class List_Fragment extends android.support.v4.app.Fragment {

    List<All_club_Item> items=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout linearLayout=(LinearLayout)inflater.inflate(R.layout.fragment_list,container,false);

        RecyclerView recyclerView=(RecyclerView)linearLayout.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext().getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        byte[] d=new byte[10];

        All_club_Item item=new All_club_Item(d,"TEST","TEST","TEST");
        items.add(item);
        recyclerView.setAdapter(new All_club_adapter(getActivity(),items,R.layout.item_all_club));
        return linearLayout;
    }
}
