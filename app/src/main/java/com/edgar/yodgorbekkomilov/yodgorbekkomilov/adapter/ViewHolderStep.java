package com.edgar.yodgorbekkomilov.yodgorbekkomilov.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.edgar.yodgorbekkomilov.yodgorbekkomilov.R;

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


