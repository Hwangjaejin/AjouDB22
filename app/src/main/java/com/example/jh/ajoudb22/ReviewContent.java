package com.example.jh.ajoudb22;

class ReviewContent {

    private String name;
    private float rating;
    private String date;
    private String review;

    public ReviewContent (String name, float rating, String date, String review){
        this.name = name;
        this.rating = rating;
        this.date = date;
        this.review = review;

    }

    public String getName() {
        return this.name;
    }

    public String getDate() {
        return this.date;
    }

    public float getRating() {
        return this.rating;
    }

    public String getReview() {
        return this.review;
    }
}
