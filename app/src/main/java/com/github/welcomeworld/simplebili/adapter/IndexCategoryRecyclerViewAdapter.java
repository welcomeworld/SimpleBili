package com.github.welcomeworld.simplebili.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.welcomeworld.simplebili.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexCategoryRecyclerViewAdapter extends RecyclerView.Adapter<IndexCategoryRecyclerViewAdapter.ViewHolder>{

    private final int HEADERVIEWTYPE=0x2233;
    private int[] categoryIds={1,167,3,129,4,36,160,119,155,5,181};
    private int[] categoryImages={R.mipmap.ic_category_animation,R.mipmap.ic_category_hope,R.mipmap.ic_category_music,R.mipmap.ic_category_dance,R.mipmap.ic_category_game,R.mipmap.ic_category_science,R.mipmap.ic_category_life,R.mipmap.ic_category_art,R.mipmap.ic_category_fashion,R.mipmap.ic_category_amusement,R.mipmap.ic_category_film,R.mipmap.ic_category_game_center};
    private int[] detailHeaders={R.string.animation,R.string.hope,R.string.music,R.string.dance,R.string.game,R.string.science,R.string.life,R.string.art,R.string.fashion,R.string.amusement,R.string.film,R.string.game_center};

    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.imagetextview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(detailHeaders[position]);
        Drawable drawable=context.getResources().getDrawable(categoryImages[position]);
        drawable.setBounds(holder.textView.getCompoundDrawables()[1].getBounds());
        holder.textView.setCompoundDrawables(null,drawable,null,null);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==11){
                    Intent gameIntent=new Intent();
                    gameIntent.setData(Uri.parse("https://mobilegame-1.biligame.com/?statusBarHeight=48"));
                    gameIntent.setPackage(v.getContext().getPackageName());
                    v.getContext().startActivity(gameIntent);
                }else{
                    Intent categoryIntent=new Intent("com.github.welcomeworld.simplebili.action.CATEGORYPAGER");
                    categoryIntent.putExtra("rid",categoryIds[position]);
                    v.getContext().startActivity(categoryIntent);
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)return HEADERVIEWTYPE;
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            textView= (TextView) itemView;
        }
    }
}
