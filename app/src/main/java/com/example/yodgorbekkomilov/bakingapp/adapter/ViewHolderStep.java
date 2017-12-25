package com.example.yodgorbekkomilov.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.yodgorbekkomilov.bakingapp.R;
import com.example.yodgorbekkomilov.bakingapp.pojo.Ingredient;

import java.util.List;
import com.example.yodgorbekkomilov.bakingapp.pojo.*;
/**
 * Created by yodgorbekkomilov on 12/15/17.
 */

public class ViewHolderStep extends RecyclerView.ViewHolder{

    private TextView steps;

    public ViewHolderStep(View itemView) {
        super(itemView);
        steps = (TextView) itemView.findViewById(R.id.steps);

    }

    public TextView getSteps() {
        return steps;
    }

    public void setSteps(TextView steps) {
        this.steps = steps;
    }
}


