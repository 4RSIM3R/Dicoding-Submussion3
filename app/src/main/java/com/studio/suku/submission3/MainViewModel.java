package com.studio.suku.submission3;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.ClipData;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {

    private String api_key = "24f2356bed948a69b6ce4946afbf4f67";
    //Mutable Live Data
    private MutableLiveData<ArrayList<Items>> listItems = new MutableLiveData<>();



    void setListFilms(){
        String[] url = new String[]{
                "https://api.themoviedb.org/3/movie/76?api_key="+api_key +"&language=id",
                "https://api.themoviedb.org/3/movie/6?api_key="+api_key+"&language=id",
                "https://api.themoviedb.org/3/movie/8?api_key="+api_key+"&language=id",
                "https://api.themoviedb.org/3/movie/78?api_key="+api_key+"&language=id",
                "https://api.themoviedb.org/3/movie/78?api_key="+api_key+"&language=id"
        };
        for (int i = 0; i < url.length; i++){
            //Log.d("List Of Url", url[i]);
            //Do Request Here
            AsyncHttpClient client = new AsyncHttpClient();
            final ArrayList<Items> listData = new ArrayList<>();

            client.get(url[i], new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String result = new String(responseBody);
                        JSONObject responseObject = new JSONObject(result);
                        Log.d("From Api", result);
                        Items items = new Items(responseObject);
                        listData.add(items);
                        listItems.postValue(listData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        }
    }
    LiveData<ArrayList<Items>> getData(){
        return listItems;
    }
}
