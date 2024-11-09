package com.example.smd_assignment3;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {
    private Context context;
    private ArrayList<Product> scheduledProducts;
    private ScheduledAdapter adapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView lvScheduledOrdersList = view.findViewById(R.id.lvScheduledOrdersList);

        ProductDB productDB = new ProductDB(context);
        productDB.open();
        scheduledProducts = productDB.fetchProducts("scheduled");
        for (Product product : scheduledProducts) {
            Log.d("ProductInfo", "ID: " + product.getId() + ", Title: " + product.getTitle() +
                    ", Date: " + product.getDate() + ", Price: " + product.getPrice() +
                    ", Status: " + product.getStatus());
        }
        productDB.close();

        adapter = new ScheduledAdapter(context, R.layout.confirmed_item_design, scheduledProducts);
        lvScheduledOrdersList.setAdapter(adapter);

    }


    public void addProductToSchedule(Product product) {
            adapter.addSchedule(product);
    }

}