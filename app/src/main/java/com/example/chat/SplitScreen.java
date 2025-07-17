package com.example.chat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;

public class SplitScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_split_screen);

      //  ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.splash), (v, insets) -> {
       //     Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            new Handler().postDelayed(() -> {
            SharedPreferences pref=getSharedPreferences("login",MODE_PRIVATE);
            Boolean check=pref.getBoolean("flag",false);
            Intent intent;
            if(check){
                intent= new  Intent(SplitScreen.this, MainActivity.class);
            }else{
               intent=  new Intent(SplitScreen.this, login.class);
            }
            startActivity(intent);
finish();
        },5000);


       //     return insets;
        }//);
    }
//}