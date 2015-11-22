package com.asc.coy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by songmho on 15. 11. 22..
 */
public class Add_Adapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<Add_item> items;
    ArrayList<String> club_names=new ArrayList<>();

    public ArrayList<String> getClub_names() {
        return club_names;
    }

    int layout;
    public Add_Adapter(Context context, ArrayList<Add_item> items, int layout) {
        this.context=context;
        this.layout=layout;
        this.items=items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new Item(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Add_item item=items.get(position);

        ((Item)holder).title.setText(item.getTitle());
        ((Item)holder).checkBox.setChecked(item.isChecked());
        ((Item)holder).checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ((Item)holder).checkBox.setChecked(isChecked);
                if(isChecked)
                    club_names.add(item.getTitle());
                else
                    club_names.remove(item.getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class Item extends RecyclerView.ViewHolder{
        TextView title;
        CheckBox checkBox;

        public Item(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            checkBox=(CheckBox)itemView.findViewById(R.id.checkbox);
        }
    }

}