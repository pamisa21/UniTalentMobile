package com.example.unitalentv3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtsAdapter extends RecyclerView.Adapter<ArtsAdapter.ViewHolder> {

    private Context mContext;
    private List<Upload> mUploads;

    public ArtsAdapter(Context context, List<Upload> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.arts_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Upload uploadcurrent = mUploads.get(position);
        holder.textViewTitle.setText(uploadcurrent.getUtitle());
        holder.textViewArtist.setText("by " + uploadcurrent.getUname());
        Picasso.get()
                .load(uploadcurrent.getUimageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.artsImage);
    }


    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView artsImage;
        TextView textViewTitle;
        TextView textViewArtist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            artsImage = itemView.findViewById(R.id.imageViewArts);
            textViewTitle = itemView.findViewById(R.id.txtArtTitle);
            textViewArtist = itemView.findViewById(R.id.txtArtistName);
        }
    }
}
