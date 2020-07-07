package com.example.tetris;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tetris.Game.Game;


public class MainActivity extends AppCompatActivity {

    private ImageButton SettingButton ;
    private ImageButton RightArrow ;
    private ImageButton LeaderButton ;
    private ImageButton NewGameButton ;
    private ImageButton LoadGameButton;
    private ImageButton LeftArrow ;
    public TextView Nickname ;
    private Button profile ;
    public MediaPlayer Song ;
    public boolean Started = true ;
    static int musiccheck = 0;
    MediaPlayer mediaPlayer ;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate (savedInstanceState);
        musiccheck++;
        setContentView (R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this ,R.raw.cinematic);
        profile = (Button)findViewById (R.id.profile);
        LeftArrow= (ImageButton) findViewById (R.id.LeftArrow);
        RightArrow= (ImageButton) findViewById (R.id.RightArrow);
        SettingButton = (ImageButton)findViewById (R.id.settings);
        LeaderButton = (ImageButton)findViewById (R.id.LeaderButton);
        NewGameButton = (ImageButton)findViewById (R.id.NewGameButton);
        LoadGameButton=(ImageButton)findViewById (R.id.LoadGameButton);
        Nickname = (TextView)findViewById (R.id.username);
        //music

        if (musiccheck== 1) {
            mediaPlayer.setLooping (true);
            mediaPlayer.start ();

        }
        //nickname first edit
        SharedPreferences prefs = getSharedPreferences ("prefs " , MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean ("firstStart" , true);
        if(firstStart){
            showStartDialog ();
        }
        // game_board activity
        NewGameButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), Game.class);
                startActivity(intent);
            }
        });


        LoadGameButton.setEnabled (false);
        LoadGameButton.setVisibility (View.INVISIBLE);
        LeftArrow.setEnabled (false);
        LeftArrow.setVisibility (View.INVISIBLE);

        //Setting View
        SettingButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                OpenSettingLayout() ;}
        });
        //Leader Board view
        LeaderButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                OpenLeaderLayout();
            }
        });
        //changing name
        profile.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Changename ();
            }
        });
        //Load Game Button
        RightArrow.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                RightArrow.setVisibility (View.INVISIBLE);
                NewGameButton.setEnabled (false);
                NewGameButton.setVisibility (View.INVISIBLE);
                LoadGameButton.setEnabled (true);
                LoadGameButton.setVisibility (View.VISIBLE);
                LeftArrow.setEnabled (true);
                LeftArrow.setVisibility (View.VISIBLE);
                LeftArrow.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        NewGameButton.setEnabled (true);
                        NewGameButton.setVisibility (View.VISIBLE);
                        LoadGameButton.setEnabled (false);
                        LoadGameButton.setVisibility (View.INVISIBLE);
                        LeftArrow.setEnabled (false);
                        LeftArrow.setVisibility (View.INVISIBLE);
                        RightArrow.setEnabled (true);
                        RightArrow.setVisibility (View.VISIBLE);
                    }
                });

            }
        });
    }


    //Leader View Method
    public void OpenLeaderLayout() {
        Intent intent1 = new Intent (this , LeaderBoard.class );
        startActivity (intent1);
    }


    //Setting View Method
    public void OpenSettingLayout() {

        Intent intent = new Intent (this,Settings.class);
        startActivity (intent);

    }

    //changing nickname
    public void Changename () {

        View v = LayoutInflater.from (MainActivity.this).inflate(R.layout.dialog_username , null);
        final EditText input = v.findViewById (R.id.username);
        new AlertDialog.Builder (this)
                .setView (v)
                .setPositiveButton ("OK", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(input.getText ().toString ().equals ("")){
                            return;
                        }
                        else
                            Nickname.setText (input.getText ().toString ());
                        dialog.dismiss ();
                    }
                }).show ();
    }


    //nickname dialog Method
    private void showStartDialog() {

        View v = LayoutInflater.from (MainActivity.this).inflate(R.layout.dialog_username , null);
        final EditText input = v.findViewById (R.id.username);
        new AlertDialog.Builder (this)
                .setView (v)
                .setPositiveButton ("OK", new DialogInterface.OnClickListener () {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(input.getText ().toString ().equals ("")){
                            return;
                        }
                        else
                            Nickname.setText (input.getText ().toString ());
                        dialog.dismiss ();
                    }
                }).show ();

        SharedPreferences prefs = getSharedPreferences ("prefs " , MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit ();
        editor.putBoolean ("firstStart" , false) ;
        editor.apply ();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }
}