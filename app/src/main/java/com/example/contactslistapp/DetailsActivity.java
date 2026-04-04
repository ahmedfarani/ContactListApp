package com.example.contactslistapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.contactslistapp.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    private int position;

    public static ActivityDetailsBinding detailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        detailsBinding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(detailsBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        String email = getIntent().getStringExtra("email");
        int image = getIntent().getIntExtra("image", 0);

        detailsBinding.detailName.setText(name);
        detailsBinding.detailPhone.setText(phone);
        detailsBinding.detailEmail.setText(email);
        detailsBinding.detailImage.setImageResource(image);

        detailsBinding.detailEmail.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Hello " + name);
            startActivity(Intent.createChooser(intent, "Send email via..."));
        });

        detailsBinding.btnCall.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phone));
            startActivity(intent);
        });

        detailsBinding.btnSms.setOnClickListener(view -> {
            String smsPhone = detailsBinding.detailPhone.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("sms:" + smsPhone));
            startActivity(intent);
        });

        detailsBinding.btnShare.setOnClickListener(view -> {
            String shareName = detailsBinding.detailName.getText().toString();
            String sharePhone = detailsBinding.detailPhone.getText().toString();
            String shareEmail = detailsBinding.detailEmail.getText().toString();

            String shareBody = "Contact Info:\nName: " + shareName + "\nPhone: " + sharePhone + "\nEmail: " + shareEmail;

            ShareCompat.IntentBuilder
                    .from(this)
                    .setType("text/plain")
                    .setChooserTitle("Share Via: ")
                    .setText(shareBody)
                    .startChooser();
        });

        detailsBinding.btnDelete.setOnClickListener(v -> {
            showDeleteDialog();
        });

        detailsBinding.btnEdit.setOnClickListener(view ->{
            Intent intent = new Intent(this, EditContactActivity.class);

            intent.putExtra("position", position);
            intent.putExtra("name", detailsBinding.detailName.getText().toString());
            intent.putExtra("phone", detailsBinding.detailPhone.getText().toString());
            intent.putExtra("email", detailsBinding.detailEmail.getText().toString());

            startActivityForResult(intent, 300);
        });

        position = getIntent().getIntExtra("position", -1);
        Toast.makeText(this, "Position: " + position, Toast.LENGTH_SHORT).show();

    }

    void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
        builder.setTitle("Delete Contact!");
        builder.setMessage("Are you sure you want to delete this contact?");

        builder.setPositiveButton("Yes", (dialog, which) -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("deletedPosition", position);
            setResult(RESULT_OK, resultIntent);
            Toast.makeText(this, "The contact was deleted", Toast.LENGTH_SHORT).show();
            finish();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            Toast.makeText(this, "Delete Cancelled!", Toast.LENGTH_SHORT).show();
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 300 && resultCode == RESULT_OK && data != null) {

            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");
            String email = data.getStringExtra("email");

            detailsBinding.detailName.setText(name);
            detailsBinding.detailPhone.setText(phone);
            detailsBinding.detailEmail.setText(email);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("name", name);
            resultIntent.putExtra("phone", phone);
            resultIntent.putExtra("email", email);
            resultIntent.putExtra("position", position);

            setResult(RESULT_OK, resultIntent);
        }
    }
}

