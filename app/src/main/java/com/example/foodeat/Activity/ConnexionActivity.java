package com.example.foodeat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodeat.DataBase.Repository.UserRepository;
import com.example.foodeat.Domain.User;
import com.example.foodeat.R;

import java.util.ArrayList;

public class ConnexionActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUserName, editTextPassword;

    private Button buttonConnexion;

    private TextView textViewInscriptionLien;

    private ArrayList<User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonConnexion = findViewById(R.id.buttonConnexion);
        buttonConnexion.setOnClickListener(this);

        textViewInscriptionLien = findViewById(R.id.textViewInscriptionLien);
        textViewInscriptionLien.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.equals(buttonConnexion)) {
            if (!editTextUserName.getText().toString().equals("") && !editTextPassword.getText().toString().equals("")) {
                boolean connexion = false;
                for (User user : UserRepository.getInstance(ConnexionActivity.this).getAll()) {
                    if (editTextUserName.getText().toString().equals(user.getUserName()) && editTextPassword.getText().toString().equals(user.getPassword())) {
                        Intent intent = new Intent(ConnexionActivity.this, MainActivity.class);
                        intent.putExtra("user",user);
                        startActivity(intent);
                        finish();
                        connexion = true;
                    }
                }
                if (!connexion)
                    Toast.makeText(ConnexionActivity.this, "Nom d'utilisateur ou mot de passe invalide", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ConnexionActivity.this, "Veuillez saisir un nom d'utilisateur et un mot de passe", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            startActivity(new Intent(ConnexionActivity.this, InscriptionActivity.class));
        }
    }
}
