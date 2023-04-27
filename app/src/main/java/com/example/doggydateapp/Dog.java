package com.example.doggydateapp;

public class Dog {

    private String name, age, breed, size, temperament, bio;
    private byte[] picture;
    private Integer rotate;


    public Dog(String name, String age, String breed, String size, String temperament, String bio, byte[] picture, Integer rotate) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.size = size;
        this.temperament = temperament;
        this.bio = bio;
        this.picture = picture;
        this.rotate = rotate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Integer getRotate() {
        return rotate;
    }

    public void setRotate(Integer rotate) {
        this.rotate = rotate;
    }
}
