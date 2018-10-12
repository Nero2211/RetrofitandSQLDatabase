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

    ArrayList<MyPojo> myPojoArrayList;
    Records records;
    Context context;
    //Interactor

    public CustomListAdapter(Context context, ArrayList<MyPojo> myPojoArrayList){
        this.context = context;
        this.myPojoArrayList = myPojoArrayList;
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
        final MyPojo pojo = myPojoArrayList.get(i);

        productViewHolder.name.setText(pojo.getRecords().get(i).getName());
        productViewHolder.description.setText(pojo.getRecords().get(i).getName());
        productViewHolder.price.setText(pojo.getRecords().get(i).getPrice());
        productViewHolder.category.setText(pojo.getRecords().get(i).getCategoryName());

        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return myPojoArrayList.size();
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
