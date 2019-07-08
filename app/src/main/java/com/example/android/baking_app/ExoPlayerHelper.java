package com.example.android.baking_app;



import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class ExoPlayerHelper {

    PlayerView playerView;
    SimpleExoPlayer player;
    Context context;
    TrackSelector trackSelector;
    DefaultRenderersFactory renderersFactory;

    public ExoPlayerHelper(SimpleExoPlayer player, Context context,PlayerView playerView) {
        this.player = player;
        this.context = context;
        this.playerView=playerView;
    }

    public SimpleExoPlayer init() {
        trackSelector = new DefaultTrackSelector();

        renderersFactory = new DefaultRenderersFactory(context);
        player = ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector, new DefaultLoadControl());

        return player;
    }
    public void buildMediaSource(Uri uri)
    {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "AudioStreamer"));

        MediaSource audioSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
        player.prepare(audioSource);

    }
    public void setPlayerView()
    {
        player.setPlayWhenReady(true);
        playerView.setPlayer(player);
    }
    public long releaseExoPlayer()
    {
        long currentPosition = 0;

        if(player!=null){
         currentPosition = player.getCurrentPosition();
            player.release();
            player.stop();
            player=null;
        }


        return  currentPosition ;
    }
}

