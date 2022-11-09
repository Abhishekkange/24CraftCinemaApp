package com.abhishekkange.theatreapp.models;

public class profilePostModel {

    String postImage;

    public profilePostModel() {
    }

    public profilePostModel(String postImage) {
        this.postImage = postImage;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }
}