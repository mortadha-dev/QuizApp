package tun.dev.comeon.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tun.dev.comeon.R;
import tun.dev.comeon.database.MyDatabase;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editPassword = findViewById(R.id.textPassword);
        EditText textEmail = findViewById(R.id.textEmail);
        Button button = findViewById(R.id.connexionButton);
        Button buttoninscri = findViewById(R.id.inscriptionButton);
        MyDatabase db = MyDatabase.getInstance(this.getApplicationContext());

        buttoninscri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, InscriptionActivity.class);
                startActivity(i);

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (db.userDao().getOneUser(textEmail.getText().toString(), editPassword.getText().toString()) != null) {
                        Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Please check you login or password", Toast.LENGTH_SHORT).show();
                    }
                }
        });
    }


}