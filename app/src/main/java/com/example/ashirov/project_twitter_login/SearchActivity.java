package com.example.ashirov.project_twitter_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;

public class SearchActivity extends AppCompatActivity {
 TextView mQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mQuery=findViewById(R.id.searchText);
    }




    public void search(View view) {
        String query=mQuery.getText().toString();
        if(query!=null) {
            final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerSearch1);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            final SearchTimeline searchTimeline = new SearchTimeline.Builder()
                    .query(query)
                    .maxItemsPerRequest(50)
                    .build();

            final TweetTimelineRecyclerViewAdapter adapter =
                    new TweetTimelineRecyclerViewAdapter.Builder(this)
                            .setTimeline(searchTimeline)
                            .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                            .build();
            recyclerView.setAdapter(adapter);

        }else{
            Toast.makeText(this,"Enter the Query",Toast.LENGTH_SHORT).show();
        }
    }
}
