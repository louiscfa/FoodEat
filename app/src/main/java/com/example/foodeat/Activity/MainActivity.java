package com.example.foodeat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodeat.Adaptor.CategoryAdaptor;
import com.example.foodeat.Adaptor.PopularAdaptor;
import com.example.foodeat.DataBase.Repository.CategoryRepository;
import com.example.foodeat.DataBase.Repository.FoodRepository;
import com.example.foodeat.DataBase.Repository.UserRepository;
import com.example.foodeat.Domain.User;
import com.example.foodeat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    private TextView textViewUser;
    private ImageView imageViewProfil;
    private Bundle extras;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (User) getIntent().getSerializableExtra("user");
        extras = getIntent().getExtras();

        textViewUser = findViewById(R.id.textView4);
        textViewUser.setText("Bienvenue " + user.getUserName() + " !");
        imageViewProfil = findViewById(R.id.imageView4);
        int drawableResourceId=imageViewProfil.getContext().getResources().getIdentifier(user.getPic(),
                "drawable", imageViewProfil.getContext().getPackageName());
        imageViewProfil.setImageResource(drawableResourceId);
        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cardBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartListActivity.class));
            }
        });
        LinearLayout buttonProfil = findViewById(R.id.profileBtn);
        buttonProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProfilActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
    }

    public void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList= findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        adapter = new CategoryAdaptor(CategoryRepository.getInstance(this).getAll());
        recyclerViewCategoryList.setAdapter(adapter);
    }
    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList=findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        adapter2=new PopularAdaptor(FoodRepository.getInstance(this).getAll());
        recyclerViewPopularList.setAdapter(adapter2);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MainActivity.this, ConnexionActivity.class));
        finish();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        user = UserRepository.getInstance(this).getById(user.getId());
    }
}