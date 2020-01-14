package com.example.tumblr_copy.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// model for blog posts
public class tumblrPost {

    String photoPath;

    public tumblrPost(JSONObject jsonObject) throws JSONException{

        JSONArray photosArray = jsonObject.getJSONArray("photos");
        JSONObject firstItemInList = photosArray.getJSONObject(0);
        JSONObject original_size = firstItemInList.getJSONObject("original_size");

        photoPath = original_size.getString("url");
    }

    public static List<tumblrPost> fromJsonArray(JSONArray photosJsonArray) throws JSONException {
        List<tumblrPost> postList = new ArrayList<>();
        for (int i = 0; i < photosJsonArray.length(); i++){
            postList.add(new tumblrPost(photosJsonArray.getJSONObject(i)));
        }
        return postList;
    }

    public String getPhotoPath() {
        return photoPath;
    }
}
