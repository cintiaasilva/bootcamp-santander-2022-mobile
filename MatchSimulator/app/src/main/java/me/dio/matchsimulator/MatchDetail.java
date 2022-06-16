package me.dio.matchsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import me.dio.matchsimulator.databinding.ActivityMatchDetailBinding;

public class MatchDetail extends AppCompatActivity {

    private ActivityMatchDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMatchDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true); // aparece a seta de voltar no action bar
    }
}