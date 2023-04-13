package com.example.foodeat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodeat.DataBase.Repository.UserRepository;
import com.example.foodeat.Domain.User;
import com.example.foodeat.R;

import java.util.ArrayList;

public class ConnexionActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUserName, editTextPassword;

    private Button buttonConnexion;

    private ArrayList<User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        /*users = new ArrayList<User>();
        users.add(new User("Charles","1234"));
        users.add(new User("test","test"));*/

        UserRepository.getInstance(this).add(new User("Charles","1234","","","","",""));
        UserRepository.getInstance(this).add(new User("test","test","","","","",""));

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonConnexion = findViewById(R.id.buttonConnexion);
        buttonConnexion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(!editTextUserName.getText().toString().equals("") && !editTextPassword.getText().toString().equals("")){
            boolean connexion = false;
            for(User user : UserRepository.getInstance(ConnexionActivity.this).getAll()) {
                if (editTextUserName.getText().toString().equals(user.getUserName()) && editTextPassword.getText().toString().equals(user.getPassword())) {
                    startActivity(new Intent(ConnexionActivity.this, MainActivity.class));
                    connexion = true;
                }
            }
            if(!connexion)
                Toast.makeText(ConnexionActivity.this, "Nom d'utilisateur ou mot de passe invalide", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(ConnexionActivity.this, "Veuillez saisir un nom d'utilisateur et un mot de passe", Toast.LENGTH_SHORT).show();
        }
    }
}
