package com.example.internshipproject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {
    @SerializedName("results")
    private List<Review> results;

    public List<Review> getResults() {
        return results;
    }

    public void setResults(List<Review> results) {
        this.results = results;
    }

    public static class Review {
        @SerializedName("content")
        private String content;

        @SerializedName("author")
        private String author;

        public void setAuthor(String author) {
            this.author = author;
        }
        public String getAuthor(){
            return author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
