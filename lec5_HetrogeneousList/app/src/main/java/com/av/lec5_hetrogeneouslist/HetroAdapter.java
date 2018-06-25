package com.av.lec5_hetrogeneouslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ankit on 25-06-2018.
 */

public class HetroAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Object> myArrayList;
    Context ctx;

    public HetroAdapter(ArrayList<Object> al, Context ctx) {
        this.myArrayList = al;
        this.ctx = ctx;
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        Object curr = myArrayList.get(position);
        if (curr instanceof TextMessage) {
            return 0;
        } else if (curr instanceof ImageMessage) {
            return 1;
        } else
            return 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return null;
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        if (viewType == 0) {
            View v = layoutInflater.inflate(R.layout.item_text, parent, false);
            return new TextViewHolder(v);
        } else if (viewType == 1) {
            View v = layoutInflater.inflate(R.layout.item_image, parent, false);
            return new ImageViewHolder(v);
        } else
            return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int id) {
        Object curr = myArrayList.get(id);
        if (getItemViewType(id) == 0) {
            TextViewHolder th = (TextViewHolder) holder;
            th.tv.setText(((TextMessage) curr).text);
        } else if (getItemViewType(id) == 1) {
            ImageViewHolder ih = (ImageViewHolder) holder;
            ImageMessage im = (ImageMessage) curr;
            Picasso.get()
                    .load(im.getURL())
                    .noFade()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ih.image);
//            Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(ih.image);
        }
    }

    @Override
    public int getItemCount() {
        return myArrayList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ImageViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img);
        }
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public TextViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.txt);
        }
    }

}
