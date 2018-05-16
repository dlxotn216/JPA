package me.strongwhisky.app._day19;

import javax.persistence.*;

/**
 * Created by taesu on 2018-05-16.
 */

public class CommentListener {

    @PrePersist
    public void onPrePersist(Comment comment){
        System.out.println("CHECK Comment onPrePersist");
    }

    @PostPersist
    public void onPostPersist(Comment comment){
        System.out.println("CHECK Comment onPostPersist");
    }

    @PreUpdate
    public void onPreUpdate(Comment comment){
        System.out.println("CHECK Comment onPreUpdate");
    }

    @PostUpdate
    public void onPostUpdate(Comment comment){
        System.out.println("CHECK Comment onPostUpdate");
    }

    @PostLoad
    public void onPostLoad(Comment comment){
        System.out.println("CHECK Comment onPostLoad ");
    }

    @PreRemove
    public void onPreRemove(Comment comment){
        System.out.println("CHECK Comment onPreRemove");
    }

    @PostRemove
    public void onPostRemove(Comment comment){
        System.out.println("CHECK Comment onPostRemove");
    }
}
