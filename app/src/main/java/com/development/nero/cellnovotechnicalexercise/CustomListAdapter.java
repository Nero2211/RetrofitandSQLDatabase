package com.development.nero.cellnovotechnicalexercise;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ProductViewHolder> {

    List<Product> products;
    Context context;

    public CustomListAdapter(Context context, List<Product> products){
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflatedView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem,
                viewGroup,
                false);
        return new ProductViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {

        productViewHolder.name.setText(products.get(i).getName());
        productViewHolder.description.setText(products.get(i).getDescription());
        productViewHolder.price.setText(products.get(i).getPrice());
        productViewHolder.category.setText(products.get(i).getCategoryName());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        private TextView name, description, price, category;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.listItem_name);
            description = (TextView)itemView.findViewById(R.id.listItem_description);
            price = (TextView)itemView.findViewById(R.id.listItem_price);
            category = (TextView)itemView.findViewById(R.id.listItem_category);
        }
    }

}
