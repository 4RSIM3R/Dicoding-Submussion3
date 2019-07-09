package com.studio.suku.submission3;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;



public class TvFragment extends Fragment implements ItemAdapter.OnRowClicked{
    private MainViewModel mainViewModel;
    private ProgressBar progressBar;
    private RecyclerView list;
    View v;
    private ItemAdapter adapter;
    private ArrayList<Items> itemTvArrayList = new ArrayList<>();

    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_tv, container, false);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Call The ViewModels
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.setListTv();
        mainViewModel.getData().observe(this, getData);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = v.findViewById(R.id.list_tv);
        list.setHasFixedSize(true);
        list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        progressBar = v.findViewById(R.id.progressBar);

        adapter = new ItemAdapter();
        adapter.setOnRowClicked(this);
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);

    }

    private Observer<ArrayList<Items>> getData = new Observer<ArrayList<Items>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Items> listItems) {
            if (listItems != null) {
                adapter.setData(listItems);
                itemTvArrayList.addAll(listItems);
                showLoading(false);
            }
            else {
                showLoading(true);
            }
        }
    };



    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public void OnClicked(int position) {
        Intent pindah = new Intent(getActivity(), DetailTv.class);

        String name = itemTvArrayList.get(position).getName();
        String photo = itemTvArrayList.get(position).getPath_img();
        String desc = itemTvArrayList.get(position).getPath_img();

        Items tv = new Items();
        tv.setDesc(desc);
        tv.setName(name);
        tv.setPath_img(photo);
        pindah.putExtra(DetailTv.EXTRA_DATA_TV, tv);
        startActivity(pindah);
    }
}
