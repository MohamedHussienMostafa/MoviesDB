package com.comelite.moviesdb.apis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response<T> {

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("total_results")
    @Expose
    private int totalResults;

    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    @SerializedName("results")
    @Expose
    private T response;

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public T getResponse() {
        return this.response;
    }
}
