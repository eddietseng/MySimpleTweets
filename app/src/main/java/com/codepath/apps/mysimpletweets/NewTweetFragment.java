package com.codepath.apps.mysimpletweets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.models.CurrentUser;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

/**
 * Created by eddietseng on 8/5/16.
 */
public class NewTweetFragment extends DialogFragment {
    private ImageView ivProfile;
    private TextView tvUserName;
    private TextView tvScreenName;
    private EditText etNewTweet;
    private Button btnSend;

    public NewTweetFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static NewTweetFragment newInstance(String title,CurrentUser userData) {
        NewTweetFragment frag = new NewTweetFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        args.putParcelable("user", Parcels.wrap(userData));
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_compose_tweet, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        ivProfile = (ImageView) view.findViewById(R.id.ivUserProfile);
        tvUserName = (TextView) view.findViewById(R.id.tvUser);
        tvScreenName = (TextView) view.findViewById(R.id.tvScreenNameFrag);
        etNewTweet = (EditText) view.findViewById(R.id.etNewTweet);
        btnSend = (Button)view.findViewById(R.id.btnSend);

        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Tweet");
        getDialog().setTitle(title);
        CurrentUser user = (CurrentUser) Parcels.unwrap(getArguments().getParcelable("user"));
        Log.d("DEBUG",user.toString());
        ivProfile.setImageResource(0);
        tvUserName.setText(user.getName());
        tvScreenName.setText("@" + user.getScreen_name());
        Picasso.with(getContext()).load(user.getProfile_image_url_https()).into(ivProfile);

        // Show soft keyboard automatically and request focus to field
        etNewTweet.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}
