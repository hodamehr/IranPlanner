package com.iranplanner.tourism.iranplanner.ui.activity.attractioListMore;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import tools.Util;

/**
 * Created by h.vahidimehr on 29/08/2017.
 */

public class AttractionListMoreItemDecoration extends RecyclerView.ItemDecoration {

    private Context context;

    public AttractionListMoreItemDecoration(Context context) {
        this.context = context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        final int itemPosition = parent.getChildAdapterPosition(view);
        final int itemCount = state.getItemCount();

        if (itemCount > 0 && itemPosition == itemCount - 1) {
            outRect.bottom = (int) Util.dpToPx(context, 70);
        }

    }
}
