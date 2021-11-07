package tun.dev.comeon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import tun.dev.comeon.R;
import tun.dev.comeon.database.MyDatabase;

public class SplachActivity extends AppCompatActivity {
    private static int SPLACH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splach);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplachActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLACH_TIME_OUT);


    }
}
