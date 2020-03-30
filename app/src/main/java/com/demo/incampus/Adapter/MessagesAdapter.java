package com.demo.incampus.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.incampus.Model.Messages;
import com.demo.incampus.R;

import java.util.List;

public class MessagesAdapter extends  RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>{

    private Context mCtx;
    private List<Messages> messageList;

    public MessagesAdapter(Context mCtx, List<Messages> messageList) {
        this.mCtx = mCtx;
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cardlayout_messages,null);
        MessageViewHolder holder = new MessageViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Messages messages = messageList.get(position);

        holder.profile_name__.setText(messages.getProfile_name__());
        holder.message_.setText(messages.getMessage_());
        holder.message_time_.setText(messages.getMessage_time_());
        holder.profile_photo_.setImageDrawable(mCtx.getResources().getDrawable(messages.getProfile_photo_()));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {

        ImageView profile_photo_;
        TextView profile_name__, message_, message_time_;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            profile_photo_ = itemView.findViewById(R.id.profile_photo_);
            profile_name__ = itemView.findViewById(R.id.profile_name__);
            message_ = itemView.findViewById(R.id.message_);
            message_time_ = itemView.findViewById(R.id.message_time_);

        }
    }
}

