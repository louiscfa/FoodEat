package com.example.foodeat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListePanierAdapter extends RecyclerView.Adapter<ListePanierAdapter.ViewHolder>{
    private ArrayList<FoodDomain> foodDomains;
    private ManagementCart managementCart;
    private INumberList listener;

    public ListePanierAdapter(ArrayList<FoodDomain> foodDomains, Context context, ManagementCart managementCart, INumberList listener) {
        this.foodDomains = foodDomains;
        this.managementCart = new ManagementCart(context);
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_panier,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewNomProduit.setText(foodDomains.get(position).getTitle());
        holder.textViewPrixProduit.setText(String.valueOf(foodDomains.get(position).getfee()));
        holder.textViewNombre.setText(String.valueOf(foodDomains.get(position).getNumberInCart()));
        holder.textViewTotalProduit.setText(String.valueOf(foodDomains.get(position).getNumberInCart() * foodDomains.get(position).getFee()*100)/100);

        int drawableRessourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(),
                "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableRessourceId)
                .into(holder.pic);

        holder.imageViewPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.ajoutProduit(foodDomains, position, new INumberList() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        listener.changed();
                    }
                });
            }
        });

        holder.imageViewMoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.retireProduit(foodDomains, position, new INumberList() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        listener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView textViewNomProduit, textViewPrixProduit, textViewNombre, textViewTotalProduit;
    private ImageView imageViewProduit, imageViewMoins, imageViewPlus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomProduit = itemView.findViewById(R.id.textViewNomProduit);
            textViewPrixProduit = itemView.findViewById(R.id.textViewPrixProduit);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewTotalProduit = itemView.findViewById(R.id.textViewTotalProduit);

            imageViewProduit = itemView.findViewById(R.id.imageViewProduit);
            imageViewMoins = itemView.findViewById(R.id.imageViewMoins);
            imageViewPlus = itemView.findViewById(R.id.imageViewPlus);
        }
    }
}
