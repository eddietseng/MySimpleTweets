package com.codepath.apps.mysimpletweets.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

/**
 * Created by eddietseng on 8/13/16.
 */
public class UserHeaderFragment extends Fragment {

    // Creates a new fragment given an screen name
    // DemoFragment.newInstance("Hello");
    public static UserHeaderFragment newInstance(Tweet.UserBean user) {
        UserHeaderFragment fragment = new UserHeaderFragment();
        Bundle args = new Bundle();
        args.putParcelable("user", Parcels.wrap(user));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_header, container, false);

        TextView tvName = (TextView)view.findViewById(R.id.tvName);
        TextView tvTagline = (TextView)view.findViewById(R.id.tvTagline);
        TextView tvFollowers = (TextView)view.findViewById(R.id.tvFollowers);
        TextView tvFollowing = (TextView)view.findViewById(R.id.tvFollowing);
        ImageView ivProfilePic = (ImageView)view.findViewById(R.id.ivProfilePic);

        Tweet.UserBean user = Parcels.unwrap(getArguments().getParcelable("user"));

        tvName.setText(user.getName());
        tvTagline.setText("@" + user.getScreen_name());
        tvFollowers.setText(user.getFollowers_count() + " Followers");
        tvFollowing.setText(user.getFriends_count() + " Following");
        Picasso.with(getActivity()).load(user.getProfile_image_url()).into(ivProfilePic);

        return view;
    }

    // Creation lifecycle event
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
