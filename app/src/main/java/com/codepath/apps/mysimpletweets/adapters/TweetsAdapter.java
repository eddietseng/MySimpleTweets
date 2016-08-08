package com.codepath.apps.mysimpletweets.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by eddietseng on 8/5/16.
 */
public class TweetsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Tweet> tweets;

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tweet, parent, false);
        TweetViewHolder viewHolder = new TweetViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        TweetViewHolder tvh = (TweetViewHolder)holder;
        configureTweetViewHolder(tvh,position);
    }

    private void configureTweetViewHolder(TweetViewHolder tvh, int position){
        Tweet tweet = tweets.get(position);

        //clear out old image for a recycled view
        tvh.ivProfileImage.setImageResource(0);
        tvh.ivMedia.setImageResource(0);
        tvh.tvUserName.setText(tweet.getUser().getName());
        tvh.tvBody.setText(tweet.getText());
        tvh.tvScreenName.setText("@" + tweet.getUser().getScreen_name());
        tvh.tvRelativeTimestamp.setText(getRelativeTimeAgo(tweet.getCreated_at()));

        //Better profile image
        String betterUrl = tweet.getUser().getProfile_image_url().replace("normal","bigger");

        Picasso.with(context).load(betterUrl).into(tvh.ivProfileImage);

        if(tweet.getEntities() != null && tweet.getEntities().getMedia() != null &&
                tweet.getEntities().getMedia().size() > 0) {
            String mediaUrl = tweet.getEntities().getMedia().get(0).getMedia_url();
            Picasso.with(context).load(mediaUrl).resize(600, 450)
                    .centerCrop().into(tvh.ivMedia);
        }
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }

    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            PrettyTime p = new PrettyTime();
            relativeDate = p.format(new Date(dateMillis));

            if(relativeDate.contains("moments")) {
                long delta = System.currentTimeMillis() - dateMillis;
                if( delta >= 60000) {
                    int min = (int) Math.floor(delta / 60000);
                    relativeDate = min + "m";
                }
                else {
                    int sec = (int) Math.floor(delta/1000);
                    relativeDate = sec +"s";
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(relativeDate.contains("min")) {
            relativeDate = relativeDate.substring(0,relativeDate.indexOf(" ")) + "m";
        } else if(relativeDate.contains("hour")) {
            relativeDate = relativeDate.substring(0,relativeDate.indexOf(" ")) + "h";
        } else if (relativeDate.contains("day")) {
            relativeDate = relativeDate.substring(0,relativeDate.indexOf(" ")) + "d";
        }

        return relativeDate;
    }

    public class TweetViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        ImageView ivProfileImage;
        TextView tvUserName;
        TextView tvBody;
        TextView tvScreenName;
        TextView tvRelativeTimestamp;
        ImageView ivMedia;

        public TweetViewHolder(View itemView) {
            super(itemView);
            ivProfileImage = (ImageView)itemView.findViewById(R.id.ivProfileImage);
            tvUserName = (TextView)itemView.findViewById(R.id.tvUserName);
            tvBody = (TextView)itemView.findViewById(R.id.tvBody);
            tvScreenName = (TextView)itemView.findViewById(R.id.tvScreenName);
            tvRelativeTimestamp = (TextView)itemView.findViewById(R.id.tvRelativeTimestamp);
            ivMedia = (ImageView)itemView.findViewById(R.id.ivMedia);

            itemView.setOnClickListener( this );
        }

        @Override
        public void onClick(View v) {

        }
    }
}
