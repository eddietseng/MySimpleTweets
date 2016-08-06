package com.codepath.apps.mysimpletweets;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.codepath.apps.mysimpletweets.adapters.TweetsAdapter;
import com.codepath.apps.mysimpletweets.helper.EndlessRecyclerViewScrollListener;
import com.codepath.apps.mysimpletweets.models.CurrentUser;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {

    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private TweetsAdapter aTweets;
    private RecyclerView rvTweets;
    private CurrentUser userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
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
                List<Tweet> rTweets = Tweet.parseJSON(responseString);
                tweets.addAll(rTweets);
                aTweets.notifyDataSetChanged();
                Log.d("DEBUG",tweets.toString());
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

    public void onSendButtonClicked(View v) {
        Toast.makeText(this,"Send clicked",Toast.LENGTH_SHORT).show();
    }

    public void onClearButtonClicked(View v) {
        Toast.makeText(this,"Clear clicked",Toast.LENGTH_SHORT).show();
    }
}
