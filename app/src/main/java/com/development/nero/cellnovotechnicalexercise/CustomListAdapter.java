package com.development.nero.cellnovotechnicalexercise;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ProductViewHolder> {

    ArrayList<Records> recordsArrayList;
    Context context;
    //Interactor

    public CustomListAdapter(Context context, ArrayList<Records> recordsArrayList){
        this.context = context;
        this.recordsArrayList = recordsArrayList;
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
        final Records records = recordsArrayList.get(i);
        records.setPosition(i);

        productViewHolder.name.setText(records.getName());
        productViewHolder.description.setText(records.getDescription());
        productViewHolder.price.setText(records.getPrice());
        productViewHolder.category.setText(records.getCategory_id());

        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return recordsArrayList.size();
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
