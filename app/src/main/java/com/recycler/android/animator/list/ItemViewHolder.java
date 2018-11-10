package com.recycler.android.animator.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.recycler.android.animator.list.ui.ItemTouchHelperViewHolder;
import com.recycler.android.itemtouchhelperdemo.R;

public class ItemViewHolder extends RecyclerView.ViewHolder implements
        ItemTouchHelperViewHolder {

    public final TextView textView;
    public final ImageView handleView;

    public ItemViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.text);
        handleView = (ImageView) itemView.findViewById(R.id.handle);
    }

    @Override
    public void onItemSelected() {
        //itemView.setBackgroundColor(Color.LTGRAY);
        Context context = itemView.getContext();
        itemView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        handleView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_handle_white));
    }

    @Override
    public void onItemClear() {
        Context context = itemView.getContext();
        itemView.setBackgroundColor(0);
        handleView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_handle_grey));
    }
}