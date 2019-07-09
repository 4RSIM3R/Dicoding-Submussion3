package com.studio.suku.submission3;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;
import com.studio.suku.submission3.db.Favorite;
import com.studio.suku.submission3.db.FavoriteAdapter;

public class DetailFilm extends AppCompatActivity implements View.OnClickListener{

    public static final String EXTRA_DATA = "extra_data";
    TextView title, desc;
    ImageView img, bg;
    Button btn;
    String name, Desc, Img;
    FavoriteAdapter favoriteAdapter;
    private SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);


        title = findViewById(R.id.judul);
        desc = findViewById(R.id.desc);
        img = findViewById(R.id.poster);
        bg = findViewById(R.id.imageView);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(this);


        ItemFilm itemFilm = getIntent().getParcelableExtra(EXTRA_DATA);
        name = itemFilm.getName();
        Desc = itemFilm.getDesc();
        Img = itemFilm.getPath_img();
        title.setText(name);
        desc.setText(Desc);

        Picasso.get().load(Img).into(img);
        Picasso.get().load("https://pro2-bar-s3-cdn-cf5.myportfolio.com/b23f9db4e2c38f9f16365bcd65fd2b3a/66fe9eea-2292-4c41-8061-70d5c500aa0c_rw_1920.png?h=0e924c63656a2b332eb2e48c78b94a69").into(bg);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button){
            //Add To Sqlite Here


            Favorite favorite = new Favorite();
            favorite.setTitle("Suku");
            favorite.setImage("https://m.media-amazon.com/images/M/MV5BNGNiNWQ5M2MtNGI0OC00MDA2LWI5NzEtMmZiYjVjMDEyOWYzXkEyXkFqcGdeQXVyMjM4NTM5NDY@._V1_.jpg");
            favorite.setDescription("Film Nya Suku");
            favorite.setType("Film");
            favoriteAdapter.addItem(favorite);
            Toast.makeText(this, "Your Favorite Film", Toast.LENGTH_SHORT).show();


        }
    }
}
