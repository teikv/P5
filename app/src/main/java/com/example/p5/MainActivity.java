package com.example.p5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.p5.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected ArrayList<String> listItems = new ArrayList<String>();
    protected ArrayAdapter arrayAdapter;
    public MyViewModel model;

    private ActivityMainBinding binding;
    private static MainActivity instance;

    public MainActivity () {
        instance = this;
    }

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        model = new ViewModelProvider(this).get(MyViewModel.class);
        model.getArrayList().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> arrayList) {
                listItems.clear();
                listItems.addAll(arrayList);
            }
        });


        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listItems);
        binding.lvCount.setAdapter(arrayAdapter);

        binding.textView.setEnabled(false);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();

            }
        });

        binding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minus();
            }
        });
        binding.btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiply();
            }
        });
        binding.btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                divide();
            }
        });
        binding.lvCount.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listItems.remove(position);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });
        binding.lvCount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("operator", listItems.get(position).toString());
              //  intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    private double[] getNumber() {
        try {
            double a = Double.parseDouble(binding.a.getText().toString());
            double b = Double.parseDouble(binding.b.getText().toString());
            double[] array = new double[2];
            array[0] = a;
            array[1] = b;
            return array;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new double[2];
    }

    private void showResult(double result) {
        binding.textView.setText("" + result);
    }

    private void addListResult(String stringResult) {
        String item = stringResult;
        listItems.add(item);
        model.addListItems(item);
        arrayAdapter.notifyDataSetChanged();
    }

    private void add() {
            try {
                double[] arrayNum = getNumber();
                double a = arrayNum[0];
                double b = arrayNum[1];
                double result = a + b;
                showResult(result);
                addListResult(a + " + " + b + " = " + result);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private void minus() {
            try {
                double[] arrayNum = getNumber();
                double a = arrayNum[0];
                double b = arrayNum[1];
                double result = a - b;
                showResult(result);
                addListResult(a + " - " + b + " = " + result);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private void multiply() {
            try {
                double[] arrayNum = getNumber();
                double a = arrayNum[0];
                double b = arrayNum[1];
                double result = a * b;
                showResult(result);
                addListResult(a + " * " + b + " = " + result);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private void divide() {
            try {
                double[] arrayNum = getNumber();
                double a = arrayNum[0];
                double b = arrayNum[1];
                double result = a / b;
                showResult(result);
                addListResult(a + " / " + b + " = " + result);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


