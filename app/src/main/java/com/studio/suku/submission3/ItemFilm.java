package com.studio.suku.submission3;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class ItemFilm implements Parcelable {
    private String name;
    private String path_img;
    private String desc;

    public ItemFilm() {

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
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    ItemFilm(JSONObject object){
        try {
            String name = object.getString("title");
            String img = object.getString("poster_path");
            String path_img = "https://image.tmdb.org/t/p/w500/"+img;
            String desc = object.getString("title");

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

    protected ItemFilm(Parcel in) {
        this.name = in.readString();
        this.path_img = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<ItemFilm> CREATOR = new Parcelable.Creator<ItemFilm>() {
        @Override
        public ItemFilm createFromParcel(Parcel source) {
            return new ItemFilm(source);
        }

        @Override
        public ItemFilm[] newArray(int size) {
            return new ItemFilm[size];
        }
    };
}
