package com.codepath.recyclerviewlab.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Article {
    @SerializedName("id")
    public String id;

    @SerializedName("headline")
    public ArticleHeadline headline;

    @SerializedName("web_url")
    public String webUrl;

    @SerializedName("multimedia")
    public List<Multimedia> multimedia;

    @SerializedName("snippet")
    public String snippet;

    @SerializedName("word_count")
    public String wordCount;

    @SerializedName("print_section")
    public String printSection;

    @SerializedName("section_name")
    public String sectionName;

    @SerializedName("print_page")
    public String printPage;

    @SerializedName("pub_date")
    public String publishDate;
}
