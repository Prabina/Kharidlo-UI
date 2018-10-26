package com.ecommerce.kharidlo_ui.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.kharidlo_ui.R;
import com.ecommerce.kharidlo_ui.model.CartItem;
import com.ecommerce.kharidlo_ui.model.Product;
import com.ecommerce.kharidlo_ui.utils.CartData;
import com.ecommerce.kharidlo_ui.utils.SharedPreferenceUtil;
import com.ecommerce.kharidlo_ui.view.ProductDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private List<Product> products = new ArrayList<>();
    private boolean isList;
    private Context context;
    private boolean isAdmin;
    private CartData cartData = CartData.getInstance();

    public ProductListAdapter(List<Product> myDataset, boolean isList, Context context) {
        if(myDataset != null) {
            products = myDataset;
        }
        this.isList = isList;
        this.context = context;
        isAdmin = SharedPreferenceUtil.isAdmin();
    }

    public void setLayoutType(boolean isList) {
        this.isList = isList;
    }

    public void add(List<Product> item) {
        if(item != null) {
            products = item;
        }
    }

    public void remove(int position) {
        products.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent,
                                                            final int viewType) {
        ViewHolder viewHolder = null;
        final View view;

        if (isList) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list, parent, false);
            viewHolder = new ViewHolderList(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_grid, parent, false);
            viewHolder = new ViewHolderGrid(view);
        }

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (isList) {
            holder = (ViewHolderList) holder;
        } else {
            holder = (ViewHolderGrid) holder;
        }

        if(isAdmin) {
            holder.addToCart.setVisibility(View.INVISIBLE);
        }

        final String name = products.get(position).getTitle();
        holder.name.setText(name);
        holder.addToCart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = products.get(position);
                CartItem cartItem = new CartItem(product.getTitle(),product.getId(), 1, product.getPrice());
                cartData.addItemToCart(cartItem);
                Toast toast = Toast.makeText(context, "Item added to cart successfully!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        holder.price.setText(String.valueOf(products.get(position).getPrice()));
        Picasso.with(holder.image.getContext()).load(products.get(position).getImageUrl()).into(holder.image);

        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Ad", "clicked >> ");
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("product", products.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return isList?0:1;
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView price;
        public ImageView image;
        public View layout;
        public ImageView addToCart;

        public ViewHolder(View v) {
            super(v);
            layout = v;
        }
    }

    public class ViewHolderList extends ViewHolder {

        public ViewHolderList(View v) {
            super(v);
            layout = v;
            name = (TextView) v.findViewById(R.id.name);
            price = (TextView) v.findViewById(R.id.cart_item_price);
            image = (ImageView) v.findViewById(R.id.icon);
            addToCart = (ImageView) v.findViewById(R.id.add_to_cart);
        }
    }

    public class ViewHolderGrid extends ViewHolder {

        public ViewHolderGrid(View v) {
            super(v);
            layout = v;
            name = (TextView) v.findViewById(R.id.name);
            price = (TextView) v.findViewById(R.id.cart_item_price);
            image = (ImageView) v.findViewById(R.id.icon);
            addToCart = (ImageView) v.findViewById(R.id.add_to_cart);
        }
    }


}
