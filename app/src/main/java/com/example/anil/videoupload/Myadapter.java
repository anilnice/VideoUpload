package com.example.anil.videoupload;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by Anil on 3/9/2018.
 */

class Myadapter extends RecyclerView.Adapter<Myadapter.Viewholder> {
    private List<SingleData> mydata;
    Context ct;
    public Myadapter(DisplayVideos displayVideos, List<SingleData> videodata) {
        this.ct=displayVideos;
        this.mydata=videodata;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        String path=mydata.get(position).getVideo_path();
        holder.tv.setText(Html.fromHtml("<b><a href='" + path + "'>" + mydata.get(position).getVideo_name() + "</a></b>"));
        holder.tv.setMovementMethod(LinkMovementMethod.getInstance());
        Picasso.with(ct).load(R.drawable.giphy).into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return mydata.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv;
        //CardView card;
        public Viewholder(View itemView) {
            super(itemView);
            iv=(ImageView)itemView.findViewById(R.id.iv);
            tv=(TextView)itemView.findViewById(R.id.tv);
            //card=(CardView)itemView.findViewById(R.id.card);

        }
    }

}
