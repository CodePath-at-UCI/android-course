package com.codepath.recyclerviewlab.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesResponse {

    @SerializedName("docs")
    public List<Article> docs;
}
