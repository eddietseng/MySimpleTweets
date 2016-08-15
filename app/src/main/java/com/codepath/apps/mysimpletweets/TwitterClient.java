package com.codepath.apps.mysimpletweets;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
    public static final String REST_URL = "https://api.twitter.com/1.1";
    public static final String REST_CONSUMER_KEY = "WrCwEHnEZVwl8LWuwJwuAMkzc";
    public static final String REST_CONSUMER_SECRET = "LXep2fZCQOttcX9sVKCg9nMiWqV8KfLE4yFTcgNv9fw2AOB7CC";
    public static final String REST_CALLBACK_URL = "oauth://cpsimpletweets"; // Change this (here and in manifest)

    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

    // Timeline pull
    public void getHomeTimeline(long maxId, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/home_timeline.json");
        //Specify params
        RequestParams params = new RequestParams();
        params.put("count", 25);
        if(maxId == 0)
            params.put("since_id", 1);
        else
            params.put("max_id", maxId - 1);

        //Execute the request
        getClient().get(apiUrl, params, handler);
    }

    // Get current user data
    public void getUserData(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("account/verify_credentials.json");
        //Specify params
        RequestParams params = new RequestParams(); // or null
        //Execute the request
        getClient().get(apiUrl, params, handler);
    }

    // Compose Tweet
    public void postNewTweet(String status,Long tweetId, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/update.json");
        //Specify params
        RequestParams params = new RequestParams();
        params.put("status", status);
        if(tweetId != null)
            params.put("in_reply_to_status_id", tweetId);
        //Execute the request
        getClient().post(apiUrl, params, handler);
    }

    // Mentions Timeline
    public void getMentionsTimeline(long maxId, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/mentions_timeline.json");
        //Specify params
        RequestParams params = new RequestParams();
        params.put("count", 25);
        if(maxId != 0)
            params.put("max_id", maxId - 1);

        //Execute the request
        getClient().get(apiUrl, params, handler);
    }

    // User Timeline
    public void getUserTimeline(String screenName, long maxId, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/user_timeline.json");
        //Specify params
        RequestParams params = new RequestParams();
        params.put("count", 25);
        params.put("screen_name", screenName);
        if(maxId != 0)
            params.put("max_id", maxId - 1);

        //Execute the request
        getClient().get(apiUrl, params, handler);
    }

    // Create Favorite
    public void postCreateFavorite(long id, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("favorites/create.json");
        //Specify params
        RequestParams params = new RequestParams();
        params.put("id", id);

        //Execute the request
        getClient().post(apiUrl, params, handler);
    }

    // Delete Favorite
    public void postDeleteFavorite(long id, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("favorites/destroy.json");
        //Specify params
        RequestParams params = new RequestParams();
        params.put("id", id);

        //Execute the request
        getClient().post(apiUrl, params, handler);
    }

    public void getRetweetsTimeline(long id, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/retweets/"+id+".json");
        //Specify params
        RequestParams params = new RequestParams();
        params.put("count", 25);

        //Execute the request
        getClient().get(apiUrl, params, handler);
    }

	/* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
     * 	  i.e getApiUrl("statuses/home_timeline.json");
	 * 2. Define the parameters to pass to the request (query or body)
	 *    i.e RequestParams params = new RequestParams("foo", "bar");
	 * 3. Define the request method and make a call to the client
	 *    i.e client.get(apiUrl, params, handler);
	 *    i.e client.post(apiUrl, params, handler);
	 */
}