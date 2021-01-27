package com.codepath.recyclerviewlab.networking;

import com.codepath.recyclerviewlab.models.Article;
import com.codepath.recyclerviewlab.models.NYTimesArticlesAPIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This code represents the networking layer of the application,
 * Other than updating the API key, you will NOT need to touch this code for the lab,
 * However it may be useful to reference the logic for your future projects
 *
 *
 * IMPORTANT INSTRUCTIONS BELOW ===========================================================
 * TODO: You have to update API_KEY variable with your own NY-Times developer api key, see
 * https://developer.nytimes.com/get-started to create your own developer account,
 * after copy and paste the API key under your Account -> Apps -> <Your App> -> API Keys
 *
 * You will also need to authorize "Article Search API" in your developer App
 */
public class NYTimesApiClient {

    // TODO: Replace the below API key with your own generated key
    /**
     * Checkpoint #1 - API Key
     * Go to https://developer.nytimes.com/get-started and create an account to get a key
     * or use the same API key if you already have one.
     */
    private static final String API_KEY = "q9Bf5NSGAL4fKrDUb2G3OnWiHV5T5yB2";

    // returns the
    private static final String API_FILTER = "headline, web_url, snippet, pub_date, word_count, print_page, print_section, section_name";
    private static final String BEGIN_DATE = "20100101";
    private static final String SORT_BY = "relevance";
    private NYTimesService nyTimesService;

    public NYTimesApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        nyTimesService = retrofit.create(NYTimesService.class);
    }

    /**
     * gets the articles given a specific query, default page number to 0
     * @param articlesListResponse
     * @param query
     */
    public void getArticlesByQuery(final CallbackResponse<List<Article>> articlesListResponse, String query) {
        getArticlesByQuery(articlesListResponse, query, 0);
    }

    /**
     * gets the articles given a specific query and page number
     * @param articlesListResponse
     * @param query
     * @param pageNumber
     */
    public void getArticlesByQuery(final CallbackResponse<List<Article>> articlesListResponse, String query, int pageNumber) {
        // this hard codes to only get the articles sorted by "relevance" sort order
        // you can actually alter the api query to have more search filters or change the sort order to search by "newest"
        // see https://developer.nytimes.com/docs/articlesearch-product/1/routes/articlesearch.json/get for more information on API documentation
        Call<NYTimesArticlesAPIResponse> current = nyTimesService.getArticlesByQuery(
                query,
                pageNumber,
                SORT_BY,
                API_FILTER,
                BEGIN_DATE,
                API_KEY);
        current.enqueue(new Callback<NYTimesArticlesAPIResponse>() {
            @Override
            public void onResponse(Call<NYTimesArticlesAPIResponse> call, Response<NYTimesArticlesAPIResponse> response) {
                NYTimesArticlesAPIResponse model = response.body();
                if (response.isSuccessful()) {
                    articlesListResponse.onSuccess(model.response.docs);
                } else {
                    articlesListResponse.onFailure(new Throwable("error with response code " + response.code() + " " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<NYTimesArticlesAPIResponse> call, Throwable t) {
                articlesListResponse.onFailure(t);
            }
        });
    }
}
