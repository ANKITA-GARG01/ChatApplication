package com.example.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {
    private List<MessageModel> messageList = new ArrayList<>();
    private MessageAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.messages_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(adapter);

        // Example: prepopulate messages
        messageList.add(new MessageModel("Hi there!", false));
        messageList.add(new MessageModel("Hello! How are you?", true));
        adapter.notifyDataSetChanged();

        // Handle send
        EditText messageInput = view.findViewById(R.id.messageInput);
        ImageButton sendButton = view.findViewById(R.id.send);
        sendButton.setOnClickListener(v -> {
            String text = messageInput.getText().toString().trim();
            if (!text.isEmpty()) {
                messageList.add(new MessageModel(text, true));
                adapter.notifyItemInserted(messageList.size() - 1);
                messageInput.setText("");
                recyclerView.scrollToPosition(messageList.size() - 1);
            }
        });

        return view;
    }
}
