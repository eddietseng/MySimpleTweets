package com.codepath.apps.mysimpletweets;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.fragments.DetailTweetFragment;
import com.codepath.apps.mysimpletweets.fragments.RetweetLineFragment;
import com.codepath.apps.mysimpletweets.models.Tweet;

import org.parceler.Parcels;

public class DetailTweetActivity extends AppCompatActivity {
    Tweet.UserBean userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tweet);

        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
//        Tweet.UserBean user = Parcels.unwrap(getIntent().getParcelableExtra("user"));
//
//        this.userData = user; TODO

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDetailTweet);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.twitter_logo_trans);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        // Get access to the custom title view
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_detail_tweet_title);
        mTitle.setText("Tweet");

        if (savedInstanceState == null) {
            DetailTweetFragment detailTweetFragment = DetailTweetFragment.newInstance(tweet);
            RetweetLineFragment retweetLineFragment = RetweetLineFragment.newInstance(tweet.getId());
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailHeader,detailTweetFragment);
            ft.replace(R.id.flRetweetContainer,retweetLineFragment);
            ft.commit();
        }
    }

    public Tweet.UserBean getUserData() {
        return userData;
    }
}
