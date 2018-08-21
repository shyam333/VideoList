package com.abiramiaudio.videolist;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayerActivity extends YouTubeFailureRecoveryActivity{

    private YouTubePlayerView playerView;
    private YouTubePlayer player;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.youtube_player);
       // baseLayout = (LinearLayout) findViewById(R.id.layout);
        playerView = (YouTubePlayerView) findViewById(R.id.player);
       // fullscreenButton = (Button) findViewById(R.id.fullscreen_button);
       // otherViews = findViewById(R.id.other_views);

        // You can use your own button to switch to fullscreen too

//        Intent intent = getIntent();
//        id = intent.getStringExtra("KEY");

        playerView.initialize(DeveloperKey.DEVELOPER_KEY, this);

    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return playerView;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {

        this.player = youTubePlayer;
        // Specify that we want to handle fullscreen behavior ourselves.
        player.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
        //   player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        //  player.setOnFullscreenListener(this);
        if (!wasRestored) {
            SharedPreferences prefs = getSharedPreferences("MyKey", MODE_PRIVATE);
            id=prefs.getString("key", null);
          //  player.cueVideo(id);
            player.loadVideo(id);
        }
    }
}
