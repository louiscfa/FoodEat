package com.example.foodeat.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodeat.DataBase.Repository.UserRepository;
import com.example.foodeat.Domain.User;
import com.example.foodeat.R;

public class ProfilActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUserName, editTextPassword, editTextNom, editTextPrenom, editTextEmail, editTextAdresse, editTextPic;

    private Button buttonValidation;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        user = (User) getIntent().getSerializableExtra("user");

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextUserName.setText(user.getUserName());
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPassword.setText(user.getPassword());
        editTextNom = findViewById(R.id.editTextNom);
        editTextNom.setText(user.getNom());
        editTextPrenom = findViewById(R.id.editTextPrenom);
        editTextPrenom.setText(user.getPrenom());
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextEmail.setText(user.getEmail());
        editTextAdresse = findViewById(R.id.editTextAdresse);
        editTextAdresse.setText(user.getAdresse());
        editTextPic = findViewById(R.id.editTextPic);
        editTextPic.setText(user.getPic());

        buttonValidation = findViewById(R.id.buttonValidation);
        buttonValidation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        user.setUserName(editTextUserName.getText().toString());
        user.setPassword(editTextPassword.getText().toString());
        user.setNom(editTextNom.getText().toString());
        user.setPrenom(editTextPrenom.getText().toString());
        user.setEmail(editTextEmail.getText().toString());
        user.setAdresse(editTextAdresse.getText().toString());
        user.setPic(editTextPic.getText().toString());
        UserRepository.getInstance(this).update(user);
        Toast.makeText(this, "Modification prise en compte!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
