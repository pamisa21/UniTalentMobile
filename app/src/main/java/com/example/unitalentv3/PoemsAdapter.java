package com.example.unitalentv3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PoemsAdapter extends RecyclerView.Adapter<PoemsAdapter.ViewHolder> {

    private Context mContext;
    private List<UploadPoem> mUploads;

    public PoemsAdapter(Context context, List<UploadPoem> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.poems_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UploadPoem uploadcurrent = mUploads.get(position);

        holder.textPoemTitle.setText(uploadcurrent.getUtitle());
        holder.textPoemAuthor.setText("by " + uploadcurrent.getUname());
        holder.textPoemDescription.setText(uploadcurrent.getUPoem());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Opening '" + uploadcurrent.getUtitle() + "'", Toast.LENGTH_SHORT).show();

                // Pass the data to the ViewPoem activity
                Intent go_intent = new Intent(view.getContext(), ViewPoem.class);
                go_intent.putExtra("title", uploadcurrent.getUtitle());
                go_intent.putExtra("author", uploadcurrent.getUname());
                go_intent.putExtra("poem", uploadcurrent.getUPoem());

                view.getContext().startActivity(go_intent);

                // Override transition animations
                ((Activity) view.getContext()).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textPoemTitle;
        TextView textPoemAuthor;
        TextView textPoemDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textPoemTitle = itemView.findViewById(R.id.txtPoemTitle);
            textPoemAuthor = itemView.findViewById(R.id.txtPoemAuthor);
            textPoemDescription = itemView.findViewById(R.id.txtPoemDesc);
        }
    }
}
