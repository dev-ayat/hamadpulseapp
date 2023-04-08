package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.models.photoBitmapModel;

import java.util.ArrayList;


public class PhotoBitmapAdapter extends RecyclerView.Adapter<PhotoBitmapAdapter.ViewHolder> {
    Context context;
    ArrayList<photoBitmapModel> bmp_imageslist;


    public PhotoBitmapAdapter(Context context, ArrayList<photoBitmapModel> bmp_imageslist) {
        this.context = context;
        this.bmp_imageslist = bmp_imageslist;
    }

    @NonNull
    @Override
    public PhotoBitmapAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.photo_copy_row, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoBitmapAdapter.ViewHolder holder, final int position) {
        holder.img.setImageBitmap(bmp_imageslist.get(position).getBitmap());

    }

    @Override
    public int getItemCount() {
        return bmp_imageslist.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);

        }
    }

}
