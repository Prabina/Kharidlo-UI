package com.ecommerce.kharidlo_ui.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ecommerce.kharidlo_ui.R;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private List<String> values;
    private boolean isList;

    public ProductListAdapter(List<String> myDataset, boolean isList) {
        values = myDataset;
        this.isList = isList;
    }

    public void setLayoutType(boolean isList) {
        this.isList = isList;
    }

    public void add(int position, String item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        ViewHolder viewHolder = null;
        View view;

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

        final String name = values.get(position);
        holder.name.setText(name);
        holder.name.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: add to cart action to be implemented
            }
        });

        holder.price.setText("Change this to price");
    }

    @Override
    public int getItemViewType(int position) {
        return isList?0:1;
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView price;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            name = (TextView) v.findViewById(R.id.name);
            price = (TextView) v.findViewById(R.id.price);
        }
    }

    public class ViewHolderList extends ViewHolder {

        public TextView name;
        public TextView price;
        public View layout;

        public ViewHolderList(View v) {
            super(v);
            layout = v;
            name = (TextView) v.findViewById(R.id.name);
            price = (TextView) v.findViewById(R.id.price);
        }
    }

    public class ViewHolderGrid extends ViewHolder {
        public TextView name;
        public TextView price;
        public View layout;

        public ViewHolderGrid(View v) {
            super(v);
            layout = v;
            name = (TextView) v.findViewById(R.id.name);
            price = (TextView) v.findViewById(R.id.price);
        }
    }


}
