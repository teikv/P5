package com.example.p2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText A;
    private EditText B;
    private Button btnAdd;
    private Button btnMinus;
    private Button btnMultiply;
    private Button btnDivide;
    private ListView lvCount;
    private ArrayList arrayList;
    private ArrayAdapter arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        A = findViewById(R.id.a);
        
        B = findViewById(R.id.b);

        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);
        btnMultiply = findViewById(R.id.btn_multiply);
        btnDivide = findViewById(R.id.btn_divide);

        lvCount = findViewById(R.id.lv_count);
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);
        lvCount.setAdapter(arrayAdapter);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double input_a = Double.parseDouble(A.getText().toString());
                double input_b = Double.parseDouble(B.getText().toString());
                double sum = input_a + input_b;

                arrayList.add(input_a +" + "+input_b +" = "+ sum);
                arrayAdapter.notifyDataSetChanged();

            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double input_a = Double.parseDouble(A.getText().toString());
                double input_b = Double.parseDouble(B.getText().toString());
                double sum = input_a - input_b;

                arrayList.add(input_a +" - "+input_b +" = "+ sum);
                arrayAdapter.notifyDataSetChanged();

            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double input_a = Double.parseDouble(A.getText().toString());
                double input_b = Double.parseDouble(B.getText().toString());
                double sum = input_a * input_b;

                arrayList.add(input_a +" * "+input_b +" = "+ sum);
                arrayAdapter.notifyDataSetChanged();

            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double input_a = Double.parseDouble(A.getText().toString());
                double input_b = Double.parseDouble(B.getText().toString());
                double sum = input_a / input_b;

                arrayList.add(input_a +" / "+input_b +" = "+ sum);
                arrayAdapter.notifyDataSetChanged();

            }
        });
        lvCount.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });
        lvCount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("operator", arrayList.get(position).toString());
                startActivity(intent);
            }
        });
    }
}
