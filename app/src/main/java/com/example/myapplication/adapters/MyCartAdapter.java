package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCartModel> myCartModelList;
    int totalPrice = 0;
    FirebaseFirestore db;
    FirebaseAuth auth;

    public MyCartAdapter(Context context, List<MyCartModel> myCartModelList) {
        this.context = context;
        this.myCartModelList = myCartModelList;
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(myCartModelList.get(position).getProductName());
        holder.price.setText(myCartModelList.get(position).getProductPrice());
        holder.date.setText(myCartModelList.get(position).getCurrentDate());
        holder.time.setText(myCartModelList.get(position).getCurrentTime());
        holder.quantity.setText(String.valueOf(myCartModelList.get(position).getTotalQuantity()));
        holder.totalPrice.setText(String.valueOf(myCartModelList.get(position).getTotalPrice()));


        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart").document(myCartModelList.get(holder.getAdapterPosition()).getDocumentId()).delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()){
                                    myCartModelList.remove(myCartModelList.get(holder.getAdapterPosition()));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
                                } else{
                                    Toast.makeText(context,"Error: " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return myCartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, date, time,quantity,totalPrice;
        ImageView deleteItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            date = itemView.findViewById(R.id.product_date);
            time = itemView.findViewById(R.id.product_time);
            quantity = itemView.findViewById(R.id.total_quantity);
            totalPrice = itemView.findViewById(R.id.total_price);
            deleteItem = itemView.findViewById(R.id.delete);
        }
    }
}
