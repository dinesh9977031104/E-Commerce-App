package com.tekitsolutions.e_commerce_app.productlist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tekitsolutions.e_commerce_app.R;
import com.tekitsolutions.e_commerce_app.productdetails.ProductDetailsActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class ListviewAdapter extends BaseAdapter {

    Context context;

    ArrayList<BeanclassList>bean;
    Typeface fonts1,fonts2;
    RatingBar ratingbar;
    Activity main;




    public ListviewAdapter(Activity activity,Context context, ArrayList<BeanclassList> bean) {

        this.main = activity;
        this.context = context;
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.size();
    }

    @Override
    public Object getItem(int position) {
        return bean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        fonts1 =  Typeface.createFromAsset(context.getAssets(),
                "fonts/MavenPro-Regular.ttf");

        fonts2 = Typeface.createFromAsset(context.getAssets(),
                "fonts/MavenPro-Regular.ttf");

        final ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview_productlist,null);

            viewHolder = new ViewHolder();

            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            viewHolder.cutprice = (TextView) convertView.findViewById(R.id.tv_full_price);
            viewHolder.discount = (TextView) convertView.findViewById(R.id.discount);
            viewHolder.ratingtext = (TextView) convertView.findViewById(R.id.ratingtext);
            viewHolder.fav1 = (ImageView) convertView.findViewById(R.id.fav1);
            viewHolder.fav2 = (ImageView) convertView.findViewById(R.id.fav2);


            viewHolder.title.setTypeface(fonts1);
            viewHolder.price.setTypeface(fonts1);
            viewHolder.cutprice.setTypeface(fonts1);
            viewHolder.discount.setTypeface(fonts1);
            viewHolder.ratingtext.setTypeface(fonts1);



//        ***********ratingBar**********
            ratingbar = (RatingBar) convertView.findViewById(R.id.ratingbar);
            LayerDrawable stars = (LayerDrawable) ratingbar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.parseColor("#f8d64e"), PorterDuff.Mode.SRC_ATOP);



            convertView.setTag(viewHolder);

            viewHolder.cutprice.setPaintFlags(viewHolder.cutprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else {

            viewHolder = (ViewHolder)convertView.getTag();
        }







        BeanclassList bean = (BeanclassList)getItem(position);

       // viewHolder.image.setImageResource(bean.getImage());
        Glide.with(context).load(bean.getImage()).into(viewHolder.image);

        viewHolder.title.setText(bean.getTitle());
        viewHolder.price.setText(bean.getPrice());
        viewHolder.cutprice.setText(bean.getCutprice());
        viewHolder.discount.setText(bean.getDiscount());
        viewHolder.ratingtext.setText(bean.getRatingtext());

        viewHolder.mSmallBang = SmallBang.attach2Window(main);



        viewHolder.fav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                viewHolder.fav2.setVisibility(View.VISIBLE);
                viewHolder.fav1.setVisibility(View.GONE);
                like(v);

            }

            public void like(View view){
                viewHolder.fav2.setImageResource(R.drawable.f4);
                viewHolder.mSmallBang.bang(view);
                viewHolder.mSmallBang.setmListener(new SmallBangListener() {
                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {

                    }


                });
            }

        });


        viewHolder.fav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                viewHolder.fav2.setVisibility(View.GONE);
                viewHolder.fav1.setVisibility(View.VISIBLE);


            }
        });

        //by dinesh
        /*viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProductDetailsActivity.class);
                intent.putExtra("item",viewHolder.title.getText().toString());
                intent.putExtra("price",viewHolder.price.getText().toString());
                intent.putExtra("curprice",viewHolder.cutprice.getText().toString());
                intent.putExtra("rating",viewHolder.ratingtext.getText().toString());
                intent.putExtra("discount",viewHolder.discount.getText().toString());

                Bitmap bitmap = ((BitmapDrawable) viewHolder.image.getDrawable()).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 10, byteArrayOutputStream);
                intent.putExtra("image",byteArrayOutputStream.toByteArray());

                view.getContext().startActivity(intent);
            }
        });*/

        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    Bitmap  bitmap = ((BitmapDrawable) viewHolder.image.getDrawable()).getBitmap();
                    String filename = "bitmap.png";
                    FileOutputStream stream = view.getContext().openFileOutput(filename, Context.MODE_PRIVATE);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                    //Cleanup
                    stream.close();
                    // bitmap.recycle();

                   /*if (bitmap !=null && !bitmap.isRecycled()){
                       bitmap.recycle();
                       bitmap = null;
                   }*/

                    //Pop intent
                    Intent intent = new Intent(view.getContext(), ProductDetailsActivity.class);
                    intent.putExtra("item",viewHolder.title.getText().toString());
                    intent.putExtra("price",viewHolder.price.getText().toString());
                    intent.putExtra("curprice",viewHolder.cutprice.getText().toString());
                    intent.putExtra("rating",viewHolder.ratingtext.getText().toString());
                    intent.putExtra("discount",viewHolder.discount.getText().toString());
                    intent.putExtra("image", filename);
                    view.getContext().startActivity(intent);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        return convertView;

    }

    private class ViewHolder {
        ImageView image;
        TextView title;
        TextView price;
        TextView cutprice;
        TextView discount;
        TextView ratingtext;
        ImageView fav1;
        ImageView fav2;
        SmallBang mSmallBang;


    }
}



