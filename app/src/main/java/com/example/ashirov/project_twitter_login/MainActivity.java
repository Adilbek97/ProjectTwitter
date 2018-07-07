package com.example.ashirov.project_twitter_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
/*
public class MainActivity extends AppCompatActivity {
    TwitterLoginButton loginButton;
    TwitterSession session;
    Button button;
    Tweet tweet;
    Media media;
    SharedPreferences pref;

    private static String CONSUMER_KEY = "Your API Key here";
    private static String CONSUMER_SECRET = "Your API Secret here";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.activity_main);

        pref = getPreferences(0);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("CONSUMER_KEY", CONSUMER_KEY);
        edit.putString("CONSUMER_SECRET", CONSUMER_SECRET);
        edit.commit();

        LoginFragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, login);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
       /* loginButton =  findViewById(R.id.login_buttonn);
       // textView.setText(session.getUserName());
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;
                String name=result.data.getUserName();

                //final Call<User> user=Twitter.getAp
                TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
                StatusesService statusesService = twitterApiClient.getStatusesService();
                Call<Tweet> call = statusesService.show(524971209851543553L, null, null, null);
                call.enqueue(new Callback<Tweet>() {
                    @Override
                    public void success(Result<Tweet> result) {
                        //Do something with result
                       String res= result.data.inReplyToUserIdStr;
                       int count=result.data.favoriteCount;


                    }

                    public void failure(TwitterException exception) {
                        //Do something on failure
                    }
                });
                login(session);
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Toast.makeText(MainActivity.this,"Avtorizasia kata",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public  void login(TwitterSession session){
        String username=session.getUserName();
        long Userid=session.getUserId();
        Intent intent=new Intent(this,Homepage.class);
        intent.putExtra("username",username);
        startActivity(intent);
//--------------
        void writeData(ContentResolver contentResolver)
        {
            activity.client.authorize(activity, new Callback<TwitterSession>()
            {
                @Override
                public void success(Result<TwitterSession> result)
                {
                    writeInfile(result);
                }
                @Override
                public void failure(TwitterException e)
                {
                    Log.e("Twitter Log in", e.toString());
                }
            });
        }


        void writeInFile(Result<TwitterSession> result)
        {
            userTimeline = new UserTweet.Builder()
                    .userId(result.data.getUserId())
                    .includeRetweets(false)
                    .maxItemsPerRequest(200)
                    .build();

            userTimeline.next(null, callback);
        }

        Callback<TimelineResult<Tweet>> callback = new Callback<TimelineResult<Tweet>>()
        {
            @Override
            public void success(Result<TimelineResult<Tweet>> searchResult)
            {
                List<Tweet> tweets = searchResult.data.items;

                for (Tweet tweet : tweets)
                {
                    String str = tweet.text; //Here is the body
                    tweet.

                }

*/

 //   }
/*
            @Override
            public void failure(TwitterException exception) {

            }
            //---------------

            @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
    */

/*
}*/

//------------------------------222222222222---------
import android.content.Intent;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;

public class MainActivity extends AppCompatActivity {
    TwitterLoginButton loginButton;
    public  static long userId;
    public static String userName;
    public static String mUserEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initializing twitter instance
        Twitter.initialize(this);
        setContentView(R.layout.activity_main);

        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);

        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                /*
                  This provides TwitterSession as a result
                  This will execute when the authentication is successful
                 */

                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;
                //Calling login method and passing twitter session

                login(session);
            }

            @Override
            public void failure(TwitterException exception) {
                //Displaying Toast message
                Toast.makeText(MainActivity.this, "Authentication failed!", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void login(TwitterSession session)
    {
        final String[] userEmail = new String[1];
        TwitterAuthClient authClient = new TwitterAuthClient();
        authClient.requestEmail(session, new Callback<String>() {
            @Override
            public void success(Result<String> result) {
                userEmail[0] =result.data;
                mUserEmail=userEmail[0];
                // Do something with the result, which provides the email address
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Toast.makeText(MainActivity.this,"Email alingan jok",Toast.LENGTH_SHORT).show();
            }
        });
        userId=session.getUserId();
        userName= session.getUserName();

        //String userId =String.valueOf(session.getUserId());
        Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
        Toast.makeText(MainActivity.this,mUserEmail,Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}





