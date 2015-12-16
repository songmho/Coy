package com.asc.coy;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by songmho on 2015-10-12.
 */
public class All_club_adapter extends RecyclerView.Adapter {
    Context context;
    List<All_club_Item> items;
    int layout;
    public All_club_adapter(Context context, List<All_club_Item> items, int layout) {
        this.context=context;
        this.items=items;
        this.layout=layout;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(this.layout,parent,false);
        return new Item(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final All_club_Item item=items.get(position);

     //   ((Item) holder).image.setImageBitmap(BitmapFactory.decodeByteArray(item.getImage(), 0, item.getImage().length));

        Glide.with(context).load(R.drawable.icon_club). bitmapTransform(new CropCircleTransformation(context.getApplicationContext())).into(((Item)holder).image);
        ((Item)holder).title.setText(item.getTitle());
        ((Item)holder).place.setText(item.getPlace());
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
        TextView place;
        CardView cardView;

        public Item(View itemView) {
            super(itemView);
            image=(CircleImageView)itemView.findViewById(R.id.image);
            title=(TextView)itemView.findViewById(R.id.title);
            place=(TextView)itemView.findViewById(R.id.place);
            detail=(TextView)itemView.findViewById(R.id.detail);
            cardView=(CardView)itemView.findViewById(R.id.cardView);
        }
    }
}
