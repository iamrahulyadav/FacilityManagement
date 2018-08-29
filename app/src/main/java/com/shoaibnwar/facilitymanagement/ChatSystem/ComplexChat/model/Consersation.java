package com.shoaibnwar.facilitymanagement.ChatSystem.ComplexChat.model;

import java.util.ArrayList;

/**
 * Created by gold on 8/29/2018.
 */

public class Consersation {
    private ArrayList<Message> listMessageData;
    public Consersation(){
        listMessageData = new ArrayList<>();
    }

    public ArrayList<Message> getListMessageData() {
        return listMessageData;
    }
}
