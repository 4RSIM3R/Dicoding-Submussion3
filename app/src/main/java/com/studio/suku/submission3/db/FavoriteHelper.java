package com.studio.suku.submission3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;

public class FavoriteHelper {

    private static final String DB_TABLE = DatabaseContract.TABLE_FAVORITE;
    private static DatabaseHelper dbHelper;
    private static FavoriteHelper INSTANCE;
    private static SQLiteDatabase database;

    //Query Here

    private FavoriteHelper(Context context){
        //Constructor
        dbHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = new FavoriteHelper(context);
        }
       return INSTANCE;
    }

    public void openDb() throws SQLException {
        //Connect to database
        database = dbHelper.getWritableDatabase();
    }

    public void closeDb(){

        //To Close Connection

        dbHelper.close();

        if (database.isOpen()){
            database.close();
        }
    }

    public ArrayList<Favorite> getAll(){

        ArrayList<Favorite> favorites = new ArrayList<>();

        Cursor cursor = database.query(
                DB_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null
        );
        cursor.moveToFirst();
        Favorite favorite;

        if (cursor.getCount() > 0){
            do {
                favorite = new Favorite();
                favorite.setId(cursor.getColumnIndexOrThrow(_ID));
                favorite.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.Title)));
                favorite.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.Description)));
                favorite.setImage(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.Description)));
                favorite.setType(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.Type)));
                favorites.add(favorite);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return favorites;
    }

    public long addFavorite(Favorite favorite){
        ContentValues args = new ContentValues();
        args.put(DatabaseContract.FavoriteColumns.Title, favorite.getTitle());
        args.put(DatabaseContract.FavoriteColumns.Description, favorite.getDescription());
        args.put(DatabaseContract.FavoriteColumns.Image, favorite.getImage());
        args.put(DatabaseContract.FavoriteColumns.Type, favorite.getType());

        return database.insert(DatabaseContract.TABLE_FAVORITE, null, args);
    }

    public int deleteFavorite(int id){
        return database.delete(DatabaseContract.TABLE_FAVORITE,
                DatabaseContract.FavoriteColumns._ID + " = '" + id + "'",
                null);
    }

}
