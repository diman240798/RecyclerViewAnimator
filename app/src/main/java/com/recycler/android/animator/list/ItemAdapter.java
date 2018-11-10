package com.recycler.android.animator.list;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.recycler.android.itemtouchhelperdemo.R;
import com.recycler.android.animator.list.ui.ItemTouchHelperAdapter;
import com.recycler.android.animator.list.ui.OnStartDragListener;
public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder>
        implements ItemTouchHelperAdapter {

    private final List<Item> mItems;

    private final OnStartDragListener mDragStartListener;
    private final Context context;

    public ItemAdapter(Context context, OnStartDragListener dragStartListener) {
        mDragStartListener = dragStartListener;
        this.context = context;
        List<String> strings = Arrays.asList(context.getResources().getStringArray(R.array.dummy_items));
        mItems = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            mItems.add(new Item(strings.get(i)));
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.textView.setText(mItems.get(position).getLabel());

        // Start a drag whenever the handle view it touched
        holder.handleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public void onItemDismiss(int position) {
        if (position < 0 || position >= mItems.size())
            return;
        mItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mItems.size());
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}
