package com.studio.suku.submission3.db;


import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_FAVORITE = "favorite";

    static final class FavoriteColumns implements BaseColumns{

        //There Is A lot of row
        static String Title = "title";
        static String Description = "description";
        static String Type = "type";
        static String Image = "image";


    }
}
