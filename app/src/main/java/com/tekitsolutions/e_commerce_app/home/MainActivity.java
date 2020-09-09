package com.tekitsolutions.e_commerce_app.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.tekitsolutions.e_commerce_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    final long DELAY_MS = 500;
    final long PERIOD_MS = 3000;
    Typeface fonts1, fonts2, fonts3, fonts4;
    TextView eshop,  groomingproducts, trendingproducts, topbrands, categories; //shirt1, jeans1, shoes1, slippers1, goggles1,
    EditText searchtext;
    LinearLayout home0, offer0, fav0, bag0, noti0;
    ImageView home, offer, fav, bag, noti;
    //banner
    Integer[] imageId = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4};
    int currentPage = 0;
    Timer timer;
    private RecyclerView rv, rv2, rv3;
    private ArrayList<BeanlistGrooming> Bean;
    private GroomingRecyclerViewAdapter baseAdapter;
    private ArrayList<BeanlistTrending> Bean1;
    private TrendingRecyclerViewAdapter baseAdapter1;
    private ArrayList<BeanlistBrands> Bean2;
    private BrandsRecyclerViewAdapter baseAdapter2;
    private Context context;
    //    *****Grooming string*******
    private int[] IMAGEG = {R.drawable.groom1, R.drawable.groom2, R.drawable.groom3, R.drawable.groom4};
    private String[] TITLEG = {"Nike Shoes", "Denim Shirt", "Lee T-Shirt", "Reebok goggles "};
    private String[] DESCRIPTIONG = {"5000 Rs", "1200 Rs", "1000 Rs", "500 Rs"};
    private String[] DATEG = {"200 Rs", "200 Rs", "200 Rs", "200 Rs"};
    private String[] DISCOUNTG = {"15%", "10%", "25%", "50%"};
    private String[] RATINGTEXTG = {"(1234)", "(2322)", "(4322)", "(1223)"};

    //    *****Trending string*******
    private int[] IMAGET = {R.drawable.trand1, R.drawable.trand2, R.drawable.trand3, R.drawable.trand4};
    private String[] TITLET = {"Woodland Belts", "Titan Watch", "Denim Jeans", "Mufti Jeans"};
    private String[] DESCRIPTIONT = {"1200 Rs", "1200 Rs", "1200 Rs", "1200 Rs"};
    private String[] DATET = {"200 Rs", "200 Rs", "200 Rs", "200 Rs"};
    private String[] DISCOUNTT = {"15%", "10%", "25%", "50%"};
    private String[] RATINGTEXTT = {"(1234)", "(2322)", "(4322)", "(1223)"};

    //    *****Brands string*******
    private int[] IMAGEB = {R.drawable.brand1, R.drawable.brand2, R.drawable.brand3, R.drawable.brand4};
    private String[] TITLEB = {"Lee cooper shoes", "Peter england pant", "Puma lower", "Adidas T-shirt"};
    private String[] DESCRIPTIONB = {"1200 Rs", "1200 Rs", "1200 Rs", "1200 Rs"};
    private String[] DATEB = {"200 Rs", "200 Rs", "200 Rs", "200 Rs"};
    private String[] DISCOUNTB = {"15%", "10%", "25%", "50%"};
    private String[] RATINGTEXTB = {"(1234)", "(2322)", "(4322)", "(1223)"};

    // *********category**************
    private RecyclerView mRecycleview;
    private List<CategoryItem> mList = new ArrayList<>();
    private CategoryAdapter mAdapter;

    //drawer
    private DrawerLayout drawer;
    private RelativeLayout relativeLayout;
    private TextView navUsername, navMobile;
    private Toolbar toolbar;
    static final float END_SCALE = 0.7f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eshop = findViewById(R.id.eshop);
       /* shirt1 = findViewById(R.id.shirt1);
        jeans1 = findViewById(R.id.jeans1);
        shoes1 = findViewById(R.id.shoes1);
        slippers1 = findViewById(R.id.slippers1);
        goggles1 = findViewById(R.id.goggles1);*/
       categories = findViewById(R.id.tv_category);
        groomingproducts = findViewById(R.id.groomingproducts);
        trendingproducts = findViewById(R.id.trendingproducts);
        topbrands = findViewById(R.id.topbrands);
        searchtext = findViewById(R.id.searchtext);

        mRecycleview = findViewById(R.id.rv_category);

//     *******BOT BAR COLOR *********
        home = findViewById(R.id.home);
        home0 = findViewById(R.id.home0);
        offer = findViewById(R.id.offer);
        offer0 = findViewById(R.id.offer0);
        fav = findViewById(R.id.fav);
        fav0 = findViewById(R.id.fav0);
        bag = findViewById(R.id.bag);
        bag0 = findViewById(R.id.bag0);
        noti = findViewById(R.id.noti);
        noti0 = findViewById(R.id.noti0);

//        home.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
        home.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);
        offer.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);
        fav.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);
        bag.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);
        noti.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);


        home0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickb("1");

            }
        });
        offer0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickb("2");

            }
        });
        fav0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickb("3");

            }
        });
        bag0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickb("4");

            }
        });
        noti0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickb("5");

            }
        });


        fonts1 = Typeface.createFromAsset(getAssets(),
                "fonts/OpenSans-Regular.ttf");
        fonts2 = Typeface.createFromAsset(getAssets(),
                "fonts/OpenSans-Semibold.ttf");

        fonts3 = Typeface.createFromAsset(getAssets(),
                "fonts/Roboto-Medium.ttf");

        fonts4 = Typeface.createFromAsset(getAssets(),
                "fonts/Roboto-Regular.ttf");

        eshop.setTypeface(fonts1);
        searchtext.setTypeface(fonts1);
       /* shirt1.setTypeface(fonts4);
        jeans1.setTypeface(fonts4);
        shoes1.setTypeface(fonts4);
        slippers1.setTypeface(fonts4);
        goggles1.setTypeface(fonts4);*/
       categories.setTypeface(fonts2);
        groomingproducts.setTypeface(fonts2);
        trendingproducts.setTypeface(fonts2);
        topbrands.setTypeface(fonts2);

//*********RECYCLERVIEWS*********

        rv = findViewById(R.id.rv);
        rv2 = findViewById(R.id.rv2);
        rv3 = findViewById(R.id.rv3);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(mLayoutManager);

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rv2.setLayoutManager(mLayoutManager1);


        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rv3.setLayoutManager(mLayoutManager2);


        Bean = new ArrayList<BeanlistGrooming>();

        for (int i = 0; i < TITLEG.length; i++) {

            BeanlistGrooming bean = new BeanlistGrooming(IMAGEG[i], TITLEG[i], DESCRIPTIONG[i], DATEG[i], DISCOUNTG[i], RATINGTEXTG[i]);
            Bean.add(bean);

        }


        baseAdapter = new GroomingRecyclerViewAdapter(this, MainActivity.this, Bean) {
        };


        Bean1 = new ArrayList<BeanlistTrending>();

        for (int i = 0; i < TITLET.length; i++) {

            BeanlistTrending bean1 = new BeanlistTrending(IMAGET[i], TITLET[i], DESCRIPTIONT[i], DATET[i], DISCOUNTT[i], RATINGTEXTT[i]);
            Bean1.add(bean1);

        }


        baseAdapter1 = new TrendingRecyclerViewAdapter(this, MainActivity.this, Bean1) {
        };


        Bean2 = new ArrayList<BeanlistBrands>();

        for (int i = 0; i < IMAGEB.length; i++) {

            BeanlistBrands bean2 = new BeanlistBrands(IMAGEB[i], TITLEB[i], DESCRIPTIONB[i], DATEB[i], DISCOUNTB[i], RATINGTEXTB[i]);
            Bean2.add(bean2);

        }


        baseAdapter2 = new BrandsRecyclerViewAdapter(this, MainActivity.this, Bean2) {
        };

        rv.setAdapter(baseAdapter);
        rv2.setAdapter(baseAdapter1);
        rv3.setAdapter(baseAdapter2);


        //         ********Slider*********


        ViewPager pager = findViewById(R.id.photos_viewpager);
        PagerAdapter adapter = new CustomAdapter(MainActivity.this, imageId);
        pager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager, true);


        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 4) {
                    currentPage = 0;
                }
                pager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);


        //navigation drawer
        drawer = findViewById(R.id.drawer_layout);
        relativeLayout = findViewById(R.id.relativeLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navUsername = headerView.findViewById(R.id.tv_nav_user_name);
        navMobile = headerView.findViewById(R.id.tv_nav_user_mobile);


        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        toolbar = findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_drawer_white_24dp, this.getTheme());
        toggle.setHomeAsUpIndicator(drawable);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
       // drawer.addDrawerListener(toggle);
        //toggle.syncState();
        animateNavigationDrawer();


        addCategoryItems();
        initCategoryRecycler();
    }


    private void animateNavigationDrawer() {

        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawer.setScrimColor(Color.TRANSPARENT);
        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                relativeLayout.setScaleX(offsetScale);
                relativeLayout.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = relativeLayout.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                relativeLayout.setTranslationX(xTranslation);
            }
        });

    }


    private void addCategoryItems(){
        CategoryItem itemAdapter = new CategoryItem();
        itemAdapter.setImage(R.drawable.tshirt);
        itemAdapter.setText("T-shirts");
        mList.add(itemAdapter);

        itemAdapter = new CategoryItem();
        itemAdapter.setImage(R.drawable.jeans);
        itemAdapter.setText("Jeans");
        mList.add(itemAdapter);

        itemAdapter = new CategoryItem();
        itemAdapter.setImage(R.drawable.shoes);
        itemAdapter.setText("Shoes");
        mList.add(itemAdapter);

        itemAdapter = new CategoryItem();
        itemAdapter.setImage(R.drawable.slippers);
        itemAdapter.setText("Slippers");
        mList.add(itemAdapter);

        itemAdapter = new CategoryItem();
        itemAdapter.setImage(R.drawable.goggles);
        itemAdapter.setText("Goggles");
        mList.add(itemAdapter);

        itemAdapter = new CategoryItem();
        itemAdapter.setImage(R.drawable.belt);
        itemAdapter.setText("Belts");
        mList.add(itemAdapter);

        itemAdapter = new CategoryItem();
        itemAdapter.setImage(R.drawable.watches);
        itemAdapter.setText("Watches");
        mList.add(itemAdapter);

    }

    private void initCategoryRecycler(){
        mAdapter = new CategoryAdapter(mList, this);

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mRecycleview.setLayoutManager(mLayoutManager1);
        mRecycleview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }






    private void clickb(String s) {

        home.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);
        offer.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);
        fav.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);
        bag.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);
        noti.setColorFilter(getResources().getColor(R.color.boticon), android.graphics.PorterDuff.Mode.MULTIPLY);


        if (s.equalsIgnoreCase("1")) {

            home.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);

        } else if (s.equalsIgnoreCase("2")) {

            offer.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);

        } else if (s.equalsIgnoreCase("3")) {

            fav.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);

        } else if (s.equalsIgnoreCase("4")) {

            bag.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);

        } else if (s.equalsIgnoreCase("5")) {

            noti.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);

        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.nav_home:
                Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
