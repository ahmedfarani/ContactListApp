package com.example.contactslistapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactslistapp.databinding.ContactItemBinding;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.contactViewHolder> {
    private Context context;
    private final ArrayList<Contact> contactList;

    public ContactAdapter(Context context, ArrayList<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }


    @NonNull
    @Override
    public contactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactItemBinding itemBinding = ContactItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new contactViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull contactViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        holder.itemBinding.textName.setText(contact.getName());
        holder.itemBinding.textPhone.setText(contact.getPhoneNumber());
        holder.itemBinding.textEmail.setText(contact.getEmail());
        holder.itemBinding.detailImage.setImageResource(contact.getImageResource());

        holder.itemBinding.getRoot().setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("name", contact.getName());
            intent.putExtra("phone", contact.getPhoneNumber());
            intent.putExtra("email", contact.getEmail());
            intent.putExtra("image", contact.getImageResource());
            intent.putExtra("position", position);
            ((Activity) context).startActivityForResult(intent, 100);
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class contactViewHolder extends RecyclerView.ViewHolder {
        private final ContactItemBinding itemBinding;

        public contactViewHolder(ContactItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
