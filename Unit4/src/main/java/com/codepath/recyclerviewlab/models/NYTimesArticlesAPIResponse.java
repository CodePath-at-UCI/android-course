package com.codepath.recyclerviewlab.models;

import com.google.gson.annotations.SerializedName;

public class NYTimesArticlesAPIResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("response")
    public ArticlesResponse response;
}
