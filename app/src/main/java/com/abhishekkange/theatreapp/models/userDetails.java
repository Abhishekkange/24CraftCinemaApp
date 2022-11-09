package com.abhishekkange.theatreapp.models;

public class userDetails {

    String userName;
    String Profession;
    String profileImage;

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }

    public userDetails() {

    }

    public userDetails(String userName) {
        this.userName = userName;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
