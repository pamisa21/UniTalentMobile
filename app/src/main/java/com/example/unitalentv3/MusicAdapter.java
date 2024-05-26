package com.example.unitalentv3;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    MusicData[] musicData;
    fragment3 context;

    public MusicAdapter(MusicData[] musicData, fragment3 activity) {
        this.musicData = musicData;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.music_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MusicData musicDataList = musicData[position];
        holder.textMusicTitle.setText(musicDataList.getMusicTitle());
        holder.textMusicArtist.setText(musicDataList.getMusicArtist());
        holder.musicImage.setImageResource(musicDataList.getMusicImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getContext(), musicDataList.getMusicTitle(), Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(context.getContext(), R.style.CustomDialogTheme);
                View dialogView = LayoutInflater.from(context.getContext()).inflate(R.layout.dialog_music_player, null);
                builder.setView(dialogView);

                ImageView dialogImage = dialogView.findViewById(R.id.ivAlbumArt);
                dialogImage.setImageResource(musicDataList.getMusicImage());

                TextView dialogTitle = dialogView.findViewById(R.id.tvSongTitle);
                dialogTitle.setText(musicDataList.getMusicTitle());

                TextView dialogArtist = dialogView.findViewById(R.id.tvSongArtist);
                dialogArtist.setText(musicDataList.getMusicArtist());

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle OK button click if needed
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle Cancel button click if needed
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView musicImage;
        TextView textMusicTitle;
        TextView textMusicArtist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            musicImage = itemView.findViewById(R.id.imageViewMusic);
            textMusicTitle = itemView.findViewById(R.id.txtMusicTitle);
            textMusicArtist = itemView.findViewById(R.id.txtMusicArtist);
        }
    }
}



