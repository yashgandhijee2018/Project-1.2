package com.demo.incampus.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.incampus.Model.Explore;
import com.demo.incampus.R;

import java.util.List;

public class ExploreAdapter extends  RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder>{

    Context context;
    List<Explore> exploreList;

    public ExploreAdapter(Context context, List<Explore> exploreList) {
        this.context = context;
        this.exploreList = exploreList;
    }

    @NonNull
    @Override
    public ExploreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardlayout_explore,null);
        ExploreViewHolder holder = new ExploreViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreViewHolder holder, int position) {
        Explore explore = exploreList.get(position);

        holder.name.setText(explore.getName());
        holder.profile_photo.setImageDrawable(context.getResources().getDrawable(explore.getProfile_photo()));
    }

    @Override
    public int getItemCount() {
        return exploreList.size();
    }

    class ExploreViewHolder extends RecyclerView.ViewHolder {

        ImageView profile_photo;
        TextView name;

        public ExploreViewHolder(@NonNull View itemView) {
            super(itemView);

            profile_photo = itemView.findViewById(R.id.profile_photo);
            name = itemView.findViewById(R.id.name);
        }
    }
}
