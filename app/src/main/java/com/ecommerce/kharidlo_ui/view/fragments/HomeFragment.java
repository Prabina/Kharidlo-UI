package com.ecommerce.kharidlo_ui.view.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ecommerce.kharidlo_ui.R;
import com.ecommerce.kharidlo_ui.model.Product;
import com.ecommerce.kharidlo_ui.view.adapters.ProductListAdapter;
import com.ecommerce.kharidlo_ui.viewmodel.ProductSearchViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private ProductListAdapter productListAdapter;
    private boolean isList = true;
    private ImageView listViewButton;
    private ImageView gridViewButton;
    private ImageView searchButton;
    private EditText searchEditText;
    List<Product> productList;
    private ProductSearchViewModel productSearchViewModel;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setListGridLayoutActions(view);
        setSearchButtonAction(view);

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);

        if (isList) {
            recyclerView.setLayoutManager(linearLayoutManager);
        } else {
            recyclerView.setLayoutManager(gridLayoutManager);
        }

        //TODO: Change this with product api response
        productList = new ArrayList<>();
        productListAdapter = new ProductListAdapter(productList, isList, getActivity());
        recyclerView.setAdapter(productListAdapter);

        loadProducts();
    }

    private void setListGridLayoutActions(View view) {
        listViewButton = (ImageView) view.findViewById(R.id.list_button);
        gridViewButton = (ImageView) view.findViewById(R.id.grid_button);

        View.OnClickListener listGridButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isList = !isList;
                resetProductRecyclerView();
            }
        };

        listViewButton.setOnClickListener(listGridButtonListener);
        gridViewButton.setOnClickListener(listGridButtonListener);
    }

    private void setSearchButtonAction(View view) {
        searchButton = (ImageView) view.findViewById(R.id.search_button);
        searchEditText = view.findViewById(R.id.search_key);
        View.OnClickListener searchButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productSearchViewModel.search(searchEditText.getText().toString());
            }
        };
        searchButton.setOnClickListener(searchButtonListener);
    }

    private void resetProductRecyclerView() {
        if (isList) {
            recyclerView.setLayoutManager(linearLayoutManager);
        } else {
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        productListAdapter.setLayoutType(isList);
        recyclerView.requestLayout();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void loadProducts(){
        productSearchViewModel = ViewModelProviders.of(this).get(ProductSearchViewModel.class);
        productSearchViewModel.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                HomeFragment.this.productList = products;
                if(products == null || (products != null && products.size() == 0)){
                    Toast.makeText(getContext(), "Oops! No product found",Toast.LENGTH_SHORT).show();
                }
                productListAdapter.add(products);
                productListAdapter.notifyDataSetChanged();
            }
        });
    }

}
