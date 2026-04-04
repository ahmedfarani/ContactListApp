package com.example.contactslistapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.contactslistapp.databinding.ActivityAddContactBinding;

public class AddContactActivity extends AppCompatActivity {

    public static ActivityAddContactBinding addContactBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        addContactBinding = ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(addContactBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addContactBinding.btnAdd.setOnClickListener(view -> {
            String addName = addContactBinding.addTextName.getText().toString();
            String addPhone = addContactBinding.addTextPhone.getText().toString();
            String addEmail = addContactBinding.addTextEmail.getText().toString();

            if(addName.isEmpty()||addPhone.isEmpty()||addEmail.isEmpty()){
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent resultIntent = new Intent();
            resultIntent.putExtra("name", addName);
            resultIntent.putExtra("phone", addPhone);
            resultIntent.putExtra("email", addEmail);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}