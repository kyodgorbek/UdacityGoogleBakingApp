package com.example.yodgorbekkomilov.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.yodgorbekkomilov.bakingapp.R;
import com.example.yodgorbekkomilov.bakingapp.pojo.Ingredient;

import org.w3c.dom.Text;

import java.util.List;

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

