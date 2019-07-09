package com.studio.suku.submission3;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FilmFragment extends Fragment implements FilmAdapter.OnItemClickListener {

    MainViewModelFilm mainViewModelFilm = new MainViewModelFilm();
    private ProgressBar progressBar;
    private RecyclerView list;
    View v;
    private FilmAdapter adapter;
    private ArrayList<ItemFilm> itemFilmArrayList = new ArrayList<>();
    String nama;


    public FilmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_film, container, false);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModelFilm = ViewModelProviders.of(this).get(MainViewModelFilm.class);
        mainViewModelFilm.setListFilms();
        mainViewModelFilm.getDataFilm().observe(this, getDataFilm);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = v.findViewById(R.id.list_film);
        list.setHasFixedSize(true);
        list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        progressBar = v.findViewById(R.id.progressBarFilm);

        adapter = new FilmAdapter();
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }

    private Observer<ArrayList<ItemFilm>> getDataFilm = new Observer<ArrayList<ItemFilm>>() {
        @Override
        public void onChanged(@Nullable ArrayList<ItemFilm> listItems) {
            if (listItems != null) {
                adapter.setDataFilm(listItems);
                itemFilmArrayList.addAll(listItems);
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
    public void onItemClick(int position) {


        Intent pindah = new Intent(getActivity(), DetailFilm.class);

        String name = itemFilmArrayList.get(position).getName();
        String photo = itemFilmArrayList.get(position).getPath_img();
        String desc = itemFilmArrayList.get(position).getPath_img();

        ItemFilm film = new ItemFilm();
        film.setDesc(desc);
        film.setName(name);
        film.setPath_img(photo);
        pindah.putExtra(DetailFilm.EXTRA_DATA, film);
        startActivity(pindah);
    }
}
