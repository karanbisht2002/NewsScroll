package com.welcomekaran.newsscroll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.security.PublicKey;
import java.util.ArrayList;

public class CategoriesRVAdapter extends RecyclerView.Adapter<CategoriesRVAdapter.ViewHolderclass> {
    private ArrayList<CategorieyRVModal> categorieyRVModalArrayList;
    private Context context;
    private CategoriesClickInterface categoriesClickInterface;
    CategoriesRVAdapter categoriesRVAdapter;
    int selected_position = -1; // You have to set this globally in the Adapter class

    public CategoriesRVAdapter(ArrayList<CategorieyRVModal> categorieyRVModalArrayList, Context context, CategoriesClickInterface categoriesClickInterface) {
        this.categorieyRVModalArrayList = categorieyRVModalArrayList;
        this.context = context;
        this.categoriesClickInterface = categoriesClickInterface;
    }

    @NonNull
    @Override
    public CategoriesRVAdapter.ViewHolderclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_recycleview,parent,false);
        return new CategoriesRVAdapter.ViewHolderclass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesRVAdapter.ViewHolderclass holder, @SuppressLint("RecyclerView") int position) {
        CategorieyRVModal categorieyRVModal= categorieyRVModalArrayList.get(position);
        holder.cattextview.setText(categorieyRVModal.getCategory());
        Picasso.get().load(categorieyRVModal.getCategoryimage()).into(holder.catimageview);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriesClickInterface.onCategoriesclick(position);
                selected_position=position;
                notifyDataSetChanged();
            }
        });
      /*  holder.catimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  holder.cardView.animate();
              //  holder.cardView.getMaxCardElevation();
                categoriesClickInterface.onCategoriesclick(position);
           //holder.catimageview.setVisibility(View.GONE);
               // holder.recyclerViewe.requestFocus(position);
            }
        });
        */

        if(selected_position==position)
        {
            holder.cardView.setBackgroundColor(Color.RED);
            //holder.cardView.setRadius(100.0f);
            holder.cardView.setBackgroundResource(R.drawable.design);
        }
        else
        {
            holder.cardView.setBackgroundColor(Color.BLACK);
            holder.cardView.setRadius(0.0f);
        }

    }
    @Override
    public int getItemCount() {
        return categorieyRVModalArrayList.size();

    }
    public interface CategoriesClickInterface
    {
        void onCategoriesclick(int position);
    }


    public class ViewHolderclass extends RecyclerView.ViewHolder
    {
              private ImageView catimageview;
              private TextView cattextview;
              CardView cardView;

        public ViewHolderclass(@NonNull View itemView) {
            super(itemView);
            catimageview=itemView.findViewById(R.id.cat_image_recycle);
            cattextview=itemView.findViewById(R.id.cat_text);
            cardView=itemView.findViewById(R.id.cardview_id);



        }
    }
}
