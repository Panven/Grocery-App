package com.example.myapplication.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapters.ViewAllAdapter;
import com.example.myapplication.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllViewActivity extends AppCompatActivity {

    List<ViewAllModel> viewAllModelList;
    ViewAllAdapter viewAllAdapter;
    RecyclerView recyclerView;
    FirebaseFirestore db;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_view);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.view_all_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(this,viewAllModelList);
        recyclerView.setAdapter(viewAllAdapter);

        //Getting Fruits
        if(type != null && type.equalsIgnoreCase("fruit")){
            db.collection("AllProducts").whereEqualTo("type","fruit").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Getting vegetable
        if(type != null && type.equalsIgnoreCase("vegetable")){
            db.collection("AllProducts").whereEqualTo("type","vegetable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Getting fish
        if(type != null && type.equalsIgnoreCase("fish")){
            db.collection("AllProducts").whereEqualTo("type","fish").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }
                }
            });
            Toast.makeText(this,"getting fish",Toast.LENGTH_SHORT).show();
        }

        //Getting milk
        if(type != null && type.equalsIgnoreCase("milk")){
            db.collection("AllProducts").whereEqualTo("type","milk").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Getting eggs
        if(type != null && type.equalsIgnoreCase("eggs")){
            db.collection("AllProducts").whereEqualTo("type","eggs").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }
}