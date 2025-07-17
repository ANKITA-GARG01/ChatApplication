package com.example.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerChatAdapter extends RecyclerView.Adapter<RecyclerChatAdapter.ViewHolder> {
    private Context c;
    private List<chatmodel> chatmodelList;
    private OnChatClickListener listener;

    public RecyclerChatAdapter(Context c, List<chatmodel> chatmodelList, OnChatClickListener listener) {
        this.c = c;
        this.chatmodelList = chatmodelList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.chat_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        chatmodel model = chatmodelList.get(position);
        holder.name.setText(model.name);
        holder.number.setText(model.number);
        holder.image.setImageResource(model.image);

        holder.card.setOnClickListener(v -> listener.onChatClick());
        // or: holder.itemView.setOnClickListener(v -> listener.onChatClick());
    }

    @Override
    public int getItemCount() {
        return chatmodelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, number;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.contactimage);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.num);
            card = itemView.findViewById(R.id.card1);
        }
    }

    public interface OnChatClickListener {
        void onChatClick();
    }
}
