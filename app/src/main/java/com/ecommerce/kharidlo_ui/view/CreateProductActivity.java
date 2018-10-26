package com.ecommerce.kharidlo_ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ecommerce.kharidlo_ui.R;
import com.ecommerce.kharidlo_ui.model.Product;
import com.ecommerce.kharidlo_ui.utils.SharedPreferenceUtil;
import com.ecommerce.kharidlo_ui.viewmodel.CreateProductViewModel;
import com.google.gson.internal.LinkedTreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateProductActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etDescription;
    private EditText etPrice;
    private EditText etFeatures;
    private EditText etQuantity;
    private Spinner spCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        etTitle = (EditText)findViewById(R.id.et_title);
        etDescription = (EditText)findViewById(R.id.et_description);
        etPrice = (EditText)findViewById(R.id.et_price);
        etFeatures = (EditText)findViewById(R.id.et_feature);
        etQuantity = (EditText)findViewById(R.id.et_quantity);
        spCategory = (Spinner)findViewById(R.id.sp_category);

    }

    public void onCreateProduct(View view) {

        String title = etTitle.getText().toString();
        String desc = etDescription.getText().toString();
        String price = etPrice.getText().toString();
        String features = etFeatures.getText().toString();
        String quantity = etQuantity.getText().toString();
        String category = spCategory.getSelectedItem().toString();

        Product product = new Product();
        product.setTitle(title);
        product.setDescription(desc);
        product.setPrice(Double.parseDouble(price));
        product.setFeatures(features);
        product.setQuantity(Integer.parseInt(quantity));
        product.setCategory(category);

        CreateProductViewModel createProductViewModel = new CreateProductViewModel();

        createProductViewModel.create(product, new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                if (response.code() == 403 || response.code() == 404) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (response.code() == 202) {
                    Toast.makeText(CreateProductActivity.this,"Success",Toast.LENGTH_SHORT).show();
                   /* Intent intent = new Intent(CreateProductActivity.this,HomeActivity.class);
                    startActivity(intent);*/
                    finish();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                }

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(CreateProductActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
