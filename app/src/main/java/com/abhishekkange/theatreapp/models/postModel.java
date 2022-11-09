package com.abhishekkange.theatreapp.models;

public class postModel {

    String postImage,like,dislike,comment,postId;

    public postModel() {
    }

    public postModel(String postImage, String like, String dislike, String comment) {
        this.postImage = postImage;
        this.like = like;
        this.dislike = dislike;
        this.comment = comment;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
