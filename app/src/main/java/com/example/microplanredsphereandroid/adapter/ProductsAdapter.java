package com.example.microplanredsphereandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.microplanredsphereandroid.R;
import com.example.microplanredsphereandroid.models.Product;
import com.example.microplanredsphereandroid.models.ProductEntry;

import java.util.List;
import java.util.Locale;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private final List<ProductEntry> products;

    public ProductsAdapter(List<ProductEntry> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductEntry productEntry = products.get(position);
        Product product = productEntry.getProduct();
        holder.name.setText(product.getName());
        holder.price.setText(String.format(Locale.UK, "$%.2f", product.getPrice()));
        holder.quantity.setText(String.valueOf(productEntry.getQuantity()));
        holder.add.setOnClickListener(v-> {
            add(productEntry);
            holder.quantity.setText(String.valueOf(productEntry.getQuantity()));
        });
        holder.subtract.setOnClickListener(v-> {
            subtract(productEntry);
            holder.quantity.setText(String.valueOf(productEntry.getQuantity()));
        });
    }

    private void subtract(ProductEntry product) {
        if (products.contains(product)) {
            if (product.getQuantity()>1) {
                product.setQuantity(product.getQuantity()-1);
            } else {
                product.setQuantity(0);
            }
        }
    }

    private void add(ProductEntry product) {
        if (products.contains(product)) {
            product.setQuantity(product.getQuantity()+1);
        } else {
            product.setQuantity(1);
        }
    }

    public interface OnProductSelected {
        void onSelect(Product product);
        void onRemove(Product product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price, quantity, add, subtract;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.textView19);
            price = itemView.findViewById(R.id.textView20);
            quantity = itemView.findViewById(R.id.quantity);
            add = itemView.findViewById(R.id.add);
            subtract = itemView.findViewById(R.id.subtract);
        }
    }
}
