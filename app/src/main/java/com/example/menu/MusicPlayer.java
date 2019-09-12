package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

public class MusicPlayer extends AppCompatActivity {
MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        setTitle("Music Player");

        player = MediaPlayer.create(getApplicationContext(), R.raw.amesfluid);
    }
}
