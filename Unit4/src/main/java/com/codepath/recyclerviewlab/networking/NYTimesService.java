package com.codepath.recyclerviewlab.networking;

import com.codepath.recyclerviewlab.models.NYTimesArticlesAPIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NYTimesService {

    @GET("svc/search/v2/articlesearch.json")
    Call<NYTimesArticlesAPIResponse> getArticlesByQuery(
            @Query("q") String query,
            @Query("page") int page,
            @Query("sort") String sortBy,
            @Query("fl") String filter,
            @Query("begin_date") String beginDate,
            @Query("api-key") String apikey);
}
