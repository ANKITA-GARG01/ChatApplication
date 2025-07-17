package com.example.chat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerChatAdapter.OnChatClickListener {

    ArrayList<chatmodel> chatmodelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Logout button
        Button logout = findViewById(R.id.logoutbutton);
        logout.setOnClickListener(v1 -> {
            SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
            pref.edit().putBoolean("flag", false).apply();
            startActivity(new Intent(MainActivity.this, login.class));
            finish();
        });

        // Setup RecyclerView
        RecyclerView rv = findViewById(R.id.recyclechat);
        rv.setLayoutManager(new LinearLayoutManager(this));

        RecyclerChatAdapter adapter = new RecyclerChatAdapter(this, chatmodelList, this);
        rv.setAdapter(adapter);

        // Add dummy data
        for (int i = 0; i < 18; i++) {
            chatmodelList.add(new chatmodel(R.drawable.ic_launcher_background, "Contact " + (i+1), "62575488826"));
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onChatClick() {
        // Make fragment container visible if hidden
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ChatFragment())
                .addToBackStack(null)
                .commit();
    }
}



