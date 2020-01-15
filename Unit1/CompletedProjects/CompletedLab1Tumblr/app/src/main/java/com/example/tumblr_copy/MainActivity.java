package com.example.tumblr_copy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.tumblr_copy.adapters.PhotoAdapter;
import com.example.tumblr_copy.models.tumblrPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    //api string of HumansOfNY Tumblr posts
    public static final String blog_HNY = "https://api.tumblr.com/v2/blog/humansofnewyork.tumblr.com/posts/photo?api_key=Q6vHoaVm5L1u2ZAW1fqv3Jw48gFzYVg9P0vH0VHl3GVy6quoGV";

    // String used for logging & debugging purposes
    public static final String TAG = "MainActivity";

    //A list of tumblrPosts that we'll be using
    List<tumblrPost> postsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postsList = new ArrayList<>();

        // use FindViewById for RecyclerView rvPhotos
        RecyclerView rvPhotos = findViewById(R.id.rvPhotos);

        // Create the adapter
        final PhotoAdapter photoAdapter = new PhotoAdapter(this, postsList);

        // Set the adapter on the recycler view
        rvPhotos.setAdapter(photoAdapter);

        // Set a Layout Manager on the recycler View
        rvPhotos.setLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(blog_HNY, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    // fetch JSONObject named response
                    JSONObject response = jsonObject.getJSONObject("response");
                    //get posts
                    JSONArray postArray = response.getJSONArray("posts");
                    // add posts to postsList
                    postsList.addAll(tumblrPost.fromJsonArray(postArray));
                    // Let adapter know data has been changed
                    photoAdapter.notifyDataSetChanged();

                    // //Log posts size
                    Log.i(TAG, "posts: " + postsList.size());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });
    }
}
