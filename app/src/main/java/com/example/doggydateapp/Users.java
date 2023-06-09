package com.example.doggydateapp;

public class Users {

    private String _id;
    private String name;
    private String password;
    private String email;
    private String age;
    private String gender;
    private String sexuality;
    private String location;
    private String bio;
    private String school;
    private String job;
    private String interests;
    private byte[] profilePicture;
    private Integer rotate;

    public Users(String _id, String name, String password, String email, String age, String gender, String sexuality, String location, String bio, String school, String job, String interests, byte[] profilePicture, Integer rotate) {
        this._id = _id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.sexuality = sexuality;
        this.location = location;
        this.bio = bio;
        this.school = school;
        this.job = job;
        this.interests = interests;
        this.profilePicture = profilePicture;
        this.rotate = rotate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSexuality() {
        return sexuality;
    }

    public void setSexuality(String sexuality) {
        this.sexuality = sexuality;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() { return bio; }

    public void setBio(String bio) { this.bio = bio; }

    public String getSchool() { return school; }

    public void setSchool(String school) {this.school = school;}

    public String getJob() { return job; }

    public void setJob(String job) { this.job = job; }

    public String getInterests() {return interests;}

    public void setInterests(String interests) { this.interests = interests; }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }
}