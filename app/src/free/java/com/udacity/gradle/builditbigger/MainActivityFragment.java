package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import maximyudin.lib.jokesdislaylibrary.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements GetJokeListener {
    private RelativeLayout rlContent;
    private ProgressBar pbLoading;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        rlContent = (RelativeLayout) root.findViewById(R.id.rlContent);
        pbLoading = (ProgressBar) root.findViewById(R.id.pbLoading);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        root.findViewById(R.id.btnDisplayJoke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingIndicator(true);
                new JokesAsyncTask().execute(MainActivityFragment.this);
            }
        });

        return root;
    }

    @Override
    public void onGetJokeCompleted(String jokeText) {
        showLoadingIndicator(false);
        Intent jokeDisplay = new Intent(getContext(), JokeActivity.class);
        jokeDisplay.putExtra(JokeActivity.JOKE, jokeText);
        startActivity(jokeDisplay);
    }

    private void showLoadingIndicator(boolean willShowProgress) {
        pbLoading.setVisibility(willShowProgress ? View.VISIBLE : View.GONE);
        rlContent.setVisibility(willShowProgress ? View.GONE : View.VISIBLE);
    }
}