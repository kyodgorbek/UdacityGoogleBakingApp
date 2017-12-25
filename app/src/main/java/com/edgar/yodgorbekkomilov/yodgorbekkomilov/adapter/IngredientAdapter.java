package com.edgar.yodgorbekkomilov.yodgorbekkomilov.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edgar.yodgorbekkomilov.yodgorbekkomilov.R;
import com.edgar.yodgorbekkomilov.yodgorbekkomilov.pojo.Ingredient;
import com.edgar.yodgorbekkomilov.yodgorbekkomilov.pojo.Step;

import java.util.List;
import com.edgar.yodgorbekkomilov.yodgorbekkomilov.pojo.RecyclerViewClickListener;
/**
 * Created by yodgorbekkomilov on 8/17/17.
 */

public class IngredientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final RecyclerViewClickListener listener;

    private List<Object> items;
    Context context;

    private final int INGREDIENTS = 0, STEP = 1;


    public IngredientAdapter(RecyclerViewClickListener listener, List<Object> items, Context context) {
        this.listener = listener;
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Ingredient) {
            return INGREDIENTS;
        } else if (items.get(position) instanceof Step) {
            return STEP;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case INGREDIENTS:
                View v1 = inflater.inflate(R.layout.layout_viewholderingredient, viewGroup, false);
                viewHolder = new ViewHolderIngredient(v1);
                break;
            default:
                View v2 = inflater.inflate(R.layout.layout_viewholderstep, viewGroup, false);
                viewHolder = new ViewHolderStep(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case INGREDIENTS:
                ViewHolderIngredient vhIngredient = (ViewHolderIngredient) viewHolder;
                configureViewHolderIngredient(vhIngredient, position);
                break;
            default:
                ViewHolderStep vhStep = (ViewHolderStep) viewHolder;
                configureViewHolderStep(vhStep, position);
                break;
        }
    }


    private void configureViewHolderIngredient(ViewHolderIngredient vhIngredient, int position) {
        Ingredient ingredient = (Ingredient) items.get(position);
        if (ingredient != null) {
            vhIngredient.getIngredient().setText("Ingredient: " + ingredient.getIngredient());
            // @todo add other properties
        }
    }

    private void configureViewHolderStep(ViewHolderStep vhStep, final int position) {
        final Step step = (Step) items.get(position);
        if (step != null) {
            vhStep.getSteps().setText("Step: " + step.getDescription());
            vhStep.getSteps().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int offset = 0;
                    for (Object stepItem : items) {
                        if (stepItem instanceof Ingredient) {
                            offset += 1;
                        }

                    }
                    listener.onItemClick(position - offset);
                }
            });
            // @todo add other properties
        }

    }
}









