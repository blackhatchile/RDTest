package com.example.guest.rdesingtest.SwipeHelper;

import android.support.v7.widget.RecyclerView;

public interface SwipeDeleteHelperListener {

    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
}
