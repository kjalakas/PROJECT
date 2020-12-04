package com.example.PROJECT.controller;

public class UuidRequest {
    private String uuid;
    private String wishlist;

    public String getUuid() {
        return uuid;
    }

    public UuidRequest setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getWishlist() {
        return wishlist;
    }

    public UuidRequest setWishlist(String wishlist) {
        this.wishlist = wishlist;
        return this;
    }
}
