package tun.dev.comeon.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tun.dev.comeon.DAO.UserDao;
import tun.dev.comeon.R;
import tun.dev.comeon.ViewModel.UserViewModel;
import tun.dev.comeon.database.MyDatabase;
import tun.dev.comeon.entities.User;

public class InscriptionActivity extends AppCompatActivity {

    EditText nom;
    EditText Prenom;
    EditText Login;
    EditText Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);


    }


    public void onBtnClick(View view) {


        Button buttoninscri = findViewById(R.id.button);
        MyDatabase db = MyDatabase.getInstance(this.getApplicationContext());

        buttoninscri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nom = findViewById(R.id.Nom);
                Prenom = findViewById(R.id.Prenom);
                Login = findViewById(R.id.Login);
                Password = findViewById(R.id.Password);
                System.out.println(nom.getText().toString());
                User user = new User(nom.getText().toString(), Prenom.getText().toString(), Login.getText().toString(), Password.getText().toString());
                db.userDao().InsertUser(user);
                System.out.println(db.userDao().getAllUsers().toString());
                OpenLoginActivity();

            }
        });
    }

    public void OpenLoginActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}