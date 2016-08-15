package com.codepath.apps.mysimpletweets.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by eddietseng on 8/14/16.
 */
public class RetweetLineFragment extends TweetsListFragment{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateTimeline();
    }

    public static RetweetLineFragment newInstance(long id) {
        RetweetLineFragment retweetLineFragment = new RetweetLineFragment();
        Bundle args = new Bundle();
        args.putLong("id", id);
        retweetLineFragment.setArguments(args);
        return retweetLineFragment;
    }

    // Send API request to get the mentions timeline json
    @Override
    protected void populateTimeline() {
        if (!checkNetwork())
            return;

        pd.show();
        long id = getArguments().getLong("id");
        client.getRetweetsTimeline(id, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("DEBUG", responseString);
                clearAll();

                List<Tweet> rTweets = Tweet.parseJSON(responseString);
                addAll(rTweets);

                // Now we call setRefreshing(false) to signal refresh has finished
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString,
                                  Throwable throwable) {
                Log.d("DEBUG", responseString);
            }
        });
        pd.dismiss();
    }
}
