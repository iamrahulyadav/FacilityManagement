package com.shoaibnwar.facilitymanagement.ChatSystem.ComplexChat.model;

/**
 * Created by gold on 8/29/2018.
 */

public class Group extends Room{
    public String id;
    public ListFriend listFriend;

    public Group(){
        listFriend = new ListFriend();
    }
}