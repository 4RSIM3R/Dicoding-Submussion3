package com.studio.suku.submission3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailTv extends AppCompatActivity {
    public static final String EXTRA_DATA_TV = "extra_data";
    TextView title, desc;
    ImageView img, bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);
        title = findViewById(R.id.judulTv);
        desc = findViewById(R.id.descTv);
        img = findViewById(R.id.posterTv);
        bg = findViewById(R.id.imageViewTv);
        Items items = getIntent().getParcelableExtra(EXTRA_DATA_TV);

        title.setText(items.getName());
        desc.setText(items.getDesc());

        Picasso.get().load(items.getPath_img()).into(img);
        Picasso.get().load("https://pro2-bar-s3-cdn-cf5.myportfolio.com/b23f9db4e2c38f9f16365bcd65fd2b3a/66fe9eea-2292-4c41-8061-70d5c500aa0c_rw_1920.png?h=0e924c63656a2b332eb2e48c78b94a69").into(bg);
    }
}
