package tun.dev.comeon.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import tun.dev.comeon.R;

public class MainActivity2 extends AppCompatActivity{
    MediaPlayer mysong;
     CardView geo;
     CardView cult;
     CardView hist;
    String Category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        geo = findViewById(R.id.geographie);
        cult = findViewById(R.id.culture);
        hist = findViewById(R.id.histoire);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mysong = MediaPlayer.create(MainActivity2.this, R.raw.destroy);
        mysong.start();

        Thread thread = new Thread() {
            public void run() {
                try {
                    mysong.setLooping(true);
                    sleep(5000);
                } catch (Exception c) {

                }}
        };
        thread.start();
    }




    public void geographie(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                //System.out.println("------------------this is geographie card ------------------" );
                // item clicked
                Intent geoIntent = new Intent(getApplicationContext(),LevelsClass.class);
                geoIntent.putExtra("Category","geographie");
                startActivity(geoIntent);
            }
        });
    }

    public void culture(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                // item clicked
                Intent geoIntent = new Intent(getApplicationContext(),LevelsClass.class);
                geoIntent.putExtra("Category","culture");
                startActivity(geoIntent);
            }
        });



    }
    public void histoire(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                // item clicked
                Intent geoIntent = new Intent(getApplicationContext(),LevelsClass.class);
                geoIntent.putExtra("Category","histoire");
                startActivity(geoIntent);
            }
        });
    }










}