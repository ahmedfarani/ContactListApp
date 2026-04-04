package com.example.contactslistapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.contactslistapp.databinding.ActivityEditContactBinding;

public class EditContactActivity extends AppCompatActivity {

    public static ActivityEditContactBinding editContactBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        editContactBinding = ActivityEditContactBinding.inflate(getLayoutInflater());
        setContentView(editContactBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editContactBinding.btnSave.setOnClickListener(view -> {
            String editName = editContactBinding.editTextName.getText().toString();
            String editPhone = editContactBinding.editTextPhone.getText().toString();
            String editEmail = editContactBinding.editTextEmail.getText().toString();

            if(editName.isEmpty()||editPhone.isEmpty()||editEmail.isEmpty()){
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent resultIntent = new Intent();
            resultIntent.putExtra("name", editName);
            resultIntent.putExtra("phone", editPhone);
            resultIntent.putExtra("email", editEmail);

            setResult(RESULT_OK, resultIntent);
            finish();
        });

    }
}