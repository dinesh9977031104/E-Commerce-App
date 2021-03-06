package com.tekitsolutions.e_commerce_app.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tekitsolutions.e_commerce_app.R;
import com.tekitsolutions.e_commerce_app.productlist.ProductListActivity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CategoryItem> mList;
    private Context mContext;

    public CategoryAdapter(List<CategoryItem> list, Context context) {
        super();
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        CategoryItem itemAdapter = mList.get(position);
        ((ViewHolder) viewHolder).mTv_name.setText(itemAdapter.getText());
        ((ViewHolder) viewHolder).mImg.setImageResource(itemAdapter.getImage());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(view.getContext(), ProductListActivity.class);
                        intent.putExtra("category",1);
                        view.getContext().startActivity(intent);
                        break;


                    case 1:
                        intent = new Intent(view.getContext(), ProductListActivity.class);
                        intent.putExtra("category",2);
                        view.getContext().startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTv_name;
        public ImageView mImg;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv_name = itemView.findViewById(R.id.tv_category);
            mImg = itemView.findViewById(R.id.iv_catagory);
        }
    }
}