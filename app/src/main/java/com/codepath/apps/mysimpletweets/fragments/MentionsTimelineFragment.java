package com.codepath.apps.mysimpletweets.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by eddietseng on 8/12/16.
 */
public class MentionsTimelineFragment extends TweetsListFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateTimeline();
    }

    // Send API request to get the mentions timeline json
    @Override
    protected void populateTimeline() {
        if( !checkNetwork() )
            return;

        pd.show();
        client.getMentionsTimeline(0, new TextHttpResponseHandler() {
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

    @Override
    protected void customLoadMoreDataFromApi(int totalItemsCount) {
        if( !checkNetwork() )
            return;

        pd.show();
        client.getMentionsTimeline(getTweets().get(totalItemsCount -1 ).getId(),
                new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Log.d("DEBUG",responseString);
                        List<Tweet> rTweets = Tweet.parseJSON(responseString);
                        addAll(rTweets);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString,
                                          Throwable throwable) {
                        Log.d("DEBUG",responseString);
                    }
                });
        pd.dismiss();
    }
}
