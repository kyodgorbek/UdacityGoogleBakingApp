package com.edgar.yodgorbekkomilov.yodgorbekkomilov.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.edgar.yodgorbekkomilov.yodgorbekkomilov.R;

/**
 * Created by yodgorbekkomilov on 12/15/17.
 */

public class ViewHolderIngredient extends RecyclerView.ViewHolder {

    private TextView ingredient;

    public ViewHolderIngredient(View itemView) {
        super(itemView);
        ingredient = (TextView) itemView.findViewById(R.id.ingredients);
    }

    public TextView getIngredient() {
        return ingredient;
    }

    public void setIngredient(TextView ingredient) {
        this.ingredient = ingredient;
    }
}

