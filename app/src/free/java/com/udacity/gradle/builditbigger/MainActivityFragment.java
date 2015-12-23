package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import maximyudin.lib.jokesdislaylibrary.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements GetJokeListener {
    private RelativeLayout rlContent;
    private ProgressBar pbLoading;
    InterstitialAd mInterstitialAd;
    private String jokeText;


    public MainActivityFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));
        requestNewInterstitial();

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                showJoke(jokeText);
            }
        });
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private void showJoke(String jokeText) {
        Intent jokeDisplay = new Intent(getContext(), JokeActivity.class);
        jokeDisplay.putExtra(JokeActivity.JOKE, jokeText);
        startActivity(jokeDisplay);
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
        if (TextUtils.isEmpty(jokeText)) {
            Toast.makeText(getContext(), getString(R.string.joke_fetch_fail), Toast.LENGTH_SHORT).show();
            return;
        }
        this.jokeText = jokeText;
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            showJoke(jokeText);
        }
    }

    private void showLoadingIndicator(boolean willShowProgress) {
        pbLoading.setVisibility(willShowProgress ? View.VISIBLE : View.GONE);
        rlContent.setVisibility(willShowProgress ? View.GONE : View.VISIBLE);
    }
}
