package com.ecommerce.kharidlo_ui.view.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.ecommerce.kharidlo_ui.R;
import com.ecommerce.kharidlo_ui.model.CartItem;
import com.ecommerce.kharidlo_ui.utils.CartData;

import java.util.ArrayList;
import java.util.List;

public class CartItemsAdapter extends ArrayAdapter<CartItem> implements View.OnClickListener{

    private List<CartItem> cartItems;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView tvTitle;
        TextView tvPrice;
        EditText etQuantity;
    }

    public CartItemsAdapter(List<CartItem> data, Context context) {
        super(context, R.layout.cart_item_list_row, data);
        this.cartItems = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

       /* int position=(Integer) v.getTag();
        Object object= getItem(position);
        DataModel dataModel=(DataModel)object;

        switch (v.getId())
        {
            case R.id.item_info:
                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }*/
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final CartItem dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.cart_item_list_row, parent, false);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.cart_item_title);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.cart_item_price);
            viewHolder.etQuantity = (EditText)convertView.findViewById(R.id.cart_item_quantity);
            viewHolder.etQuantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {



                }

                @Override
                public void afterTextChanged(Editable s) {
                    double price = dataModel.getPrice();
                    int quantity = Integer.parseInt(viewHolder.etQuantity.getText().toString());
                    double finalPrice = price * quantity;
                    viewHolder.tvPrice.setText(String.valueOf(finalPrice));
                    dataModel.setQuantity(quantity);
                }
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTitle.setText(dataModel.getTitle()+"");
        viewHolder.tvPrice.setText(String.valueOf(dataModel.getPrice())+"");
        viewHolder.etQuantity.setText(dataModel.getQuantity()+"");



        // Return the completed view to render on screen
        return convertView;
    }
}