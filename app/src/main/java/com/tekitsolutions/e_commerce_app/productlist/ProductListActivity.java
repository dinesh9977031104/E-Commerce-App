package com.tekitsolutions.e_commerce_app.productlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tekitsolutions.e_commerce_app.R;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    private ExpandableHeightGridView gridview;
    private ArrayList<Beanclass> beanclassArrayList;
    private GridviewAdapter gridviewAdapter;


    private ExpandableHeightListView listview;
    private ArrayList<BeanclassList> Bean;
    private ListviewAdapter baseAdapter;

    ImageView gridviewicon,listviewicon;

    int categoryNumer;


    private int[] IMAGE = {R.drawable.tshirt1, R.drawable.tshirt2, R.drawable.tshirt3, R.drawable.tshirt4, R.drawable.tshirt5, R.drawable.tshirt6};
    private String[] TITLE = {"Puma mans t-shirt", "Nike mans t-shirt ", "Lee cooper T-shirt", "Peter England T-shirt", "Reebok mans t-shirt", "Adidas mans t-shirt"};
    private String[] PRICE = {"Rs. 1,499", "Rs. 1,899", "Rs. 1,299", "Rs. 1,199", "Rs. 1,499", "Rs. 1,899"};
    private String[] CUTPRICE = {"Rs. 1,699", "Rs. 1,999", "Rs. 1,599", "Rs. 1,399", "Rs. 1,699", "Rs. 1,999"};
    private String[] DISCOUNT = {"10%", "14%", "20%", "25%","10%", "14%"};
    private String[] RATING = {"(1231)", "(2331)", "(2321)", "(3213)","(4322)", "(2432)"};


    private int[] IMAGE1 = {R.drawable.jeanse1, R.drawable.jeanse2, R.drawable.jeanse3, R.drawable.jeanse4, R.drawable.jeanse5, R.drawable.jeanse6};
    private String[] TITLE1 = {"Puma mans Jeans", "Nike mans Jeans ", "Lee cooper Jeans", "Peter England Jeans", "Reebok mans Jeans", "Adidas mans Jeans"};
    private String[] PRICE1 = {"Rs. 1,499", "Rs. 1,899", "Rs. 1,299", "Rs. 1,199", "Rs. 1,499", "Rs. 1,899"};
    private String[] CUTPRICE1 = {"Rs. 1,699", "Rs. 1,999", "Rs. 1,599", "Rs. 1,399", "Rs. 1,699", "Rs. 1,999"};
    private String[] DISCOUNT1 = {"10%", "14%", "20%", "25%","10%", "14%"};
    private String[] RATING1 = {"(1231)", "(2331)", "(2321)", "(3213)","(4322)", "(2432)"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);


//              ********GRIDVIEW***********


        gridview = (ExpandableHeightGridView)findViewById(R.id.gridview);
        listview = (ExpandableHeightListView)findViewById(R.id.listview);

        beanclassArrayList= new ArrayList<Beanclass>();

        /*for (int i= 0; i< IMAGE.length; i++) {
            Beanclass beanclass = new Beanclass(IMAGE[i], TITLE[i], PRICE[i], CUTPRICE[i], DISCOUNT[i], RATING[i]);
            beanclassArrayList.add(beanclass);
        }
        gridviewAdapter = new GridviewAdapter(this,ProductListActivity.this, beanclassArrayList);
        gridview.setExpanded(true);
        gridview.setAdapter(gridviewAdapter);*/

        Intent intent = getIntent();
        categoryNumer = getIntent().getExtras().getInt("category");

        switch (categoryNumer){
            case 1:
                for (int i= 0; i< IMAGE.length; i++) {
                    Beanclass beanclass = new Beanclass(IMAGE[i], TITLE[i], PRICE[i], CUTPRICE[i], DISCOUNT[i], RATING[i]);
                    beanclassArrayList.add(beanclass);
                }
                gridviewAdapter = new GridviewAdapter(this,ProductListActivity.this, beanclassArrayList);
                gridview.setExpanded(true);
                gridview.setAdapter(gridviewAdapter);
                break;

            case 2:
                for (int i= 0; i< IMAGE1.length; i++) {
                    Beanclass beanclass = new Beanclass(IMAGE1[i], TITLE1[i], PRICE1[i], CUTPRICE1[i], DISCOUNT1[i], RATING1[i]);
                    beanclassArrayList.add(beanclass);
                }
                gridviewAdapter = new GridviewAdapter(this,ProductListActivity.this, beanclassArrayList);
                gridview.setExpanded(true);
                gridview.setAdapter(gridviewAdapter);
                break;
        }

//        /        ********LISTVIEW***********



        Bean = new ArrayList<BeanclassList>();

       /* for (int i= 0; i< TITLE.length; i++){
            BeanclassList BeanclassList = new BeanclassList(IMAGE[i], TITLE[i], PRICE[i], CUTPRICE[i], DISCOUNT[i], RATING[i]);
            Bean.add(BeanclassList);
        }
        baseAdapter = new ListviewAdapter(this,ProductListActivity.this, Bean) {
        };
        listview.setAdapter(baseAdapter);*/

       switch (categoryNumer){
           case 1:
               for (int i= 0; i< TITLE.length; i++){
                   BeanclassList BeanclassList = new BeanclassList(IMAGE[i], TITLE[i], PRICE[i], CUTPRICE[i], DISCOUNT[i], RATING[i]);
                   Bean.add(BeanclassList);
               }
               baseAdapter = new ListviewAdapter(this,ProductListActivity.this, Bean) {
               };
               listview.setAdapter(baseAdapter);
               break;

           case 2:
               for (int i= 0; i< TITLE1.length; i++){
                   BeanclassList BeanclassList = new BeanclassList(IMAGE1[i], TITLE1[i], PRICE1[i], CUTPRICE1[i], DISCOUNT1[i], RATING1[i]);
                   Bean.add(BeanclassList);
               }
               baseAdapter = new ListviewAdapter(this,ProductListActivity.this, Bean) {
               };
               listview.setAdapter(baseAdapter);
               break;
       }



//        ***********Grid - list view **********

        gridviewicon = (ImageView) findViewById(R.id.gridviewicon);
        listviewicon = (ImageView)findViewById(R.id.listviewicon);


        gridviewicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                listviewicon.setVisibility(View.VISIBLE);
                gridviewicon.setVisibility(View.GONE);
                listview.setVisibility(View.VISIBLE);
                gridview.setVisibility(View.GONE);

            }
        });

        listviewicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                listviewicon.setVisibility(View.GONE);
                gridviewicon.setVisibility(View.VISIBLE);
                listview.setVisibility(View.GONE);
                gridview.setVisibility(View.VISIBLE);



            }
        });



    }
}
