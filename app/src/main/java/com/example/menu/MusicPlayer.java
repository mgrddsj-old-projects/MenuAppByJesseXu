package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MusicPlayer extends AppCompatActivity
{
public static MediaPlayer player;
public ArrayList<Integer> songList;
public int songNum;
public SeekBar seekBar;
public Handler handler;
public Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        setTitle("Music Player");

        handler = new Handler();

        songNum = 0;
        songList = new ArrayList<>();
        songList.add(R.raw.in_time);
        songList.add(R.raw.undertale_hotel);
        songList.add(R.raw.ames_fluid);

        player = MediaPlayer.create(getApplicationContext(), songList.get(songNum));
        seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /*
                void onProgressChanged (SeekBar seekBar, int progress, boolean fromUser)
                    Notification that the progress level has changed. Clients can use the fromUser
                    parameter to distinguish user-initiated changes from those that occurred programmatically.

                Parameters
                    seekBar SeekBar : The SeekBar whose progress has changed
                    progress int : The current progress level. This will be in the range min..max
                                   where min and max were set by setMin(int) and setMax(int),
                                   respectively. (The default values for min is 0 and max is 100.)
                    fromUser boolean : True if the progress change was initiated by the user.
            */
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(player!=null && b){
                    /*
                        void seekTo (int msec)
                            Seeks to specified time position. Same as seekTo(long, int)
                            with mode = SEEK_PREVIOUS_SYNC.

                        Parameters
                            msec int: the offset in milliseconds from the start to seek to

                        Throws
                            IllegalStateException : if the internal player engine has not been initialized
                    */
                    player.seekTo(i*1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void play(View view)
    {
        player.start();
        initializeSeekBar();
    }

    public void pause(View view)
    {
        player.pause();
    }

    public void skip(View view)
    {
        songNum++;
        if (songNum >= songList.size())
        {
            songNum = 0;
        }
        player.release();
        player = MediaPlayer.create(getApplicationContext(), songList.get(songNum));
        player.start();
    }

    protected void initializeSeekBar(){
        seekBar.setMax(player.getDuration()/1000);

        runnable = new Runnable() {
            @Override
            public void run() {
                if(player!=null){
                    int mCurrentPosition = player.getCurrentPosition()/1000; // In milliseconds
                    seekBar.setProgress(mCurrentPosition);
                    getAudioStats();
                }
                handler.postDelayed(runnable,1000);
            }
        };
        handler.postDelayed(runnable,1000);
    }

    protected void getAudioStats(){
        int duration  = player.getDuration()/1000; // In milliseconds
        int due = (player.getDuration() - player.getCurrentPosition())/1000;
        int pass = duration - due;
    }
}
