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
    }

    public void onBtnClick(View view) {

        EditText editPassword = findViewById(R.id.textPassword);
        EditText textEmail = findViewById(R.id.textEmail);


        Button button = findViewById(R.id.connexionButton);
        Button buttoninscri = findViewById(R.id.inscriptionButton);

        buttoninscri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();

            }
        });

        MyDatabase db = MyDatabase.getInstance(this.getApplicationContext());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editPassword.getText().length() > 5) {
                    System.out.println(textEmail.getText().toString());
                    if (db.userDao().getOneUser(textEmail.getText().toString(), editPassword.getText().toString()) != null) {
                        Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                        openActivity2();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please check you login or password", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    editPassword.setError("Password too short");
                }


            }
        });

    }

    public void openActivity2() {
        EditText editEmail = findViewById(R.id.textEmail);
        Bundle bundle = new Bundle();
        Intent i = new Intent(this, MainActivity2.class);
        bundle.putString("emailvalue", editEmail.getText().toString());
        i.putExtras(bundle);
        startActivity(i);
    }

    public void openActivity3() {
        Intent i = new Intent(this, InscriptionActivity.class);
        startActivity(i);
    }


}