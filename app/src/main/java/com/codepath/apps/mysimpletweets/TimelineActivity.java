package com.codepath.apps.mysimpletweets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.mysimpletweets.fragments.HomeTimeLineFragment;
import com.codepath.apps.mysimpletweets.fragments.MentionsTimelineFragment;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity
        implements NewTweetFragment.NewTweetDialogListener{

    private Tweet.UserBean userData;
    private TwitterClient client;
    private ViewPager viewPager;
    private TweetsPagerAdapter tweetsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        client = TwitterApplication.getRestClient();

        storeCurrentUserData();

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.twitter_logo_trans);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // Get the viewpager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        // Set the viewpager adapter
        tweetsPagerAdapter = new TweetsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tweetsPagerAdapter);
        // Find the sliding tabstrip
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the tabstrip to the viewpager
        tabStrip.setViewPager(viewPager);
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
        } else if (id == R.id.miProfile) {
            launchProfileActivity(userData);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void launchProfileActivity(Tweet.UserBean userData) {
        Intent i = new Intent(this, ProfileActivity.class);
        // pass in the article into intent
        i.putExtra("user", Parcels.wrap(userData));
        startActivity(i);
    }

    // Return the order fragments in the view pager
    public class TweetsPagerAdapter extends FragmentPagerAdapter {
        private  String tabtitles[] = { "Home", "Mentions" };
        SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

        // Adapter gets the manager insert or remove fragment from activity
        public TweetsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // The order and creation of fragment within the pager
        @Override
        public Fragment getItem(int position) {
            if( position == 0 ) {
                return new HomeTimeLineFragment();
            } else if ( position == 1 ) {
                return new MentionsTimelineFragment();
            } else
                return null;
        }

        // Return the tab title
        @Override
        public CharSequence getPageTitle(int position) {
            return tabtitles[position];
        }

        // Return how many fragments to swipe between
        @Override
        public int getCount() {
            return tabtitles.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment)super.instantiateItem(container, position);
            registeredFragments.put(position,fragment);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }

        public Fragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }
    }

    private void showNewTweetDialog(Tweet.UserBean userData) {
        FragmentManager fm = getSupportFragmentManager();
        NewTweetFragment editNameDialogFragment = NewTweetFragment.newInstance("New Tweet", userData);
        editNameDialogFragment.show(fm, "fragment_edit_name");
    }

    private void storeCurrentUserData() {
        client.getUserData(new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("DEBUG",responseString);
                Tweet.UserBean currentUser = Tweet.UserBean.parseJSON(responseString);
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

    public void onFinishEditDialog(final String inputText) {
        final Fragment fragment = tweetsPagerAdapter.getRegisteredFragment(viewPager.getCurrentItem());

        client.postNewTweet(inputText, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("DEBUG",response.toString());

                Tweet newTweet = new Tweet(response);
                Log.d("DEBUG",newTweet.toString());

                if(fragment instanceof HomeTimeLineFragment)
                    ((HomeTimeLineFragment)fragment).addTweet(0,newTweet);
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
