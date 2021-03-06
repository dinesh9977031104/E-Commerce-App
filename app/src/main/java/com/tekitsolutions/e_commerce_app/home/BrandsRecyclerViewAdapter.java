package com.tekitsolutions.e_commerce_app.home;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tekitsolutions.e_commerce_app.R;

import java.util.ArrayList;


public class BrandsRecyclerViewAdapter extends RecyclerView
        .Adapter<BrandsRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private static BrandsRecyclerViewAdapter.MyClickListener myClickListener;
    Activity main;
    Typeface fonts1, fonts2, fonts3, fonts4, fonts5;
    private ArrayList<BeanlistBrands> bean;
    private Context context;


    public BrandsRecyclerViewAdapter(Activity activity, Context context, ArrayList<BeanlistBrands> bean) {

        this.main = activity;
        this.context = context;
        this.bean = bean;
    }

    public void setOnItemClickListener(BrandsRecyclerViewAdapter.MyClickListener myClickListener) {
        BrandsRecyclerViewAdapter.myClickListener = myClickListener;
    }

    @Override
    public BrandsRecyclerViewAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);

        BrandsRecyclerViewAdapter.DataObjectHolder dataObjectHolder = new BrandsRecyclerViewAdapter.DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final BrandsRecyclerViewAdapter.DataObjectHolder holder, int position) {


        //  holder.icon.setImageResource(mDataset.get(position).get());
        holder.image.setImageResource(bean.get(position).getImage());
        holder.title.setText(bean.get(position).getTitle());
        holder.price.setText(bean.get(position).getPrice());
        holder.cutprice.setText(bean.get(position).getCutprice());
        holder.discount.setText(bean.get(position).getDiscount());
        holder.ratingtex.setText(bean.get(position).getRatingtext());


        holder.mSmallBang = SmallBang.attach2Window(main);

        holder.fav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                holder.fav2.setVisibility(View.VISIBLE);
                holder.fav1.setVisibility(View.GONE);
                like(v);

            }

            public void like(View view) {
                holder.fav2.setImageResource(R.drawable.f4);
                holder.mSmallBang.bang(view);
                holder.mSmallBang.setmListener(new SmallBangListener() {
                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {

                    }


                });
            }

        });

        holder.fav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                holder.fav2.setVisibility(View.GONE);
                holder.fav1.setVisibility(View.VISIBLE);


            }
        });


    }

    public void deleteItem(int index) {
        bean.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return bean.size();
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);

    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {


        ImageView image;
        TextView title;
        TextView price;
        TextView cutprice;
        TextView discount;
        TextView ratingtex;
        ImageView fav1, fav2;
        RatingBar ratingbar;
        private SmallBang mSmallBang;


        public DataObjectHolder(View itemView) {
            super(itemView);


            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            cutprice = itemView.findViewById(R.id.tv_full_price);
            discount = itemView.findViewById(R.id.discount);
            ratingtex = itemView.findViewById(R.id.ratingtext);


            cutprice.setPaintFlags(cutprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


//        ***********ratingBar**********
            ratingbar = itemView.findViewById(R.id.ratingbar);
            LayerDrawable stars = (LayerDrawable) ratingbar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.parseColor("#f8d64e"), PorterDuff.Mode.SRC_ATOP);

            fav1 = itemView.findViewById(R.id.fav1);
            fav2 = itemView.findViewById(R.id.fav2);


            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            //       myClickListener.onItemClick(getAdapterPosition(), v);
        }

    }


}
