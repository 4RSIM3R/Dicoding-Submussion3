package com.studio.suku.submission3;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

public class Items implements Parcelable {

    private String name;
    private String path_img;
    private String desc;

    public Items() {

    }

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
            String name = object.getString("original_name");
            String img = object.getString("poster_path");
            String path_img = "https://image.tmdb.org/t/p/w500/"+img;
            String desc = object.getString("original_name");

            this.name = name;
            this.path_img = path_img;
            this.desc = desc;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.path_img);
        dest.writeString(this.desc);
    }

    protected Items(Parcel in) {
        this.name = in.readString();
        this.path_img = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<Items> CREATOR = new Parcelable.Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel source) {
            return new Items(source);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };
}
