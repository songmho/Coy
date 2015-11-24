package com.asc.coy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by songmho on 15. 11. 24..
 */
public class PlanAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<Plan_item> items;
    int layout;
    public PlanAdapter(Context context, ArrayList<Plan_item> items, int item_plan) {
        this.context=context;
        this.items=items;
        this.layout=item_plan;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(this.layout,parent,false);
        return new Item(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Plan_item item=items.get(position);
        ((Item)holder).title.setText(item.getTitle());
        ((Item)holder).club.setText(item.getClub());
        ((Item)holder).date.setText(item.getDate());
        ((Item)holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Plan_Detail_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class Item extends RecyclerView.ViewHolder{
        TextView title;
        TextView club;
        TextView date;
        CardView cardView;

        public Item(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            club=(TextView)itemView.findViewById(R.id.club);
            date=(TextView)itemView.findViewById(R.id.date);
            cardView=(CardView)itemView.findViewById(R.id.cardView);
        }
    }
}
