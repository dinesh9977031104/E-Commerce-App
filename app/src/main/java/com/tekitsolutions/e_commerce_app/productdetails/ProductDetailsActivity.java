package com.tekitsolutions.e_commerce_app.productdetails;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.tekitsolutions.e_commerce_app.R;

import java.io.FileInputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends AppCompatActivity implements  CompoundButton.OnCheckedChangeListener {

    ImageView plus, minus;
    TextView cartno, cutprice, change, changeno;
    int counter = 0;
    Dialog myDialog1;
    EditText numberpopup;
    RatingBar ratingbar;


    //by dinesh
    TextView tvItem, tvPrice, tvRating, tvDiscount;
    ImageView ivProduct;


    @BindView(R.id.toggle_1)
    ToggleButton toggle1;
    @BindView(R.id.toggle_2)
    ToggleButton toggle2;
    @BindView(R.id.toggle_3)
    ToggleButton toggle3;
    @BindView(R.id.toggle_4)
    ToggleButton toggle4;
    @BindView(R.id.toggle_5)
    ToggleButton toggle5;
    @BindView(R.id.toggle_6)
    ToggleButton toggle6;
    @BindView(R.id.toggle_7)
    ToggleButton toggle7;

    //    *****Trending string*******

    //similar product comment by dinesh
    /*private RecyclerView rv2;
    private ArrayList<BeanlistPeopleViewed> Bean1;
    private PeopleViewedRecyclerViewAdapter baseAdapter1;*/
    //private Context context;
   /* private int[] IMAGET = {R.drawable.shoppy_logo, R.drawable.shoppy_logo, R.drawable.shoppy_logo, R.drawable.shoppy_logo};
    private String[] TITLET = {"Canvera Black Heel", "Canvera Black Heel", "Canvera Black Heel", "Canvera Black Heel"};
    private String[] PRICE = {"1200 Rs", "1200 Rs", "1200 Rs", "1200 Rs"};
    private String[] CUTPRICE = {"200 Rs", "200 Rs", "200 Rs", "200 Rs"};
    private String[] DISCOUNTT = {"15%", "10%", "25%", "50%"};
    private String[] RATINGTEXTT = {"(1234)", "(2322)", "(4322)", "(1223)"};*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        ButterKnife.bind(this);
        //*********RECYCLERVIEWS*********


        //similar product list comment by dinesh
        /*rv2 = findViewById(R.id.rv2);

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rv2.setLayoutManager(mLayoutManager1);


        Bean1 = new ArrayList<BeanlistPeopleViewed>();

        for (int i = 0; i < TITLET.length; i++) {

            BeanlistPeopleViewed bean1 = new BeanlistPeopleViewed(IMAGET[i], TITLET[i], PRICE[i], CUTPRICE[i], DISCOUNTT[i], RATINGTEXTT[i]);
            Bean1.add(bean1);

        }


        baseAdapter1 = new PeopleViewedRecyclerViewAdapter(this, ProductDetailsActivity.this, Bean1) {
        };


        rv2.setAdapter(baseAdapter1);*/


        change = findViewById(R.id.change);

        changeno = findViewById(R.id.changeno);


//*******Text thru Line********
        cutprice = findViewById(R.id.tv_full_price);
        cutprice.setPaintFlags(cutprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

//********PRODUCT PLUS MINUS*************
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        cartno = findViewById(R.id.cartno);

        final int[] number = {0};
        cartno.setText("" + number[0]);

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (number[0] == 0) {
                    cartno.setText("" + number[0]);
                }

                if (number[0] > 0) {

                    number[0] = number[0] - 1;
                    cartno.setText("" + number[0]);
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (number[0] == 9) {
                    cartno.setText("" + number[0]);
                }

                if (number[0] < 9) {

                    number[0] = number[0] + 1;
                    cartno.setText("" + number[0]);

                }
            }
        });

//        ***********ratingBar**********
        ratingbar = findViewById(R.id.ratingbar);
        LayerDrawable stars = (LayerDrawable) ratingbar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);


        //        *********POPUP*************

        changeno.setText("000000");
        changeno.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myDialog1 = new Dialog(ProductDetailsActivity.this);
                myDialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                myDialog1.setCancelable(true);
                myDialog1.setContentView(R.layout.popup_change);
                myDialog1.show();

                numberpopup = myDialog1.findViewById(R.id.numberpopup);

                TextView button = myDialog1.findViewById(R.id.button);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (numberpopup.getText().toString().equalsIgnoreCase("") || numberpopup.getText().toString().equalsIgnoreCase(null)) {

                            Toast.makeText(ProductDetailsActivity.this, "write a code", Toast.LENGTH_LONG).show();

                        } else {


                            changeno.setText(numberpopup.getText().toString());
                            myDialog1.dismiss();


                        }
                    }
                });
            }
        });


        //by dinesh
        tvItem = findViewById(R.id.tv_item);
        tvPrice = findViewById(R.id.tv_price);
        ivProduct = findViewById(R.id.iv_produce);
        tvRating = findViewById(R.id.tv_rating);
        tvDiscount = findViewById(R.id.discount);

        Intent intent = getIntent();
        String item = getIntent().getStringExtra("item");
        String price = getIntent().getStringExtra("price");
        String fullprice = getIntent().getStringExtra("curprice");
        String rat = getIntent().getStringExtra("rating");
        String disc = getIntent().getStringExtra("discount");

        tvItem.setText(item);
        tvPrice.setText(price);
        cutprice.setText(fullprice);
        tvRating.setText(rat);
        tvDiscount.setText(disc);



       /* if(getIntent().hasExtra("image")) {
            Bitmap _bitmap = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("image"),0,getIntent().getByteArrayExtra("image").length);
            ivProduct.setImageBitmap(_bitmap);
        }*/

        Bitmap bmp = null;
        String filename = getIntent().getStringExtra("image");
        try {
            FileInputStream is = this.openFileInput(filename);
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ivProduct.setImageBitmap(bmp);

        setClickListener();
    }


    private void setClickListener(){

        toggle1.setOnCheckedChangeListener(this);
        toggle2.setOnCheckedChangeListener(this);
        toggle3.setOnCheckedChangeListener(this);
        toggle4.setOnCheckedChangeListener(this);
        toggle5.setOnCheckedChangeListener(this);
        toggle6.setOnCheckedChangeListener(this);
        toggle7.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        if (isChecked){
            if (compoundButton == toggle1){
                toggle1.setChecked(true);
                toggle2.setChecked(false);
                toggle3.setChecked(false);
                toggle4.setChecked(false);
                toggle5.setChecked(false);
                toggle6.setChecked(false);
                toggle7.setChecked(false);
            }

            if (compoundButton == toggle2){
                toggle1.setChecked(false);
                toggle2.setChecked(true);
                toggle3.setChecked(false);
                toggle4.setChecked(false);
                toggle5.setChecked(false);
                toggle6.setChecked(false);
                toggle7.setChecked(false);
            }

            if (compoundButton == toggle3){
                toggle1.setChecked(false);
                toggle2.setChecked(false);
                toggle3.setChecked(true);
                toggle4.setChecked(false);
                toggle5.setChecked(false);
                toggle6.setChecked(false);
                toggle7.setChecked(false);
            }

            if (compoundButton == toggle4){
                toggle1.setChecked(false);
                toggle2.setChecked(false);
                toggle3.setChecked(false);
                toggle4.setChecked(true);
                toggle5.setChecked(false);
                toggle6.setChecked(false);
                toggle7.setChecked(false);
            }

            if (compoundButton == toggle5){
                toggle1.setChecked(false);
                toggle2.setChecked(false);
                toggle3.setChecked(false);
                toggle4.setChecked(false);
                toggle5.setChecked(true);
                toggle6.setChecked(false);
                toggle7.setChecked(false);
            }

            if (compoundButton == toggle6){
                toggle1.setChecked(false);
                toggle2.setChecked(false);
                toggle3.setChecked(false);
                toggle4.setChecked(false);
                toggle5.setChecked(false);
                toggle6.setChecked(true);
                toggle7.setChecked(false);
            }

            if (compoundButton == toggle7){
                toggle1.setChecked(false);
                toggle2.setChecked(false);
                toggle3.setChecked(false);
                toggle4.setChecked(false);
                toggle5.setChecked(false);
                toggle6.setChecked(false);
                toggle7.setChecked(true);
            }
        }
    }
}
