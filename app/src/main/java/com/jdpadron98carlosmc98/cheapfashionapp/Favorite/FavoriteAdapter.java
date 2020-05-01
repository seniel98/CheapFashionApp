package com.jdpadron98carlosmc98.cheapfashionapp.Favorite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.app.ProductItem;

import java.util.List;

public class FavoriteAdapter
        extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<ProductItem> itemList;
    private final View.OnClickListener clickListener;


    public FavoriteAdapter(View.OnClickListener listener, List<ProductItem> itemList) {

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setTag(itemList.get(position));
        holder.itemView.setOnClickListener(clickListener);
        holder.priceProduct.setText(itemList.get(position).getPrice());
        holder.nameProduct.setText(itemList.get(position).getName());
        //Esto esta hecho solo para la version del primer Sprint
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
        final ImageView favoriteButton;

        ViewHolder(View view) {
            super(view);
            priceProduct = view.findViewById(R.id.favoriteProductPrice);
            nameProduct = view.findViewById(R.id.favoriteProductName);
            imageProduct = view.findViewById(R.id.favoriteProductImage);
            favoriteButton = view.findViewById(R.id.favoriteProductLikeButton);
        }
    }

    private void loadImageFromURL(ImageView imageView, String imageURL) {
        Glide.with(imageView.getContext())
                .load(imageURL)
                .into(imageView);
    }
}