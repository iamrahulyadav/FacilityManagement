package com.shoaibnwar.facilitymanagement.ChatSystem.ComplexChat.model;

/**
 * Created by gold on 8/29/2018.
 */

public class Configuration {
    private String label;
    private String  value;
    private int icon;

    public Configuration(String label, String value, int icon){
        this.label = label;
        this.value = value;
        this.icon = icon;
    }

    public String getLabel(){
        return this.label;
    }

    public String getValue(){
        return this.value;
    }

    public int getIcon(){
        return this.icon;
    }
}

