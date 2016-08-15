package com.codepath.apps.mysimpletweets.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.TwitterApplication;
import com.codepath.apps.mysimpletweets.TwitterClient;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.codepath.apps.mysimpletweets.utility.NetworkUtil;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

/**
 * Created by eddietseng on 8/14/16.
 */
public class DetailTweetFragment extends Fragment {

    protected TwitterClient client;
    protected ProgressDialog pd;
    ImageButton ibtnDetailFavorite;

    public static DetailTweetFragment newInstance(Tweet tweet) {
        DetailTweetFragment fragment = new DetailTweetFragment();
        Bundle args = new Bundle();
        args.putParcelable("tweet", Parcels.wrap(tweet));
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_tweet_header, container, false);

        client = TwitterApplication.getRestClient();

        pd = new ProgressDialog(getContext());
        pd.setTitle("Loading...");
        pd.setMessage("Please wait.");
        pd.setCancelable(false);

        TextView tvDetailUserName = (TextView)view.findViewById(R.id.tvDetailUserName);
        TextView tvDetailScreenName = (TextView)view.findViewById(R.id.tvDetailScreenName);
        TextView tvDetailTimestamp = (TextView)view.findViewById(R.id.tvDetailTimestamp);
        TextView tvDetailBody = (TextView)view.findViewById(R.id.tvDetailBody);
        TextView tvDetailRetweets = (TextView)view.findViewById(R.id.tvDetailRetweets);
        TextView tvDetailLikes = (TextView)view.findViewById(R.id.tvDetailLikes);
        ImageView ivDetailProfileImage = (ImageView)view.findViewById(R.id.ivDetailProfileImage);
        ImageView ivDetailMedia = (ImageView)view.findViewById(R.id.ivDetailMedia);
        ImageButton ibtnDetailReTweet = (ImageButton)view.findViewById(R.id.ibtnDetailReTweet);
        ibtnDetailFavorite = (ImageButton)view.findViewById(R.id.ibtnDetailFavorite);

        Tweet tweet = Parcels.unwrap(getArguments().getParcelable("tweet"));

        tvDetailUserName.setText(tweet.getUser().getName());
        tvDetailScreenName.setText("@"+tweet.getUser().getScreen_name());
        tvDetailTimestamp.setText(tweet.getCreated_at());
        tvDetailBody.setText(tweet.getText());
        tvDetailRetweets.setText(tweet.getRetweet_count()+" RETWEETS");
        tvDetailLikes.setText(tweet.getFavorite_count()+" LIKES");

        if(tweet.isFavorited())
            ibtnDetailFavorite.setImageResource(R.drawable.ic_favorite_black_24dp);
        else
            ibtnDetailFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);

        //Better profile image
        String betterUrl = tweet.getUser().getProfile_image_url().replace("normal","bigger");

        Picasso.with(getActivity()).load(betterUrl).into(ivDetailProfileImage);

        if(tweet.getEntities() != null && tweet.getEntities().getMedia() != null &&
                tweet.getEntities().getMedia().size() > 0) {
            String mediaUrl = tweet.getEntities().getMedia().get(0).getMedia_url();
            Picasso.with(getActivity()).load(mediaUrl).resize(400, 300)
                    .centerCrop().into(ivDetailMedia);
        }

        ibtnDetailReTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ibtnDetailFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tweet tweet = Parcels.unwrap(getArguments().getParcelable("tweet"));
                boolean currentSt = tweet.isFavorited();
                sendFavorite(!currentSt, tweet);

                if(!currentSt)
                    ibtnDetailFavorite.setImageResource(R.drawable.ic_favorite_black_24dp);
                else
                    ibtnDetailFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }
        });

        return view;
    }

    protected void sendFavorite(boolean isFavorited,final Tweet tweet) {
        if( !checkNetwork() )
            return;

        pd.show();
        if(isFavorited) {
            client.postCreateFavorite(tweet.getId(), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Log.d("DEBUG",response.toString());

                    Tweet newTweet = new Tweet(response);
                    Log.d("DEBUG",newTweet.toString());

                    tweet.setFavorited(true);
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
            client.postDeleteFavorite(tweet.getId(), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Log.d("DEBUG",response.toString());

                    Tweet newTweet = new Tweet(response);
                    Log.d("DEBUG",newTweet.toString());

                    tweet.setFavorited(false);
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

    protected boolean checkNetwork() {
        if(!NetworkUtil.isOnline()) {
            NetworkUtil.showNoNetworkMessage(getContext());
            return false;
        }
        return true;
    }
}
