package com.jdpadron98carlosmc98.cheapfashionapp.MyProducts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class MyProductsAdapter extends RecyclerView.Adapter<MyProductsAdapter.ViewHolder> {

    private List<ProductItem> itemList;
    private final View.OnClickListener clickListener;


    public MyProductsAdapter(View.OnClickListener listener,List<ProductItem> itemList) {

        this.itemList = itemList;
        clickListener = listener;
    }


    public void addItem(ProductItem item) {
        itemList.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<ProductItem> items) {
        itemList.addAll(items);
        notifyDataSetChanged();
    }

    public void setItems(List<ProductItem> items) {
        itemList = items;
        notifyDataSetChanged();
    }

    @Override
    public MyProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_products_product_item, parent, false);
        return new MyProductsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyProductsAdapter.ViewHolder holder, int position) {
        holder.priceProduct.setText(itemList.get(position).getPrice());
        holder.nameProduct.setText(itemList.get(position).getName());
        //Esto esta hecho solo para la version del primer Sprint
        loadImageFromURL(holder.imageProduct, itemList.get(position).getPicture());
        //Insertar la imagen al producto que creamos para posteriormente tenerla en el detalle
        itemList.get(position).imageView = holder.imageProduct;
        //holder.imageProduct.setImageResource(itemList.get(position).getDrawable());
        //loadImageFromURL(holder.imageProduct, itemList.get(position).getPicture());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView priceProduct;
        final TextView nameProduct;
        final ImageView imageProduct;
        final ImageView productDeleteButton;

        ViewHolder(View view) {
            super(view);
            priceProduct = view.findViewById(R.id.myProductsProductPrice);
            nameProduct = view.findViewById(R.id.myProductsProductName);
            imageProduct = view.findViewById(R.id.myProductsProductImage);
            productDeleteButton = view.findViewById(R.id.myProductsProductDeleteButton);
        }
    }

    private void loadImageFromURL(ImageView imageView, String imageURL) {
        Glide.with(imageView.getContext())
                .load(imageURL)
                .into(imageView);
    }
}
