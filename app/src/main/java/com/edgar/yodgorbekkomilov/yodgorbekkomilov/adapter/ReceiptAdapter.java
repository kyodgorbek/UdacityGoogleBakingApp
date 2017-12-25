package com.edgar.yodgorbekkomilov.yodgorbekkomilov.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import com.edgar.yodgorbekkomilov.yodgorbekkomilov.R;
import com.edgar.yodgorbekkomilov.yodgorbekkomilov.pojo.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yodgorbekkomilov on 6/22/17.
 */

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.MyViewHolder> {


    public ArrayList<Recipe> examples;
    public List<Recipe> recipes;
    OnItemClickListener listener;
    Context context;

    public ReceiptAdapter(ArrayList<Recipe> examples, OnItemClickListener listener, Context context) {
        this.examples = examples;
        this.listener = listener;
        this.context = context;


    }

    @Override
    public ReceiptAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_layout, viewGroup, false);
        return new MyViewHolder(view);
    }


    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        viewHolder.bind(examples.get(position), listener);
        viewHolder.imageView.setImageBitmap(null);
        String url = examples.get(position).getImage();
        if (URLUtil.isValidUrl(url)) {
            Picasso.with(context).load(url).into(viewHolder.imageView);
    }else if (examples.get(position).getName().equals("Nutella Pie")) {
            Picasso.with(context).load(R.drawable.nutellapie).into(viewHolder.imageView);
        } else if (examples.get(position).getName().equals("Brownies")) {
            Picasso.with(context).load(R.drawable.brownies).into(viewHolder.imageView);
        } else if (examples.get(position).getName().equals("Cheesecake")) {
            Picasso.with(context).load(R.drawable.cheesecake).into(viewHolder.imageView);
        } else if (examples.get(position).getName().equals("Yellow Cake")) {
            Picasso.with(context).load(R.drawable.yellowcake).into(viewHolder.imageView);
        } else {
            //If the recipe doesn't have a title that you know, use a default image here
        }

    }

    @Override
    public int getItemCount() {
        return (examples == null) ? 0 :examples.size();
    }

    public interface OnItemClickListener {


        void onItemClick(Recipe example);

        void onItemClick(View view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView myTextview;
        private ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            myTextview = (TextView) view.findViewById(R.id.my_text_view);
            imageView = (ImageView) view.findViewById(R.id.imageView);

        }

        public void bind(final Recipe examples, final OnItemClickListener listener) {
            myTextview.setText(examples.getName());


            myTextview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(examples);
                }
            });
        }
    }


}


