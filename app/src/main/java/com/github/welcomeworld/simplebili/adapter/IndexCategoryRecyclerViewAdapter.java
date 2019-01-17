package com.github.welcomeworld.simplebili.adapter;

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
    private int[] detailHeaders={R.string.bangumi_area,R.string.animation_area,R.string.hope_area,R.string.music_area,R.string.dance_area,R.string.game_area,R.string.science_area,R.string.life_area,R.string.art_area,R.string.fashion_area,R.string.amusement_area,R.string.record_area,R.string.film_area,R.string.tv_area};
    private int[] detailFooters={R.string.bangumi_more,R.string.animation_more,R.string.hope_more,R.string.music_more,R.string.dance_more,R.string.game_more,R.string.science_more,R.string.life_more,R.string.art_more,R.string.fashion_more,R.string.amusement_more,R.string.record_more,R.string.film_more,R.string.tv_more};


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==HEADERVIEWTYPE)return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_category_header,parent,false));
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_category_detail,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(getItemViewType(position)!=HEADERVIEWTYPE){
            holder.headerTitleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),holder.headerTitleView.getText(),Toast.LENGTH_SHORT).show();
                }
            });
            holder.headerTitleView.setText(detailHeaders[position>detailHeaders.length?detailHeaders.length-1:position-1]);
            holder.footerMoreView.setText(detailFooters[position>detailFooters.length?detailFooters.length-1:position-1]);
            holder.footerMoreView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),holder.footerMoreView.getText(),Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)return HEADERVIEWTYPE;
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @Nullable
        @BindView(R.id.category_detail_header_title)
        TextView headerTitleView;
        @Nullable
        @BindView(R.id.category_detail_footer_more)
        TextView footerMoreView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
