package com.example.chat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chat.databinding.ActivityLoginBinding;
import com.example.chat.databinding.ActivityRegisterAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterAccount extends AppCompatActivity {

    private ActivityRegisterAccountBinding binding;

    FirebaseAuth acc = FirebaseAuth.getInstance();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityRegisterAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       // EdgeToEdge.enable(this);
       // setContentView(R.layout.activity_register_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register_account), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.Signupbutton.setOnClickListener(view->{

            if(binding.regUserName.getText().toString().trim().isEmpty() || binding.regEmail.getText().toString().trim().isEmpty()||binding.regPassword.getText().toString().trim().isEmpty()||binding.regRePassword.getText().toString().trim().isEmpty()) {

                Toast.makeText(RegisterAccount.this, "fill all the details", Toast.LENGTH_SHORT).show();

            } else if ( !binding.regPassword.getText().toString().equals(binding.regRePassword.getText().toString())) {
                Toast.makeText(RegisterAccount.this, "Re-entered password is different", Toast.LENGTH_SHORT).show();}
//             else if (binding.regEmail.) {
//
//            }
            else {
                createAccount(binding.regEmail.toString(), binding.regPassword.toString());
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       Intent iregister =new Intent(RegisterAccount.this, MainActivity.class);
                       startActivity(iregister);
                       finish();
                   }
               },3000);

            }
        });



    }

    private void createAccount(String Email,String pass) {
        Email = Objects.requireNonNull(binding.regEmail.getText()).toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            binding.regEmail.setError("Enter a valid email address");
            return; // stop execution
        }

        String finalEmail = Email;
        acc.createUserWithEmailAndPassword(Email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isComplete()){
                    Log.d("Register", "Email: " + finalEmail);

                    Toast.makeText(RegisterAccount.this, "Account Created Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegisterAccount.this, task.getException().toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}