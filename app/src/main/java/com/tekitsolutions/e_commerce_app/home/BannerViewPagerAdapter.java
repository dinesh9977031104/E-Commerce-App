package com.tekitsolutions.e_commerce_app.home;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.tekitsolutions.e_commerce_app.R;

public class BannerViewPagerAdapter extends PagerAdapter {

    private Activity activity;
    private Integer[] imagesArray;


    public BannerViewPagerAdapter(Activity activity, Integer[] imagesArray) {

        this.activity = activity;
        this.imagesArray = imagesArray;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (activity).getLayoutInflater();
        View viewItem = inflater.inflate(R.layout.slider_list, container, false);
        ImageView imageView = viewItem.findViewById(R.id.imageView);
        imageView.setBackgroundResource(imagesArray[position]);

        container.addView(viewItem);

        return viewItem;
    }

    @Override
    public int getCount() {
        return imagesArray.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}