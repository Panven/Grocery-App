package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapters.NavCategoryDetailedAdapter;
import com.example.myapplication.models.NavCategoryDetailedModel;
import com.example.myapplication.models.PopularModel;
import com.example.myapplication.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NavCategoryDetailedModel> navCategoryDetailedModelList;
    NavCategoryDetailedAdapter navCategoryDetailedAdapter;
    ProgressBar progressBar;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.nav_cat_det_rec);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        navCategoryDetailedModelList = new ArrayList<>();
        navCategoryDetailedAdapter = new NavCategoryDetailedAdapter(this, navCategoryDetailedModelList);
        recyclerView.setAdapter(navCategoryDetailedAdapter);

        String type = getIntent().getStringExtra("type");

        //GETTING DRINKS
        if(type != null && type.equalsIgnoreCase("drink")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","drink").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        navCategoryDetailedModelList.add(navCategoryDetailedModel);
                        navCategoryDetailedAdapter.notifyDataSetChanged();
                    }
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            });
        }
        //GETTING BREAKFAST
        if(type != null && type.equalsIgnoreCase("breakfast")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","breakfast").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        navCategoryDetailedModelList.add(navCategoryDetailedModel);
                        navCategoryDetailedAdapter.notifyDataSetChanged();
                    }
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            });
        }
        //GETTING FRUIT
        if(type != null && type.equalsIgnoreCase("fruit")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","fruit").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        navCategoryDetailedModelList.add(navCategoryDetailedModel);
                        navCategoryDetailedAdapter.notifyDataSetChanged();
                    }
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            });
        }
        //GETTING BAKERY
        if(type != null && type.equalsIgnoreCase("bakery")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","bakery").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        navCategoryDetailedModelList.add(navCategoryDetailedModel);
                        navCategoryDetailedAdapter.notifyDataSetChanged();
                    }
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            });
        }
        //GETTING MEAT
        if(type != null && type.equalsIgnoreCase("meat")){
            db.collection("NavCategoryDetailed").whereEqualTo("type","meat").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        navCategoryDetailedModelList.add(navCategoryDetailedModel);
                        navCategoryDetailedAdapter.notifyDataSetChanged();
                    }
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            });
        }



    }
}