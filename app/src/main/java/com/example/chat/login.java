package com.example.chat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chat.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
FirebaseAuth acc=FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
       // EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

        Intent ilogin = new Intent(this, MainActivity.class);
            binding.button.setOnClickListener(
                    view -> {
                        if (binding.editText.getText().toString().trim().isEmpty() || binding.Email.getText().toString().trim().isEmpty() || binding.Password.getText().toString().trim().isEmpty()) {
                            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();

                        } else {
                            //if user exists
                            acc.signInWithEmailAndPassword(binding.Email.getText().toString(), binding.Password.getText().toString())
                                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                                SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                                                SharedPreferences.Editor editor = pref.edit();
                                                editor.putBoolean("flag", true);
                                                editor.apply();
                                                editor.commit();
                                                ilogin.putExtra("Username", binding.editText.getText().toString());
                                                ilogin.putExtra("EmailAddress", binding.Email.getText().toString());
                                                ilogin.putExtra("Password", binding.Password.getText().toString());
                                                startActivity(ilogin);
                                                finish();
                                            } else {
                                                Toast.makeText(login.this, task.getException().toString(), Toast.LENGTH_SHORT).show();

                                            }


                                        }
                                    });
                            binding.newaccount.setOnClickListener(v1 -> {
                                Intent inew = new Intent(this, RegisterAccount.class);
                                startActivity(inew);
                            });


                        }
                    });
                    return insets;
        });
        }}