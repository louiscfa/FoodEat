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

public class InscriptionActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUserName, editTextPassword, editTextNom, editTextPrenom, editTextEmail, editTextAdresse, editTextPic;

    private Button buttonInscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextNom = findViewById(R.id.editTextNom);
        editTextPrenom = findViewById(R.id.editTextPrenom);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAdresse = findViewById(R.id.editTextAdresse);
        editTextPic = findViewById(R.id.editTextPic);

        buttonInscription = findViewById(R.id.buttonInscription);
        buttonInscription.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(!editTextUserName.getText().toString().equals("") && !editTextPassword.getText().toString().equals("")
                && !editTextNom.getText().toString().equals("") && !editTextPrenom.getText().toString().equals("")
                && !editTextEmail.getText().toString().equals("") && !editTextAdresse.getText().toString().equals("") &&
                !editTextPic.getText().toString().equals("")) {
            UserRepository.getInstance(this).add(new User(editTextUserName.getText().toString(),
                    editTextPassword.getText().toString(),editTextNom.getText().toString(),
                    editTextPrenom.getText().toString(),editTextEmail.getText().toString(),
                    editTextAdresse.getText().toString(),editTextPic.getText().toString()));
            Toast.makeText(this, "Inscription réussi!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(InscriptionActivity.this, ConnexionActivity.class));
        }
        else{
            Toast.makeText(this, "Veuillez saisir toutes les informations!", Toast.LENGTH_SHORT).show();
        }
    }
}
