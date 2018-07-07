package com.example.ashirov.project_twitter_login;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;

public class UserTweet extends AppCompatActivity {
    public RecyclerView userTimelineRecyclerView;
    public SwipeRefreshLayout swipeRefreshLayout;
    public TweetTimelineRecyclerViewAdapter adapter;
    String mUserEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_timeline);
        setUpSwipeRefreshLayout();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final RecyclerView recyclerView = findViewById(R.id.recyclerUsertimeline);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        com.twitter.sdk.android.tweetui.UserTimeline userTimeline = new com.twitter.sdk.android.tweetui.UserTimeline.Builder()
                .userId(MainActivity.userId)
                .includeReplies(true)
                .includeRetweets(true)
                .maxItemsPerRequest(50)
                .build();

        TweetTimelineRecyclerViewAdapter adapter = new TweetTimelineRecyclerViewAdapter.Builder(this)
                .setTimeline(userTimeline)
                .setOnActionCallback(new Callback<Tweet>() {
                    @Override
                    public void success(Result<Tweet> result) {
                        mUserEmail=result.data.user.email;
                        Toast.makeText(UserTweet.this,mUserEmail,Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void failure(TwitterException exception) {
                    }
                })
                .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                .build();
        recyclerView.setAdapter(adapter);
        Toast.makeText(UserTweet.this,mUserEmail,Toast.LENGTH_SHORT).show();
    }


    private void setUpSwipeRefreshLayout() {
        swipeRefreshLayout = findViewById(R.id.user_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (adapter == null)
                    return;
                swipeRefreshLayout.setRefreshing(true);
                adapter.refresh(new Callback<TimelineResult<Tweet>>() {
                    @Override
                    public void success(Result<TimelineResult<Tweet>> result) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    @Override
                    public void failure(TwitterException exception) {

                        Toast.makeText(UserTweet.this, "Oshibka", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
