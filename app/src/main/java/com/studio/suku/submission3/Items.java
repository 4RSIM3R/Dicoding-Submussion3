package com.studio.suku.submission3;

import android.util.Log;

import org.json.JSONObject;

public class Items {

    private String name;
    private String path_img;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath_img() {
        return path_img;
    }

    public void setPath_img(String path_img) {
        this.path_img = path_img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    //This Is A Constructor To Parse The JSONObject
    Items(JSONObject object){
        try {
            String name = object.getString("budget");
            this.name = name;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
