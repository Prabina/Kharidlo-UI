package com.ecommerce.kharidlo_ui.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.kharidlo_ui.R;
import com.ecommerce.kharidlo_ui.model.CartItem;
import com.ecommerce.kharidlo_ui.model.Product;
import com.ecommerce.kharidlo_ui.utils.CartData;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {

    public static final String PRODUCT_ITEM = "product";
    private Product product;
    private TextView name;
    private TextView price;
    private TextView description;
    private TextView features;
    private TextView category;
    private Button addToCart;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        name = findViewById(R.id.product_name);
        price = findViewById(R.id.product_price);
        description = findViewById(R.id.product_description);
        features = findViewById(R.id.product_features);
        category = findViewById(R.id.product_category);
        addToCart = findViewById(R.id.product_add_to_cart);
        image = findViewById(R.id.product_image);

        product = (Product) getIntent().getSerializableExtra(PRODUCT_ITEM);
        name.setText(product.getTitle());
        price.setText(String.format("Rs. %s", String.valueOf(product.getPrice())));
        description.setText(product.getDescription());
        features.setText(product.getFeatures());
        category.setText(product.getCategory());

        Picasso.with(this).load(product.getImageUrl()).into(image);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem cartItem = new CartItem(product.getTitle(), product.getId(), 1, product.getPrice());
                CartData.getInstance().addItemToCart(cartItem);
                Toast.makeText(ProductDetailActivity.this, "Item added to cart successfully!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
