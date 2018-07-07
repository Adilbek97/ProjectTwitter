package com.example.ashirov.project_twitter_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

public class ExampleTweet extends AppCompatActivity {

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_tweet);

        final LinearLayout myLayout
                = (LinearLayout) findViewById(R.id.my_tweet_layout);
        try {
            final long tweetId = MainActivity.userId;//510908133917487104L;
            TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
                @Override
                public void success(Result<Tweet> result) {
                    Tweet tweet = result.data;
                    myLayout.addView(new TweetView(ExampleTweet.this, tweet));
                }
                @Override
                public void failure(TwitterException exception) {
                    Toast.makeText(ExampleTweet.this, "Oshibka", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Oshibka", Toast.LENGTH_SHORT).show();
        }
    }
}
