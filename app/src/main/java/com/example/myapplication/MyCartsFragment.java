package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.activities.PlacedOrderActivity;
import com.example.myapplication.adapters.MyCartAdapter;
import com.example.myapplication.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MyCartsFragment extends Fragment {

    RecyclerView recyclerView;
    MyCartAdapter cartAdapter;
    List<MyCartModel> cartModelList;
    TextView overTotalAmount;
    ProgressBar progressBar;
    Button buyNow;
    int totalBill;

    FirebaseFirestore db;
    FirebaseAuth auth;

    public MyCartsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_carts,container,false);

            progressBar = root.findViewById(R.id.progressbar);
            progressBar.setVisibility(View.VISIBLE);

            db = FirebaseFirestore.getInstance();
            auth = FirebaseAuth.getInstance();
            recyclerView = root.findViewById(R.id.recyclerview);
            recyclerView.setVisibility(View.GONE);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            overTotalAmount = root.findViewById(R.id.total_price_cart);
            buyNow = root.findViewById(R.id.buy_now);

            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver, new IntentFilter("MyTotalAmount"));

            cartModelList = new ArrayList<>();
            cartAdapter = new MyCartAdapter(getActivity(),cartModelList);
            recyclerView.setAdapter(cartAdapter);

            db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                    .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                            String documentId = documentSnapshot.getId();
                            MyCartModel cartModel = documentSnapshot.toObject(MyCartModel.class);
                            cartModel.setDocumentId(documentId);
                            cartModelList.add(cartModel);
                            cartAdapter.notifyDataSetChanged();
                        }
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });

            buyNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), PlacedOrderActivity.class);
                    intent.putExtra("itemList", (Serializable) cartModelList);
                    startActivity(intent);
                }
            });

        return root;
    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill = intent.getIntExtra("totalAmount",0);
            overTotalAmount.setText("Total Bill : " + totalBill + "$");
        }
    };
}