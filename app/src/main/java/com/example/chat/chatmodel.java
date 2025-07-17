package com.example.chat;

public class chatmodel {
    int image;
    String name, number;

    public chatmodel(int image, String name, String number) {
        this.image = image;
        this.name = name;
        this.number = number;
    }

    public int getImage() { return image; }
    public String getName() { return name; }
    public String getNumber() { return number; }
}