package com.example.jh.ajoudb22.Item;

public class RestaurantListitem {

    private String name;
    private String R_number;
    private String Image;
    private String Rating;
    private String review_num;

    public RestaurantListitem(String name, String r_number, String image) {
        this.name = name;
        R_number = r_number;
        Image = image;
    }

    public RestaurantListitem(String name, String r_number, String image, String rating, String review_num) {
        this.name = name;
        R_number = r_number;
        Image = image;
        Rating = rating;
        this.review_num = review_num;
    }

    public String getName() {
        return name;
    }
    public String getR_number() {
        return R_number;
    }

    public String getImage() {
        return Image;
    }

    public String getRating() {
        return Rating;
    }

    public String getReview_num() {
        return review_num;
    }

}
