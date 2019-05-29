package com.github.welcomeworld.simplebili.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.bean.BangumiDetailPageBean;
import com.github.welcomeworld.simplebili.bean.BangumiDetailRecommendBean;
import com.github.welcomeworld.simplebili.bean.ReplyCursorBean;
import com.github.welcomeworld.simplebili.bean.VideoDetailPageBean;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.DynamicParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedHeaderInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.FixedParameterInterceptor;
import com.github.welcomeworld.simplebili.net.okhttp.interceptor.SortAndSignInterceptor;
import com.github.welcomeworld.simplebili.net.retrofit.BangumiDetailNetAPI;
import com.github.welcomeworld.simplebili.net.retrofit.BaseUrl;
import com.github.welcomeworld.simplebili.utils.StringUtils;
import com.github.welcomeworld.simplebili.widget.SwiperefreshContainer;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BangumiDetailPagerAdapter extends PagerAdapter {

    private BangumiDetailPageBean.ResultBean descData;
    private ReplyCursorBean.DataBean replyData;
    private BangumiDetailRecommendBean.ResultBean recommendData;
    private SwiperefreshContainer.OnLoadListener loadListener;
    private SwiperefreshContainer.OnRefreshListener refreshListener;
    private SwiperefreshContainer replyView;
    private ConstraintLayout descView;
    private boolean descChanged=false;
    private View.OnClickListener listener;
    private boolean replyChanged=false;
    private int maxId=0;
    private VideoDetailReplyRecyclerViewAdapter replyRecyclerViewAdapter;
    private RecyclerView recommendRecyclerView;
    public BangumiDetailPagerAdapter(BangumiDetailPageBean.ResultBean descData,ReplyCursorBean.DataBean replyData) {
        this.descData=descData;
        this.replyData=replyData;
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if(object==descView){
            return descChanged?POSITION_NONE:POSITION_UNCHANGED;
        }else{
            return replyChanged?POSITION_NONE:POSITION_UNCHANGED;
        }
    }

    public void setOnClickListener(View.OnClickListener v){
        this.listener = v;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if(position==0){
            descView= (ConstraintLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.bangumi_detail_desc,container,false);
            if(descData!=null){
                Glide.with(container.getContext()).load(descData.getCover()+"@240w_320h_1e_1c.webp").apply(new RequestOptions().transform(new FitCenter(),new RoundedCorners(10))).into((ImageView) descView.findViewById(R.id.bangumi_detail_desc_cover));
                ((TextView)descView.findViewById(R.id.bangumi_detail_desc_title)).setText(descData.getSeasonTitle());
                ((TextView)descView.findViewById(R.id.bangumi_detail_desc_play_num)).setText(StringUtils.formatNumber(descData.getStat().getViews()));
                ((TextView)descView.findViewById(R.id.bangumi_detail_desc_subscribe_num)).setText(StringUtils.formatNumber(descData.getStat().getFavorites()));
                ((TextView)descView.findViewById(R.id.bangumi_detail_desc_desc)).setText(descData.getNewEp().getDesc());
                ((TextView)descView.findViewById(R.id.bangumi_detail_desc_introduce)).setText(descData.getEvaluate());
                if(descData.getRating()==null){
                    descView.findViewById(R.id.bangumi_detail_desc_evaluate_suffix).setVisibility(View.GONE);
                    descView.findViewById(R.id.bangumi_detail_desc_evaluate_text).setVisibility(View.GONE);
                    descView.findViewById(R.id.bangumi_detail_desc_evaluate_num).setVisibility(View.GONE);
                    //descView.findViewById(R.id.bangumi_detail_desc_evaluate).setEnabled(false);
                }else {
                    ((TextView)descView.findViewById(R.id.bangumi_detail_desc_evaluate_text)).setText(descData.getRating().getScore()+"");
                    ((TextView)descView.findViewById(R.id.bangumi_detail_desc_evaluate_num)).setText(StringUtils.formatNumber(descData.getRating().getCount())+"人");
                }
                ((TextView)descView.findViewById(R.id.bangumi_detail_desc_coin)).setText(StringUtils.formatNumber(descData.getStat().getCoins()));
                ((TextView)descView.findViewById(R.id.bangumi_detail_desc_share)).setText(StringUtils.formatNumber(descData.getStat().getShare()));
                descView.findViewById(R.id.bangumi_detail_desc_download).setOnClickListener(listener);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                BangumiDetailDescRecyclerViewAdapter recyclerViewAdapter = new BangumiDetailDescRecyclerViewAdapter(recommendData);
                recommendRecyclerView = descView.findViewById(R.id.bangumi_detail_desc_recyclerview);
                recommendRecyclerView .setAdapter(recyclerViewAdapter);
                recommendRecyclerView .setLayoutManager(linearLayoutManager);
                if(recommendData==null){
                    getRecomend();
                }
            }
            container.addView(descView);
            return descView;
        }else{
            replyView= (SwiperefreshContainer) LayoutInflater.from(container.getContext()).inflate(R.layout.video_detail_reply,container,false);
            replyView.setOnLoadListener(loadListener);
            replyView.setOnRefreshListener(refreshListener);
            replyView.setColorSchemeColors(container.getContext().getResources().getColor(R.color.colorPrimary));
            RecyclerView recyclerView=replyView.findViewById(R.id.video_detail_reply_recyclerview);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(container.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(),DividerItemDecoration.VERTICAL));
            recyclerView.setLayoutManager(linearLayoutManager);
            replyRecyclerViewAdapter=new VideoDetailReplyRecyclerViewAdapter(replyData);
            recyclerView.setAdapter(replyRecyclerViewAdapter);
            container.addView(replyView);
            return replyView;
        }
    }

    private void getRecomend(){
        if(descData == null){
            return;
        }
        Map<String,String> parameters=new HashMap<>();
        parameters.put("season_id",descData.getSeasonId()+"");
        parameters.put("ts",""+System.currentTimeMillis());
        parameters.put("qn","32");
        OkHttpClient.Builder okHttpClientBuilder=new OkHttpClient.Builder()
                .addInterceptor(new FixedHeaderInterceptor())
                .addInterceptor(new DynamicHeaderInterceptor(null))
                .addInterceptor(new FixedParameterInterceptor())
                .addInterceptor(new DynamicParameterInterceptor(parameters))
                .addInterceptor(new SortAndSignInterceptor())
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl.APIURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();
        BangumiDetailNetAPI bangumiDetailNetAPI = retrofit.create(BangumiDetailNetAPI.class);
        bangumiDetailNetAPI.getBangumiRecommend().enqueue(new Callback<BangumiDetailRecommendBean>() {
            @Override
            public void onResponse(Call<BangumiDetailRecommendBean> call, Response<BangumiDetailRecommendBean> response) {
                recommendData = response.body().getResult();
                if(recommendRecyclerView!=null){
                    recommendRecyclerView.setAdapter(new BangumiDetailDescRecyclerViewAdapter(recommendData));
                }
            }

            @Override
            public void onFailure(Call<BangumiDetailRecommendBean> call, Throwable throwable) {

            }
        });
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    public void setLoadListener(SwiperefreshContainer.OnLoadListener loadListener) {
        this.loadListener = loadListener;
    }

    public void setRefreshListener(SwiperefreshContainer.OnRefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }

    public void setRefreshing(boolean refreshing){
        if(replyView!=null){
            replyView.setRefreshing(refreshing);
        }
    }

    public void setLoading(boolean loading){
        if(replyView!=null){
            replyView.setLoading(loading);
        }
    }
    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId){
        this.maxId = maxId;
    }


    public void setReplyData(ReplyCursorBean.DataBean replyData) {
        this.replyData = replyData;
        if(replyData!=null){
            if(replyData.getCursor().getMin_id()>1){
                maxId=replyData.getCursor().getMin_id()-1;
            }else{
                maxId=0;
            }
            replyChanged=true;
            notifyDataSetChanged();
        }
    }

    public void addReplyData(ReplyCursorBean.DataBean replyData){
        if(this.replyData==null){
            setReplyData(replyData);
            return;
        }
        if(replyData!=null){
            int index=replyRecyclerViewAdapter.getItemCount();
            this.replyData.getReplies().addAll(replyData.getReplies());
            if(replyData.getCursor().getMin_id()>1){
                maxId=replyData.getCursor().getMin_id()-1;
            }else{
                maxId=0;
            }
            replyRecyclerViewAdapter.notifyItemRangeInserted(index,replyData.getCursor().getSize());
        }
    }

    public void setDescData(BangumiDetailPageBean.ResultBean descData) {
        this.descData = descData;
        descChanged=true;
        notifyDataSetChanged();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

}
