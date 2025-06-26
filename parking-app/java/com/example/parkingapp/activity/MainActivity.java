package com.example.parkingapp.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parkingapp.R;
import com.example.parkingapp.fragment.EntryFragment;
import com.example.parkingapp.fragment.ExitFragment;
import com.example.parkingapp.fragment.SlotListFragment;

public class MainActivity extends AppCompatActivity {

    private Button btnEntry, btnExit, btnSlots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Default load EntryFragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, new EntryFragment())
                .commit();

        // Inisialisasi tombol
        btnEntry = findViewById(R.id.btnEntry);
        btnExit = findViewById(R.id.btnExit);
        btnSlots = findViewById(R.id.btnSlots);

        // Navigasi ke EntryFragment
        btnEntry.setOnClickListener(v -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new EntryFragment())
                    .commit();
        });

        // Navigasi ke ExitFragment
        btnExit.setOnClickListener(v -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new ExitFragment())
                    .commit();
        });

        // Navigasi ke SlotListFragment
        btnSlots.setOnClickListener(v -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new SlotListFragment())
                    .commit();
        });
    }
}
