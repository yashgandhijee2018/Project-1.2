package com.demo.incampus.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.incampus.Model.Home;
import com.demo.incampus.R;

import java.util.List;

public class HomeAdapter extends  RecyclerView.Adapter<HomeAdapter.HomeViewHolder>{

    private Context mCtx;
    private List<Home> homeList;

    public HomeAdapter(Context mCtx, List<Home> homeList) {
        this.mCtx = mCtx;
        this.homeList = homeList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cardlayout_home,null);
        HomeViewHolder holder = new HomeViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Home home = homeList.get(position);

        holder.topic.setText(home.getTopic());
        holder.name.setText(home.getName());
        holder.time.setText(home.getTime());
        holder.content.setText(home.getContent());
        holder.messages.setText(home.getMessages());
        holder.hearts.setText(home.getHearts());
        holder.profileImage.setImageDrawable(mCtx.getResources().getDrawable(home.getProfilePhoto()));
    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        ImageView profileImage;
        TextView topic, name, time, content, messages, hearts;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profilephoto);
            topic = itemView.findViewById(R.id.topic);
            name = itemView.findViewById(R.id.user_profile_name);
            time = itemView.findViewById(R.id.time);
            content = itemView.findViewById(R.id.content);
            messages = itemView.findViewById(R.id.nmessages);
            hearts = itemView.findViewById(R.id.nhearts);
        }
    }
}
