package com.example.yodgorbekkomilov.bakingapp;


import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yodgorbekkomilov.bakingapp.pojo.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    public static  final String POSITION_KEY = "STEP_POSITION";
    public static final String PLAYER_POSITION_KEY = "POSITION_KEY";
    public static final String PLAYER_WINDOW_KEY = "POSITION_WINDOW";
    public static final String PLAYER_STATE_KEY = "PLAYER_STATE";


    public int MAX_THRESHOLD;
    // public List<Step> step;
    public ArrayList<Step> stepList;
    public Step currentStep;
    public Step step;
    public ExoPlayer mExoPlayer;
    public SimpleExoPlayerView mPlayerView;
    long playbackPosition;
    int currentWindow;
    boolean playWhenReady;
    public ImageView imageView;

    public int position;

    public String videoURL;
    public Uri videoUri;
    public TextView textView;
    public String thumbnailURL;
    public String description;
    public Button nextButton;
    public Button previousButton;


    public DetailFragment() {
        // Required empty public constructor
    }

    public void showCurrentStep(Step currentStep) {
        if (currentStep != null) {
            thumbnailURL = currentStep.getThumbnailURL();
            if (currentStep.getVideoURL() != null) {
                videoURL = currentStep.getVideoURL();
                videoUri = Uri.parse(videoURL);
                initializePlayer(videoUri);
            } else if ((!TextUtils.isEmpty(thumbnailURL))) {
                //Load the thumbnailURL in the imageView
                Picasso.with(getContext()).load(thumbnailURL).into(imageView);


}

            //in any other case you can load a placeholder image in the imageView (as you have done with the recipe's images)




        textView.setText(currentStep.getDescription());

        Log.i("toString ===> ", currentStep.toString());



    }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        mPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.video);
        nextButton = (Button) rootView.findViewById(R.id.next);
        textView = (TextView) rootView.findViewById(R.id.steps);
        imageView = (ImageView)rootView.findViewById(R.id.imageView);


        // Inflate the layout for this fragment
        // Step steps = getArguments().getParcelable("Step");

         if(getArguments() != null ){
          // ArrayList<Step> stepsList = (ArrayList<Step>)getSteps();
           stepList = getArguments().getParcelableArrayList("stepsList");
           position = getArguments().getInt("position");
           currentStep = stepList.get(position);
           step = currentStep;
            videoURL = step.getVideoURL();
           if(!TextUtils.isEmpty(videoURL)){
               videoUri = Uri.parse(videoURL);


           }
           MAX_THRESHOLD = stepList.size();
       }





        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position >= 0 && position < MAX_THRESHOLD) {
                    position++;
                    currentStep = stepList.get(position);


                    // if (position < stepList.size() + 1) {
                   /* nextButton.setEnabled(true);
                    showCurrentStep(currentStep);
                    initializePlayer(videoUri);*/
                    releasePlayer();

                    prepareContent();
                    //}
                }
            }


        });

        //TODO LOGIC PREVIUS BUTTOM

        rootView.findViewById(R.id.previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "How previous?", Toast.LENGTH_LONG).show();
                position--;
                if (position >= 0) {
                    currentStep = stepList.get(position);

                    releasePlayer();

                    prepareContent();

                }
                }

        });

        return rootView;
    }

    void prepareContent() {
        nextButton.setEnabled(true);

        showCurrentStep(currentStep);
       // initializePlayer(videoUri);
    }


    public void initializePlayer(Uri videoURL) {
        try {
            if (mExoPlayer == null) {
                TrackSelector trackSelector = new DefaultTrackSelector();
                LoadControl loadControl = new DefaultLoadControl();
                mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
                mPlayerView.setPlayer((SimpleExoPlayer) mExoPlayer);
                String userAgent = Util.getUserAgent(getContext(), "Baking App");
                MediaSource mediaSource = new ExtractorMediaSource(videoURL,
                        new DefaultDataSourceFactory(getContext(), userAgent),
                         new DefaultExtractorsFactory(), null, null);
                mExoPlayer.setPlayWhenReady(playWhenReady);
                mExoPlayer.seekTo(currentWindow, playbackPosition);
                mExoPlayer.prepare(mediaSource);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void releasePlayer() {
        if (mExoPlayer != null) {
            playbackPosition = mExoPlayer.getCurrentPosition();
            currentWindow = mExoPlayer.getCurrentWindowIndex();
            playWhenReady = mExoPlayer.getPlayWhenReady();

            mExoPlayer.release();

            mExoPlayer = null;
        }
    }



    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        prepareContent();
    }



    void setPlayWhenReady(boolean playWhenReady) {
        mExoPlayer.setPlayWhenReady(!mExoPlayer.getPlayWhenReady());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            playbackPosition = savedInstanceState.getLong(PLAYER_POSITION_KEY);
            playWhenReady = savedInstanceState.getBoolean(PLAYER_STATE_KEY);
            currentWindow = savedInstanceState.getInt(PLAYER_WINDOW_KEY);
            position = savedInstanceState.getInt(POSITION_KEY);
            currentStep = stepList.get(position);


        }




    }

   public void onSaveInstanceState(Bundle outState){
       super.onSaveInstanceState(outState);
       outState.putLong(PLAYER_POSITION_KEY, playbackPosition);
       outState.putLong(PLAYER_WINDOW_KEY, currentWindow);
       outState.putBoolean(PLAYER_STATE_KEY, playWhenReady);
       outState.putInt(POSITION_KEY, position);

   }

}


















