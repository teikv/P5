package com.example.p5;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;

import com.example.p5.databinding.ActivityDetailsBinding;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    private ActivityDetailsBinding binding;
    private DetailsViewModel model;
    private String string;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        model = new ViewModelProvider(this).get(DetailsViewModel.class);
        model.getString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                string = s;
                binding.evDetails.setText(string);
            }
        });

        Intent reciveIntent = getIntent();
        if (reciveIntent != null) {
            string = reciveIntent.getStringExtra("string");
            position = reciveIntent.getIntExtra("position",0);
            binding.evDetails.setText(string);
            model.setString(string);

        }
        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main = MainActivity.getInstance();
                main.model.editListItems(position, binding.evDetails.getText().toString());
                main.model.getArrayList().observe(main, new Observer<ArrayList<String>>() {
                    @Override
                    public void onChanged(ArrayList<String> arrayList) {
                        main.listItems.clear();
                        main.listItems.addAll(arrayList);
                    }
                });
                main.arrayAdapter.notifyDataSetChanged();
                finish();
            }
        });

    }
}