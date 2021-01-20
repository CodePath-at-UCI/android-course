package com.codepath.debuggingchallenges.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.debuggingchallenges.R;
import com.codepath.debuggingchallenges.adapters.MoviesAdapter;
import com.codepath.debuggingchallenges.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import okhttp3.Headers;

/**
 * Milestone 4 - View Movies
 * 1. MoviesActivity > onCreate() - initiate the movies array:
 *      add:    movies = new ArrayList<>();
 *      why:    We need to initialize a pointer to our movies array pointer so when we pass it to
 *              the adapter's constructor, we'll be able to put movies in it.
 * 2. MoviesActivity > onCreate() - add the layout manager:
 *      add:    rvMovies.setLayoutManager(new LinearLayoutManager(this));
 *      why:    Every RecyclerView needs a layout manager so it knows where to place the views.
 * 3. MoviesActivity > onCreate() - change MoviesAdapter:
 *      from:   MoviesAdapter adapter = new MoviesAdapter(movies)
 *      to:     adapter = new MoviesAdapter(movies);
 *      why:    When we use this new way of defining the adapter, it will be available to the
 *              scope of the whole activity and you can call notifyDataSetChanged in onSuccess().
 * 4. MoviesActivity > fetchMovies() - change string url
 *      from:   "https://api.themoviedb.org/3/movie/now_playing?api_key=";
 *      to:     "https://api.themoviedb.org/3/movie/now_playing?api_key=" + API_KEY;
 *      why:    We want to add our private API key to the API's base url so we can utilize the API.
 * 5. MoviesActivity > fetchMovies() - change movies
 *      from:   movies = Movie.fromJSONArray(moviesJson);
 *      to:     movies.addAll(Movie.fromJSONArray(moviesJson));
 *      why:    If we use "movies = ..." we overwrite the movies pointer, and we won't be able to
 *              access our movies outside of this method. By using "movies.addAll" we're calling a
 *              method on the EXISTING movies pointer which allows us to save the data and use it
 *              outside of this method.
 * 6. MoviesActivity > fetchMovies() - add
 *      add:    adapter.notifyDataSetChanged();
 *      why:    We want to notify the adapter that we updated the data in the movies array.
 *              If we don't do this, the movies we just extracted from the API won't be saved.
 * 7. MoviesAdapter > getItemCount() - change the returning result
 *      from:   0;
 *      to:     movies.size();
 *      why:    getItemCount() relies on the total number of items we want to look at.
 *              In this case, we want to look at every movie in our movies array.
 * 8. Movie.java > Movie() - change
 *      from:   this.title = jsonObject.getString("original-title");
 *      to:     this.title = jsonObject.getString("original_title");
 *      why:    The API keyword "original_title" needs to be exact or else we can't grab the data
 *              from the API's JSON response. You can find out the right spelling of each keyword by
 *              looking at the JSON response by copying/pasting your API URL (include the key) in a
 *              browser OR by printing the response you get in-app after making the API call.
 */
public class MoviesActivity extends AppCompatActivity {

    private static final String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";

    RecyclerView rvMovies;
    MoviesAdapter adapter;
    ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        rvMovies = findViewById(R.id.rvMovies);

        // #1. Instantiate our array of movies so we can hold data
        movies = new ArrayList<>();

        // Create the adapter to convert the array to views
        // MoviesAdapter adapter = new MoviesAdapter(movies);
        // #2. use the existing adapter reference instead of overwriting it
        adapter = new MoviesAdapter(movies);

        // Attach the adapter to a RecyclerView
        rvMovies.setAdapter(adapter);

        // #3. Add layout Manager to setup view layout for RecyclerView
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        fetchMovies();
    }


    private void fetchMovies() {
        // #4. App API key to url to take advantage of the movie db API
        String url = " https://api.themoviedb.org/3/movie/now_playing?api_key=" + API_KEY;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON response) {
                try {
                    JSONArray moviesJson = response.jsonObject.getJSONArray("results");

                    // #5.
                    movies.addAll(Movie.fromJSONArray(moviesJson));

                    // #6.
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.e(MoviesActivity.class.getSimpleName(), "Error retrieving movies: ", throwable);
            }
        });
    }
}
