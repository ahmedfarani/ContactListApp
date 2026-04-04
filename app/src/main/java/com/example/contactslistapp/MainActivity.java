package com.example.contactslistapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.contactslistapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ActivityMainBinding mainBinding;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String LAYOUT_KEY = "layout_pos";
    private ContactAdapter adapter;
    private ArrayList<Contact> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedPos = prefs.getInt(LAYOUT_KEY, 0);

        String[] options = {"Linear View", "Grid View"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, options);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mainBinding.spinner.setAdapter(adapter);

        mainBinding.spinner.setSelection(savedPos);
        updateRecyclerViewLayout(savedPos);

        mainBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                prefs.edit().putInt(LAYOUT_KEY, position).apply();
                updateRecyclerViewLayout(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loadContacts();
        setupRecycler();

        mainBinding.floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddContactActivity.class);
            startActivityForResult(intent, 200);
        });
    }

    private void updateRecyclerViewLayout(int position) {
        if (position == 0) {
            mainBinding.recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        } else {
            mainBinding.recyclerViewContacts.setLayoutManager(new GridLayoutManager(this, 2));
        }
    }

    private void loadContacts() {
        contactList = new ArrayList<>();
        contactList.add(new Contact("Ahmad", "0599001122", "ahmad@gmail.com", R.drawable.ic_action_name));
        contactList.add(new Contact("Sara", "0597765432", "sara@gmail.com", R.drawable.ic_action_name));
        contactList.add(new Contact("Omar", "0591234567", "omar@gmail.com", R.drawable.ic_action_name));
        contactList.add(new Contact("Khalil", "0591234567", "Khalil@gmail.com", R.drawable.ic_action_name));
        contactList.add(new Contact("Ata", "0591234567", "Ata@gmail.com", R.drawable.ic_action_name));
        contactList.add(new Contact("Mohammed", "0591234567", "Mohammed@gmail.com", R.drawable.ic_action_name));
        contactList.add(new Contact("Kareem", "0591234567", "Kareem@gmail.com", R.drawable.ic_action_name));
        contactList.add(new Contact("Kamal", "0591234567", "Kamal@gmail.com", R.drawable.ic_action_name));
        contactList.add(new Contact("Zainab", "0591234567", "Zainab@gmail.com", R.drawable.ic_action_name));
        contactList.add(new Contact("Samar", "0591234567", "Samar@gmail.com", R.drawable.ic_action_name));
        contactList.add(new Contact("Basel", "0591234567", "Basel@gmail.com", R.drawable.ic_action_name));
        contactList.add(new Contact("Alaa", "0591234567", "Alaa@gmail.com", R.drawable.ic_action_name));
        contactList.add(new Contact("Dima", "0591234567", "Dima@gmail.com", R.drawable.ic_action_name));
    }

    private void setupRecycler() {
        adapter = new ContactAdapter(this, contactList);
        mainBinding.recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.recyclerViewContacts.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            if (data.hasExtra("deletedPosition")) {
                int pos = data.getIntExtra("deletedPosition", -1);

                if (pos != -1) {
                    contactList.remove(pos);
                    adapter.notifyItemRemoved(pos);
                    adapter.notifyItemRangeChanged(pos, contactList.size());
                }
            }
        } else if (requestCode == 200 && resultCode == RESULT_OK && data != null) {

            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");
            String email = data.getStringExtra("email");

            Contact newContact = new Contact(
                    name,
                    phone,
                    email,
                    R.drawable.ic_action_name
            );

            contactList.add(newContact);
            adapter.notifyItemInserted(contactList.size() - 1);
        } else if (requestCode == 300 && resultCode == RESULT_OK && data != null) {

            int pos = data.getIntExtra("position", -1);

            if (pos != -1) {
                String name = data.getStringExtra("name");
                String phone = data.getStringExtra("phone");
                String email = data.getStringExtra("email");

                Contact contact = contactList.get(pos);

                contact.setName(name);
                contact.setPhoneNumber(phone);
                contact.setEmail(email);

                adapter.notifyItemChanged(pos);
            }
        }
    }
}