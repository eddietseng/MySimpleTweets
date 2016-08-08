package com.codepath.apps.mysimpletweets;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.apps.mysimpletweets.adapters.TweetsAdapter;
import com.codepath.apps.mysimpletweets.helper.EndlessRecyclerViewScrollListener;
import com.codepath.apps.mysimpletweets.models.CurrentUser;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity
        implements NewTweetFragment.NewTweetDialogListener{

    private SwipeRefreshLayout swipeContainer;
    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private TweetsAdapter aTweets;
    private RecyclerView rvTweets;
    private CurrentUser userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                populateTimeline();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        //Find listView
        rvTweets = (RecyclerView)findViewById(R.id.rvTweets);
        //Create the arrayList
        tweets = new ArrayList<>();
        //Construct adapter
        aTweets = new TweetsAdapter(this,tweets);
        //Connect adapter to list view
        rvTweets.setAdapter(aTweets);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvTweets.setLayoutManager(linearLayoutManager);

        client = TwitterApplication.getRestClient();
        storeCurrentUserData();
        populateTimeline();

        rvTweets.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {

            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                customLoadMoreDataFromApi(totalItemsCount);
            }
        });

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.twitter_logo_trans);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }



    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.miCompose) {
            showNewTweetDialog(userData);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showNewTweetDialog(CurrentUser userData) {
        FragmentManager fm = getSupportFragmentManager();
        NewTweetFragment editNameDialogFragment = NewTweetFragment.newInstance("New Tweet", userData);
        editNameDialogFragment.show(fm, "fragment_edit_name");
    }

    private void storeCurrentUserData() {
        client.getUserData(new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("DEBUG",responseString);
                CurrentUser currentUser = CurrentUser.parseJSON(responseString);
                userData = currentUser;
                Log.d("DEBUG",userData.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString,
                                  Throwable throwable) {
                Log.d("DEBUG",responseString);
            }
        });
    }

    // Send API request to get the timeline json
    private void populateTimeline() {
        client.getHomeTimeline(new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("DEBUG",responseString);
                tweets.clear();

                List<Tweet> rTweets = Tweet.parseJSON(responseString);
                tweets.addAll(rTweets);
                aTweets.notifyDataSetChanged();
                Log.d("DEBUG",tweets.toString());

                // Now we call setRefreshing(false) to signal refresh has finished
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString,
                                  Throwable throwable) {
                Log.d("DEBUG",responseString);
            }
        });
    }

    private void customLoadMoreDataFromApi(int totalItemsCount) {
        client.getNextTimeline(tweets.get(totalItemsCount -1 ).getId(),new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("DEBUG",responseString);
                List<Tweet> rTweets = Tweet.parseJSON(responseString);
                tweets.addAll(rTweets);
                aTweets.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString,
                                  Throwable throwable) {
                Log.d("DEBUG",responseString);
            }
        });
    }

    public void onFinishEditDialog(final String inputText) {
        client.postNewTweet(inputText, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("DEBUG",response.toString());

                Tweet newTweet = new Tweet(response);
                Log.d("DEBUG",newTweet.toString());
                tweets.add(0,newTweet);
                aTweets.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG",errorResponse.toString());
                try {
                    JSONArray errors = errorResponse.getJSONArray("errors");

                    if(errors.length() >= 1) {
                        Log.d("DEBUG",errors.length() + "");
                        String errorMsg = errors.getJSONObject(0).getString("message");
                        Toast.makeText(getApplicationContext(),errorMsg,Toast.LENGTH_LONG).show();
                    }
                } catch(JSONException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
