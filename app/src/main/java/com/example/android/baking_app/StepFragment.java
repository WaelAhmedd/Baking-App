package com.example.android.baking_app;


import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.baking_app.Retrofit.StepsModel;
import com.example.android.baking_app.Retrofit.TotalDataModel;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StepFragment extends android.support.v4.app.Fragment {
    ExoPlayerHelper exoPlayerHelper;
    PlayerView playerView;
    SimpleExoPlayer player;
    Context context;
    TextView textView;
    ImageView instead_video;
    TotalDataModel totalDataModel;
    int index;
    boolean btnTest=false;
    long position;
    StepsModel stepsModell;
    ImageView buttonNext,buttonBack;
    String CURRENT_POSITION = "key";
    long previousPosition;


    public StepFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_step, container, false);
        playerView=view.findViewById(R.id.exo_player_view);
        textView=view.findViewById(R.id.step_discreption);
        buttonBack=view.findViewById(R.id.back);
        buttonNext=view.findViewById(R.id.next);
        instead_video=view.findViewById(R.id.video_instead);

        context=getContext();

        exoPlayerHelper = new ExoPlayerHelper(player, context, playerView);
        if(getArguments()!=null)
        {
             stepsModell= (StepsModel) getArguments().getSerializable("Step");
            assert stepsModell != null;
            index=stepsModell.getId();

            setExoPlayer(stepsModell);

            totalDataModel= (TotalDataModel) getArguments().getSerializable("total");

            btnTest=true;

        }
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkVideo(stepsModell)){
                exoPlayerHelper.releaseExoPlayer();}

                if(index!=totalDataModel.getSteps().size()){
                index+=1;
              stepsModell= totalDataModel.getSteps().get(index);

             setExoPlayer(stepsModell);}
                else {
                    Toast.makeText(getActivity(), "we finished", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkVideo(stepsModell)){
                exoPlayerHelper.releaseExoPlayer();}
                if(index!=0){
                index-=1;
                 stepsModell=totalDataModel.getSteps().get(index);

                setExoPlayer(stepsModell);
            }
            else {
                    Toast.makeText(getActivity(), "you can't back it's beginning", Toast.LENGTH_SHORT).show();
                }
            }
        });
     
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(exoPlayerHelper.player!=null)
        {
            exoPlayerHelper.player.seekTo(position);
            btnTest=false;

        }
        else {
            setExoPlayer(stepsModell);
            if(position>0)
            exoPlayerHelper.player.seekTo(position);
            btnTest=true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(checkVideo(stepsModell)){
        position=exoPlayerHelper.releaseExoPlayer();}
    }



    private void setExoPlayer(StepsModel stepsModel)
    {

    if(!stepsModel.getVideoURL().isEmpty()) {


        exoPlayerHelper.playerView.setVisibility(View.VISIBLE);
        instead_video.setVisibility(View.GONE);
        exoPlayerHelper.init();
        exoPlayerHelper.buildMediaSource(Uri.parse(stepsModel.getVideoURL()));
        exoPlayerHelper.setPlayerView();
        exoPlayerHelper.setPlayerView();
        textView.setText(stepsModel.getDescription());
    }
    else {

        playerView.setVisibility(View.GONE);
        instead_video.setVisibility(View.VISIBLE);
        textView.setText(stepsModel.getDescription());

    }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(CURRENT_POSITION)) {
                previousPosition = savedInstanceState.getLong(CURRENT_POSITION);
            }
    }}

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(CURRENT_POSITION, previousPosition);
    }

    private boolean checkVideo(StepsModel stepsModel)
    {
        if(stepsModel.getVideoURL()!="")
        {
            return true;
        }
        else return false;
    }


}
