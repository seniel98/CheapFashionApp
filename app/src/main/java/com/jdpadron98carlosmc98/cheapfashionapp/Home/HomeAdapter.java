package com.jdpadron98carlosmc98.cheapfashionapp.Home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jdpadron98carlosmc98.cheapfashionapp.R;
import com.jdpadron98carlosmc98.cheapfashionapp.data.ProductItem;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<ProductItem> itemList;
    private final View.OnClickListener clickListener;


    public HomeAdapter(View.OnClickListener listener, List<ProductItem> itemList) {

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
        itemList.clear();
        itemList.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_product_item, parent, false);
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
        holder.itemView.setTag(itemList.get(position));
        holder.itemView.setOnClickListener(clickListener);
        loadImageFromURL(holder.imageProduct, itemList.get(position).getPicture());
        //Insertar la imagen al producto que creamos para posteriormente tenerla en el detalle
        itemList.get(position).imageView = holder.imageProduct;

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView priceProduct;
        final TextView nameProduct;
        final ImageView imageProduct;
        final ImageView productLikedButton;

        ViewHolder(View view) {
            super(view);
            priceProduct = view.findViewById(R.id.homeProductPrice);
            nameProduct = view.findViewById(R.id.homeProductName);
            imageProduct = view.findViewById(R.id.homeProductImage);
            productLikedButton = view.findViewById(R.id.homeProductLikedButton);
        }
    }

    private void loadImageFromURL(ImageView imageView, String imageURL) {
        Glide.with(imageView.getContext())
                .load(imageURL)
                .into(imageView);

    }
}


