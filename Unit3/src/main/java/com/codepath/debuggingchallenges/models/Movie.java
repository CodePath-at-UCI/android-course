package com.codepath.debuggingchallenges.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie {
    private String title;
    private String posterUrl;
    private double rating;

    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterUrl = jsonObject.getString("poster_path");
        this.rating = jsonObject.getDouble("vote_average");

        // #8.
        this.title = jsonObject.getString("original_title");
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public String getPosterUrl() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterUrl);
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Movie> results = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                results.add(new Movie(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d(Movie.class.getSimpleName(), "fromJSONArray: " + results);
        return results;
    }
}
