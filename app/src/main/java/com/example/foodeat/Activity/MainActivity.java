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
import com.example.foodeat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    private TextView textViewUser;
    private ImageView imageViewProfil;
    private Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        extras = getIntent().getExtras();

        textViewUser = findViewById(R.id.textView4);
        textViewUser.setText("Bienvenue " + extras.getString("username") + " !");
        imageViewProfil = findViewById(R.id.imageView4);
        int drawableResourceId=imageViewProfil.getContext().getResources().getIdentifier(extras.get("pic").toString(),
                "drawable", imageViewProfil.getContext().getPackageName());
        imageViewProfil.setImageResource(drawableResourceId);
        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cardBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartListActivity.class));
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
}