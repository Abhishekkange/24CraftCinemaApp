package com.abhishekkange.theatreapp.models;

public class followerModel {

    String profileImage;
    String userName;

    public followerModel() {
    }

    public followerModel(String profileImage, String userName) {
        this.profileImage = profileImage;
        this.userName = userName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
