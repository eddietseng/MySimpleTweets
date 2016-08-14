package com.codepath.apps.mysimpletweets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

/**
 * Created by eddietseng on 8/5/16.
 */
public class NewTweetFragment extends DialogFragment implements View.OnClickListener {
    private ImageView ivProfile;
    private TextView tvUserName;
    private TextView tvScreenName;
    private EditText etNewTweet;
    private Button btnSend;
    private ImageButton iBtnClear;
    private TextView tvCounter;

    // Defines the listener interface
    public interface NewTweetDialogListener {
        void onFinishEditDialog(String inputText);
    }

    public NewTweetFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static NewTweetFragment newInstance(String title,Tweet.UserBean userData) {
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
        iBtnClear = (ImageButton)view.findViewById(R.id.iBtnClear);
        tvCounter = (TextView) view.findViewById(R.id.tvCounter);

        // Set listener
        btnSend.setOnClickListener(this);
        iBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(),"Clear clicked",Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        etNewTweet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() > 0) {
                    int chars = 140 - editable.length();
                    tvCounter.setText(Integer.toString(chars));
                } else
                    tvCounter.setText("140");
            }
        });

        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Tweet");
        getDialog().setTitle(title);
        Tweet.UserBean user = (Tweet.UserBean) Parcels.unwrap(getArguments().getParcelable("user"));
        Log.d("DEBUG",user.toString());
        ivProfile.setImageResource(0);
        tvUserName.setText(user.getName());
        tvScreenName.setText("@" + user.getScreen_name());

        //Better profile image
        String betterUrl = user.getProfile_image_url().replace("normal","bigger");

        Picasso.with(getContext()).load(betterUrl).into(ivProfile);

        // Show soft keyboard automatically and request focus to field
        etNewTweet.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onClick(View v) {
//        Toast.makeText(getContext(),"Send clicked",Toast.LENGTH_SHORT).show();
        if( etNewTweet.getText().length() > 0 ) {
            NewTweetDialogListener listener = (NewTweetDialogListener)getActivity();
            listener.onFinishEditDialog(etNewTweet.getText().toString());
        }

        // Close the dialog and return back to the parent activity
        dismiss();
    }

}
