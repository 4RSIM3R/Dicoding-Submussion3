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
    private MutableLiveData<ArrayList<Items>> myItems = new MutableLiveData<>();


    void setListTv(){
        //Request Here
        String urlFilm = "http://api.themoviedb.org/3/movie/upcoming?api_key="+api_key+"&language=id";
        String url = "https://api.themoviedb.org/3/discover/tv?api_key="+api_key+"&language=id";
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Items> listItems = new ArrayList<>();

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray listTv = responseObject.getJSONArray("results");

                    for (int i = 0; i < listTv.length(); i++){
                        JSONObject Tv = listTv.getJSONObject(i);
                        Items items = new Items(Tv);
                        listItems.add(items);
                    }

                    myItems.setValue(listItems);

                }catch (Exception e){
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage() + statusCode);
            }
        });


    }
    LiveData<ArrayList<Items>> getData(){
        return myItems;
    }
}
