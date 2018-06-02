package com.example.jh.ajoudb22;

public class RestaurantListitem {
    private String name;
    private String R_number;

    public String getName() {
        return name;
    }
    public String getR_number() {
        return R_number;
    }

    public RestaurantListitem(String name,String R_number) {

        this.name = name;
        this.R_number = R_number;
    }
}
