package com.example.constraint_layout_example;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    EditText user;
    EditText pass;
    Button btn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sharedPreferences = getSharedPreferences("myData",MODE_PRIVATE);
        String uname = sharedPreferences.getString("username","");
        intent = new Intent(this,validate.class);
        if(!uname.isEmpty()){
            startActivity(intent);
            finish();
        }
        user = findViewById(R.id.uname);
        pass = findViewById(R.id.pass);
        btn = findViewById(R.id.btn);
        editor = sharedPreferences.edit();
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String name = user.getText().toString();
                String password = pass.getText().toString();
                if(name.isEmpty()){
                    Toast.makeText(MainActivity.this, "Empty Name", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Empty Password", Toast.LENGTH_SHORT).show();
                } else if (name.equals("admin") && password.equals("admin")) {
                    editor.putString("username",name);
                    editor.putString("password",password);
                    editor.commit();
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Username or Password is Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}