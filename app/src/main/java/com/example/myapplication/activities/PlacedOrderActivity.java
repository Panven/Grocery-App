package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlacedOrderActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        List<MyCartModel> myCartModelList = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemList");

        if (myCartModelList != null && myCartModelList.size() > 0){
            for(MyCartModel model : myCartModelList){
                final HashMap<String,Object> cartMap = new HashMap<>();

                cartMap.put("productName", model.getProductName());
                cartMap.put("productPrice", model.getProductPrice());
                cartMap.put("currentDate", model.getCurrentDate());
                cartMap.put("currentTime", model.getCurrentTime());
                cartMap.put("totalQuantity", model.getTotalQuantity());
                cartMap.put("totalPrice", model.getTotalPrice());

                db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder"                                                                                                                                                                                                      ).add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(PlacedOrderActivity.this,"Your Order Has Been Placed",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}