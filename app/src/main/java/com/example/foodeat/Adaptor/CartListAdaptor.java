package com.example.foodeat.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodeat.Domain.FoodDomain;
import com.example.foodeat.Helper.ManagementCart;
import com.example.foodeat.Listener.INumberList;
import com.example.foodeat.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartListAdaptor extends RecyclerView.Adapter<CartListAdaptor.ViewHolder> {
    private ArrayList<FoodDomain> foodDomains;
    private INumberList listener;

    public CartListAdaptor(ArrayList<FoodDomain> foodDomains, Context context, INumberList listener) {
        this.foodDomains = foodDomains;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_panier,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        holder.textViewNomProduit.setText(foodDomains.get(position).getTitle());
        holder.textViewPrixProduit.setText(String.valueOf(foodDomains.get(position).getFee()) + " €");
        holder.textViewNombre.setText(String.valueOf(foodDomains.get(position).getNumberInCart()));
        holder.textViewTotalProduit.setText(String.valueOf(numberFormat.format((double)(foodDomains.get(position).getNumberInCart() * foodDomains.get(position).getFee()*100)/100)) + " €");

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(),
                "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.imageViewProduit);

        holder.imageViewPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManagementCart.getInstance(v.getContext()).ajoutProduit(foodDomains, position, new INumberList() {
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
                ManagementCart.getInstance(v.getContext()).retireProduit(foodDomains, position, new INumberList() {
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

            float[] colorTransform = {
                    0.33f, 0.33f, 0.33f, 0, 0,
                    0.33f, 0.33f, 0.33f, 0, 0,
                    0.33f, 0.33f, 0.33f, 0, 0,
                    0, 0, 0, 1, 0 };
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0f); //Remove Colour
            colorMatrix.set(colorTransform); //Apply the Red
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
            imageViewProduit = itemView.findViewById(R.id.imageViewProduit);
            imageViewMoins = itemView.findViewById(R.id.imageViewMoins);
            imageViewMoins.setColorFilter(filter);
            imageViewPlus = itemView.findViewById(R.id.imageViewPlus);
            imageViewPlus.setColorFilter(filter);
        }
    }
}
