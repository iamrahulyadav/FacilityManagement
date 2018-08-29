package com.shoaibnwar.facilitymanagement.ChatSystem.ComplexChat.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gold on 8/29/2018.
 */

public class Room {
    public ArrayList<String> member;
    public Map<String, String> groupInfo;

    public Room(){
        member = new ArrayList<>();
        groupInfo = new HashMap<String, String>();
    }
}
