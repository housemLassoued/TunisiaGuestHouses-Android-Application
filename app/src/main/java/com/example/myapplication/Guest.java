package com.example.myapplication;

public class Guest {
    private int id;
    private String name;
    private String region;
    private String city;
    private String price;
    private String image;

    public Guest(int id, String name, String region, String city, String price, String image) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.city = city;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}

