package com.github.welcomeworld.simplebili.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.RequestOptions;
import com.github.welcomeworld.simplebili.R;
import com.github.welcomeworld.simplebili.bean.ReplyCursorBean;
import com.github.welcomeworld.simplebili.utils.DateUtils;
import com.github.welcomeworld.simplebili.utils.SpanUtils;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoDetailReplyRecyclerViewAdapter extends RecyclerView.Adapter<VideoDetailReplyRecyclerViewAdapter.MyInnerViewHolder>{
    ReplyCursorBean.DataBean data;
    VideoDetailReplyRecyclerViewAdapter(ReplyCursorBean.DataBean data){
        this.data=data;
    }
    private int hot=0;
    private int normal=0;
    private int top=0;
    Context context;


    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new MyInnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_reply,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        holder.subreplyContent1.setMovementMethod(LinkMovementMethod.getInstance());
        holder.subreplyContent2.setMovementMethod(LinkMovementMethod.getInstance());
        holder.subreplyContent3.setMovementMethod(LinkMovementMethod.getInstance());
        holder.dividerText.setVisibility(View.GONE);
        String clipText;
        if(position<top){
            clipText=data.getTop().getUpper().getContent().getMessage();
            ReplyCursorBean.DataBean.TopBean.UpperBean currentTop=data.getTop().getUpper();
            Glide.with(context).load(data.getTop().getUpper().getMember().getAvatar()+"@200w_200h_1e_1c.webp").apply(new RequestOptions().transform(new FitCenter(),new CircleCrop()).error(R.mipmap.ic_default_avatar).placeholder(holder.avatorView.getDrawable())).into(holder.avatorView);
            holder.nameView.setText(data.getTop().getUpper().getMember().getUname());
            holder.floorView.setText("#"+data.getTop().getUpper().getFloor());
            holder.dateView.setText(DateUtils.getDay(((long)data.getTop().getUpper().getCtime())*1000));
            holder.contentView.setText(data.getTop().getUpper().getContent().getMessage());
            holder.likeView.setText(data.getTop().getUpper().getLike()+"");
            if(currentTop.getReplies()!=null){
                holder.subreplyLayout.setVisibility(View.VISIBLE);
                if(currentTop.getReplies().size()>3){
                    for(int i=0;i<3;i++){
                        String linkString = currentTop.getReplies().get(i).getMember().getUname();
                        if(currentTop.getReplies().get(i).getContent().getMessage().startsWith("回复 ")){
                            linkString=linkString+"  "+currentTop.getReplies().get(i).getContent().getMessage();
                        }else{
                            linkString=linkString+":  "+currentTop.getReplies().get(i).getContent().getMessage();
                        }
                        switch (i){
                            case 0:
                                holder.subreplyContent1.setVisibility(View.VISIBLE);
                                holder.subreplyContent1.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent1);
                                break;
                            case 1:
                                holder.subreplyContent2.setVisibility(View.VISIBLE);
                                holder.subreplyContent2.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent2);
                                break;
                            case 2:
                                holder.subreplyContent3.setVisibility(View.VISIBLE);
                                holder.subreplyContent3.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent3);
                                break;
                        }
                    }
                    holder.subreplyAll.setText("共"+currentTop.getCount()+"条回复");
                    holder.subreplyAll.setVisibility(View.VISIBLE);
                }else{
                    for(int i=0;i<currentTop.getReplies().size();i++){
                        String linkString = currentTop.getReplies().get(i).getMember().getUname();
                        if(currentTop.getReplies().get(i).getContent().getMessage().startsWith("回复 ")){
                            linkString=linkString+"  "+currentTop.getReplies().get(i).getContent().getMessage();
                        }else{
                            linkString=linkString+":  "+currentTop.getReplies().get(i).getContent().getMessage();
                        }
                        switch (i){
                            case 0:
                                holder.subreplyContent1.setVisibility(View.VISIBLE);
                                holder.subreplyContent1.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent1);
                                break;
                            case 1:
                                holder.subreplyContent2.setVisibility(View.VISIBLE);
                                holder.subreplyContent2.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent2);
                                break;
                            case 2:
                                holder.subreplyContent3.setVisibility(View.VISIBLE);
                                holder.subreplyContent3.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent3);
                                break;
                        }
                    }
                }
            }else{
                holder.subreplyLayout.setVisibility(View.GONE);
            }
        }else if(position<top+hot){
            if(position==top+hot-1){
                holder.dividerText.setVisibility(View.VISIBLE);
            }
            ReplyCursorBean.DataBean.HotsBean currentHot=data.getHots().get(position-top);
            clipText=currentHot.getContent().getMessage();
            Glide.with(context).load(currentHot.getMember().getAvatar()+"@200w_200h_1e_1c.webp").apply(new RequestOptions().transform(new FitCenter(),new CircleCrop()).error(R.mipmap.ic_default_avatar).placeholder(holder.avatorView.getDrawable())).into(holder.avatorView);
            holder.nameView.setText(currentHot.getMember().getUname());
            holder.floorView.setText("#"+currentHot.getFloor());
            holder.dateView.setText(DateUtils.getDay(((long)currentHot.getCtime())*1000));
            holder.contentView.setText(currentHot.getContent().getMessage());
            holder.likeView.setText(currentHot.getLike()+"");
            if(currentHot.getReplies()!=null){
                holder.subreplyLayout.setVisibility(View.VISIBLE);
                if(currentHot.getReplies().size()>3){
                    for(int i=0;i<3;i++){
                        String linkString = currentHot.getReplies().get(i).getMember().getUname();
                        if(currentHot.getReplies().get(i).getContent().getMessage().startsWith("回复 ")){
                            linkString=linkString+"  "+currentHot.getReplies().get(i).getContent().getMessage();
                        }else{
                            linkString=linkString+":  "+currentHot.getReplies().get(i).getContent().getMessage();
                        }
                        switch (i){
                            case 0:
                                holder.subreplyContent1.setVisibility(View.VISIBLE);
                                holder.subreplyContent1.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent1);
                                break;
                            case 1:
                                holder.subreplyContent2.setVisibility(View.VISIBLE);
                                holder.subreplyContent2.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent2);
                                break;
                            case 2:
                                holder.subreplyContent3.setVisibility(View.VISIBLE);
                                holder.subreplyContent3.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent3);
                                break;
                        }
                    }
                    holder.subreplyAll.setText("共"+currentHot.getCount()+"条回复");
                    holder.subreplyAll.setVisibility(View.VISIBLE);
                }else{
                    for(int i=0;i<currentHot.getReplies().size();i++){
                        String linkString = currentHot.getReplies().get(i).getMember().getUname();
                        if(currentHot.getReplies().get(i).getContent().getMessage().startsWith("回复 ")){
                            linkString=linkString+"  "+currentHot.getReplies().get(i).getContent().getMessage();
                        }else{
                            linkString=linkString+":  "+currentHot.getReplies().get(i).getContent().getMessage();
                        }
                        switch (i){
                            case 0:
                                holder.subreplyContent1.setVisibility(View.VISIBLE);
                                holder.subreplyContent1.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent1);
                                break;
                            case 1:
                                holder.subreplyContent2.setVisibility(View.VISIBLE);
                                holder.subreplyContent2.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent2);
                                break;
                            case 2:
                                holder.subreplyContent3.setVisibility(View.VISIBLE);
                                holder.subreplyContent3.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent3);
                                break;
                        }
                    }
                }
            }else{
                holder.subreplyLayout.setVisibility(View.GONE);
            }
        }else{
            Glide.with(context).load(data.getReplies().get(position-top-hot).getMember().getAvatar()+"@200w_200h_1e_1c.webp").apply(new RequestOptions().transform(new FitCenter(),new CircleCrop()).error(R.mipmap.ic_default_avatar).placeholder(holder.avatorView.getDrawable())).into(holder.avatorView);
            holder.nameView.setText(data.getReplies().get(position-top-hot).getMember().getUname());
            holder.floorView.setText("#"+data.getReplies().get(position-top-hot).getFloor());
            holder.dateView.setText(DateUtils.getDay(((long)data.getReplies().get(position-top-hot).getCtime())*1000));
            holder.contentView.setText(data.getReplies().get(position-top-hot).getContent().getMessage());
            holder.likeView.setText(data.getReplies().get(position-top-hot).getLike()+"");
            ReplyCursorBean.DataBean.RepliesBeanXX currentReply=data.getReplies().get(position-top-hot);
            clipText=currentReply.getContent().getMessage();
            if(currentReply.getReplies()!=null){
                holder.subreplyLayout.setVisibility(View.VISIBLE);
                if(currentReply.getReplies().size()>3){
                    for(int i=0;i<3;i++){
                        String linkString = currentReply.getReplies().get(i).getMember().getUname();
                        if(currentReply.getReplies().get(i).getContent().getMessage().startsWith("回复 ")){
                            linkString=linkString+"  "+currentReply.getReplies().get(i).getContent().getMessage();
                        }else{
                            linkString=linkString+":  "+currentReply.getReplies().get(i).getContent().getMessage();
                        }
                        switch (i){
                            case 0:
                                holder.subreplyContent1.setVisibility(View.VISIBLE);
                                holder.subreplyContent1.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent1);
                                break;
                            case 1:
                                holder.subreplyContent2.setVisibility(View.VISIBLE);
                                holder.subreplyContent2.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent2);
                                break;
                            case 2:
                                holder.subreplyContent3.setVisibility(View.VISIBLE);
                                holder.subreplyContent3.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent3);
                                break;
                        }
                    }
                    holder.subreplyAll.setText("共"+currentReply.getCount()+"条回复");
                    holder.subreplyAll.setVisibility(View.VISIBLE);
                }else{
                    for(int i=0;i<currentReply.getReplies().size();i++){
                        String linkString = currentReply.getReplies().get(i).getMember().getUname();
                        if(currentReply.getReplies().get(i).getContent().getMessage().startsWith("回复 ")){
                            linkString=linkString+"  "+currentReply.getReplies().get(i).getContent().getMessage();
                        }else{
                            linkString=linkString+":  "+currentReply.getReplies().get(i).getContent().getMessage();
                        }
                        switch (i){
                            case 0:
                                holder.subreplyContent1.setVisibility(View.VISIBLE);
                                holder.subreplyContent1.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent1);
                                break;
                            case 1:
                                holder.subreplyContent2.setVisibility(View.VISIBLE);
                                holder.subreplyContent2.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent2);
                                break;
                            case 2:
                                holder.subreplyContent3.setVisibility(View.VISIBLE);
                                holder.subreplyContent3.setText(linkString);
                                SpanUtils.UrlSpanChange(holder.subreplyContent3);
                                break;
                        }
                    }
                }
            }else{
                holder.subreplyLayout.setVisibility(View.GONE);
            }
        }
        holder.replyLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager clipboardManager= (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setPrimaryClip(ClipData.newPlainText("reply",clipText));
                Toast.makeText(v.getContext(),"评论已复制",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if(data==null){
            return 0;
        }else{
            if(data.getHots()!=null) {
                hot = data.getHots().size();
            }
            if(data.getReplies()!=null){
                normal=data.getReplies().size();
            }if(data.getTop()!=null&&data.getTop().getUpper()!=null){
                top=1;
            }
            return hot+normal+top;
        }
    }


    class MyInnerViewHolder extends RecyclerView.ViewHolder{

        @Nullable
        @BindView(R.id.video_reply_avator)
        ImageView avatorView;
        @Nullable
        @BindView(R.id.video_reply_username)
        TextView nameView;
        @Nullable
        @BindView(R.id.video_reply_floor_num)
        TextView floorView;
        @Nullable
        @BindView(R.id.video_reply_date)
        TextView dateView;
        @Nullable
        @BindView(R.id.video_reply_content)
        TextView contentView;
        @Nullable
        @BindView(R.id.video_reply_like)
        TextView likeView;
        @Nullable
        @BindView(R.id.video_reply_subreply)
        ConstraintLayout subreplyLayout;
        @Nullable
        @BindView(R.id.subreply_content1)
        TextView subreplyContent1;
        @Nullable
        @BindView(R.id.subreply_content2)
        TextView subreplyContent2;
        @Nullable
        @BindView(R.id.subreply_content3)
        TextView subreplyContent3;
        @Nullable
        @BindView(R.id.subreply_all)
        TextView subreplyAll;
        @Nullable
        @BindView(R.id.reply_divider_text)
        TextView dividerText;
        @Nullable
        @BindView(R.id.reply_layout)
        ConstraintLayout replyLayout;




        public MyInnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
