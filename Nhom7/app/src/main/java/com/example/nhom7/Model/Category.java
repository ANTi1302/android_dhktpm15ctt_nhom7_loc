package com.example.nhom7.Model;

public class Category {
    private String Name, Image;

    public Category(String name, String image) {
        Name = name;
        Image = image;
    }

    public Category() {
    }

    public String getName() {
        return Name;
    }

    public String getImage() {
        return Image;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setImage(String image) {
        Image = image;
    }
}
