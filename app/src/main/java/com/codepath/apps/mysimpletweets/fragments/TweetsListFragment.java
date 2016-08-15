package com.codepath.apps.mysimpletweets.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codepath.apps.mysimpletweets.DetailTweetActivity;
import com.codepath.apps.mysimpletweets.NewTweetFragment;
import com.codepath.apps.mysimpletweets.ProfileActivity;
import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.TimelineActivity;
import com.codepath.apps.mysimpletweets.TwitterApplication;
import com.codepath.apps.mysimpletweets.TwitterClient;
import com.codepath.apps.mysimpletweets.adapters.TweetsAdapter;
import com.codepath.apps.mysimpletweets.helper.EndlessRecyclerViewScrollListener;
import com.codepath.apps.mysimpletweets.helper.RecyclerViewClickListener;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.codepath.apps.mysimpletweets.utility.NetworkUtil;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by eddietseng on 8/12/16.
 */
public class TweetsListFragment extends Fragment implements RecyclerViewClickListener {
    private ArrayList<Tweet> tweets;
    private TweetsAdapter aTweets;
    protected RecyclerView rvTweets;
    protected SwipeRefreshLayout swipeContainer;

    protected TwitterClient client;
    protected ProgressDialog pd;

    // Inflation Logic
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweets_list, container, false);

        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);
        setOnRefreshListener();

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //Find listView
        rvTweets = (RecyclerView) v.findViewById(R.id.rvTweets);
        //Connect adapter to list view
        rvTweets.setAdapter(aTweets);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvTweets.setLayoutManager(linearLayoutManager);
        addOnScrollListener(linearLayoutManager);

        return v;
    }

    // Creation lifecycle event
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();

        pd = new ProgressDialog(getContext());
        pd.setTitle("Loading...");
        pd.setMessage("Please wait.");
        pd.setCancelable(false);

        //Create the arrayList
        tweets = new ArrayList<>();
        //Construct adapter
        aTweets = new TweetsAdapter(getActivity(), tweets, this);
    }

    protected void setOnRefreshListener() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                if(NetworkUtil.isOnline())
                    populateTimeline();
                else {
                    // Now we call setRefreshing(false) to signal refresh has finished
                    swipeContainer.setRefreshing(false);
                }
            }
        });
    }

    protected void addOnScrollListener(LinearLayoutManager linearLayoutManager) {
        rvTweets.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {

            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                customLoadMoreDataFromApi(totalItemsCount);
            }
        });
    }

    protected void populateTimeline() {
    }

    protected void customLoadMoreDataFromApi(int totalItemsCount) {
    }

    protected boolean checkNetwork() {
        if(!NetworkUtil.isOnline()) {
            NetworkUtil.showNoNetworkMessage(getContext());
            return false;
        }
        return true;
    }

    public void addAll(List<Tweet> tweets) {
        aTweets.addAll(tweets);
        aTweets.notifyDataSetChanged();

        Log.d("DEBUG", tweets.toString());
    }

    public void clearAll() {
        tweets.clear();
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    public void addTweet(int index, Tweet tweet) {
        tweets.add(index, tweet);
        aTweets.notifyDataSetChanged();
    }

    @Override
    public void onViewClicked(View v, int position) {

        if(v.getId() == R.id.ivProfileImage){
            Tweet.UserBean user = tweets.get(position).getUser();
            Intent i = new Intent(getActivity(), ProfileActivity.class);
            // pass in the article into intent
            i.putExtra("user", Parcels.wrap(user));
            i.putExtra("screen_name", user.getScreen_name());
            startActivity(i);
        }
        else if(v.getId() == R.id.ibtnReTweet) {
            if(getActivity() instanceof TimelineActivity ) {
                Tweet.UserBean userData = ((TimelineActivity)getActivity()).getUserData();
                Tweet tweet = tweets.get(position);
                FragmentManager fm = getFragmentManager();
                NewTweetFragment editNameDialogFragment =
                        NewTweetFragment.newInstance("New Tweet", userData, tweet);
                editNameDialogFragment.show(fm, "fragment_edit_name");
            }
            else if(getActivity() instanceof ProfileActivity ) {
                Tweet.UserBean userData = ((ProfileActivity)getActivity()).getUserData();
                Tweet tweet = tweets.get(position);
                FragmentManager fm = getFragmentManager();
                NewTweetFragment editNameDialogFragment =
                        NewTweetFragment.newInstance("New Tweet", userData, tweet);
                editNameDialogFragment.show(fm, "fragment_edit_name");
            }//TODO
        }
        else if(v.getId() == R.id.ibtnFavorite) {
            Tweet tweet = tweets.get(position);
            boolean currentSt = tweet.isFavorited();
            sendFavorite(!currentSt, tweet.getId(), position);
        }
    }

    @Override
    public void onRowClicked(int position) {
        Tweet tweet = tweets.get(position);
        Intent i = new Intent(getActivity(), DetailTweetActivity.class);
        i.putExtra("tweet", Parcels.wrap(tweet));
        startActivity(i);
    }

    protected void sendFavorite(boolean isFavorited, long id,final int position) {
        if( !checkNetwork() )
            return;

        pd.show();
        if(isFavorited) {
            client.postCreateFavorite(id, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Log.d("DEBUG",response.toString());

                    Tweet newTweet = new Tweet(response);
                    Log.d("DEBUG",newTweet.toString());

                    Tweet tweet = tweets.get(position);
                    tweet.setFavorited(true);
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
                            Toast.makeText(getContext(),errorMsg,Toast.LENGTH_LONG).show();
                        }
                    } catch(JSONException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        else {
            client.postDeleteFavorite(id, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Log.d("DEBUG",response.toString());

                    Tweet newTweet = new Tweet(response);
                    Log.d("DEBUG",newTweet.toString());

                    Tweet tweet = tweets.get(position);
                    tweet.setFavorited(false);
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
                            Toast.makeText(getContext(),errorMsg,Toast.LENGTH_LONG).show();
                        }
                    } catch(JSONException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        pd.dismiss();
    }
}
