package tun.dev.comeon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import tun.dev.comeon.R;

public class LevelsClass extends AppCompatActivity {
    String categoryValue = "";
    CardView cv1;
    CardView cv2;
    CardView cv3;
    int beginner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);
        cv1 = findViewById(R.id.debutant);
        cv2 = findViewById(R.id.intermediaire);
        cv3 = findViewById(R.id.avance);

    }

    public void debutant(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // item clicked
                beginner = 1;
                Intent i = new Intent(LevelsClass.this, MainActivity4.class);
                categoryValue = getIntent().getStringExtra("Category");
                //System.out.println("++++++++++++++++++this is the categoryvalue+++++++++++++++"+categoryValue);
                i.putExtra("Category", categoryValue);
                System.out.println("++++++++++++++++++this is the level in levels class+++++++++++++++" + beginner);
                i.putExtra("level", beginner);
                startActivity(i);
            }
        });


    }

    public void intermediaire(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // item clicked
                beginner = 2;
                Intent i = new Intent(LevelsClass.this, MainActivity4.class);
                categoryValue = getIntent().getStringExtra("Category");
                i.putExtra("Category", categoryValue);
                i.putExtra("level", beginner);
                startActivity(i);
            }
        });


    }

    public void avance(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // item clicked
                beginner = 3;
                Intent i = new Intent(LevelsClass.this, MainActivity4.class);
                categoryValue = getIntent().getStringExtra("Category");
                i.putExtra("Category", categoryValue);
                i.putExtra("level", beginner);
                startActivity(i);
            }
        });


    }
}
