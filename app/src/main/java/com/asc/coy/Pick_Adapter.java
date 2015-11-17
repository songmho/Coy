package com.asc.coy;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by songmho on 2015-10-12.
 */
public class Pick_Adapter extends RecyclerView.Adapter {
    Context context;
    List<Club_Pick_Item> items;
    int layout;

    public Pick_Adapter(Context context, List<Club_Pick_Item> items, int layout) {
        this.context=context;
        this.items=items;
        this.layout=layout;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club_pick,parent,false);
        return new Item(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Club_Pick_Item item=items.get(position);

        ((Item) holder).image.setImageBitmap(BitmapFactory.decodeByteArray(item.getImage(),0,item.getImage().length));
        ((Item)holder).title.setText(item.getTitle());
        ((Item)holder).detail.setText(item.getDetail());
        ((Item)holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context.getApplicationContext(),Club_Activity.class);
                intent.putExtra("sub",item.getSub());
                intent.putExtra("title",item.getTitle());
                intent.putExtra("place",item.getPlace());
                intent.putExtra("detail",item.getDetail());
                intent.putExtra("leader",item.getLeader());
                intent.putExtra("phone",item.getPhone());
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
        CircleImageView image;
        TextView title;
        TextView detail;
        CardView cardView;

        public Item(View itemView) {
            super(itemView);
            image=(CircleImageView)itemView.findViewById(R.id.image);
            title=(TextView)itemView.findViewById(R.id.title);
            detail=(TextView)itemView.findViewById(R.id.detail);
            cardView=(CardView)itemView.findViewById(R.id.cardView);
        }
    }
}
