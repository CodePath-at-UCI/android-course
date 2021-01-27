package com.codepath.recyclerviewlab.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.recyclerviewlab.R;
import com.codepath.recyclerviewlab.models.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleResultsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = ArticleResultsRecyclerViewAdapter.class.getSimpleName();

    // Define a static int for each view type, loading = showing the loading spinner at the end of the list
    public static final int VIEW_TYPE_LOADING = 0;
    // article = each article that shows up
    public static final int VIEW_TYPE_ARTICLE = 1;

    public static final int VIEW_TYPE_FIRST_PAGE_ARTICLE = 2;

    private final List<Article> articleList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ARTICLE) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_article_result, parent, false);
            return new ArticleViewHolder(view);
        }
        else if (viewType == VIEW_TYPE_FIRST_PAGE_ARTICLE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_article_result_first_page, parent, false);
            return new FirstPageArticleViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.article_progress, parent, false);
            return new LoadingViewHolder(view);
        }

    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        final TextView headlineView;
        final TextView snippetView;
        final TextView dateView;

        ArticleViewHolder(View view) {
            super(view);
            dateView = view.findViewById(R.id.article_pub_date);
            headlineView = view.findViewById(R.id.article_headline);
            snippetView = view.findViewById(R.id.article_snippet);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof FirstPageArticleViewHolder) {
            FirstPageArticleViewHolder articleViewHolder = (FirstPageArticleViewHolder) holder;
            articleViewHolder.firstPageHeader.setText(holder.itemView.getContext().getString(R.string.first_page, articleList.get(position).sectionName));
        }
        if (holder instanceof ArticleViewHolder) {
            // Prevent the app from crashing if the user tries to see more results before
            // the api call to get more results is done.
            if (position >= articleList.size()) {
                return;
            }
            Article article = articleList.get(position);
            ArticleViewHolder articleViewHolder = (ArticleViewHolder) holder;
            articleViewHolder.headlineView.setText(article.headline.main);
            articleViewHolder.snippetView.setText(article.snippet);
        }

        // Bonus: Try searching up how to display the date using `SimpleDateFormat`
    }

    @Override
    public int getItemCount() {
        return (articleList.size() == 0) ? 0 : articleList.size() + 1;
    }

    // This method clears the existing dataset and adds new articles
    public void setNewArticles(List<Article> articles) {
        articleList.clear();
        articleList.addAll(articles);
    }

    // This method adds all articles to the existing dataset
    public void addArticles(List<Article> articles) {
        articleList.addAll(articles);
    }

    /**
     * Checkpoint #3
     * Load a different "loading" view if we're at the end of this "page" of articles to show that
     * we're making an API call to get more results. This helps communicate to the user why the app
     * isn't showing them any additional data yet.
     */

    @Override
    public int getItemViewType(int position) {
        if (position >= articleList.size()) {
            return VIEW_TYPE_LOADING;
        }
        else if ("1".equals(articleList.get(position).printPage)) {
            return VIEW_TYPE_FIRST_PAGE_ARTICLE;
        }
        else {
            return VIEW_TYPE_ARTICLE;
        }
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    // rather than creating a completely new ViewHolder, we can extend the existing ArticleViewHolder
    static class FirstPageArticleViewHolder extends ArticleViewHolder {
        final TextView firstPageHeader;

        FirstPageArticleViewHolder(View view) {
            super(view);
            firstPageHeader = view.findViewById(R.id.first_page_header);
        }
    }


}
