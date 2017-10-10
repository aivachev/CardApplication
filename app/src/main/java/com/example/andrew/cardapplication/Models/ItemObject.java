package com.example.andrew.cardapplication.Models;

/**
 * Created by Andrew on 09.10.2017.
 */

public class ItemObject {
    private String one;
    private String two;
    private String three;

    public ItemObject(String one, String two, String three) {
        this.one = one;
        this.two = two;
        this.three = three;
    }

    public String getOne() {
        return one;
    }

    public String getTwo() {
        return two;
    }

    public String getThree() {
        return three;
    }
}