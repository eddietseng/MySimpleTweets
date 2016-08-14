package com.codepath.apps.mysimpletweets.helper;

/**
 * Created by eddietseng on 8/14/16.
 */

import android.view.View;

public interface RecyclerViewClickListener {
    void onRowClicked(int position);
    void onViewClicked(View v, int position);
}
