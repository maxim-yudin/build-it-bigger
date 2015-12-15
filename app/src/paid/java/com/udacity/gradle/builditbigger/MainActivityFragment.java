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
        Intent jokeDisplay = new Intent(getContext(), JokeActivity.class);
        jokeDisplay.putExtra(JokeActivity.JOKE, jokeText);
        startActivity(jokeDisplay);
    }

    private void showLoadingIndicator(boolean willShowProgress) {
        pbLoading.setVisibility(willShowProgress ? View.VISIBLE : View.GONE);
        rlContent.setVisibility(willShowProgress ? View.GONE : View.VISIBLE);
    }
}
