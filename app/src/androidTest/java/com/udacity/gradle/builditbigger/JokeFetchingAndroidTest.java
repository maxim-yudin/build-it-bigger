package com.udacity.gradle.builditbigger;

import android.os.ConditionVariable;
import android.test.AndroidTestCase;
import android.text.TextUtils;

/**
 * Created by maximyudin on 15.12.15.
 */
public class JokeFetchingAndroidTest extends AndroidTestCase implements GetJokeListener {
    private JokesAsyncTask jokesAsyncTask;
    private ConditionVariable waiter;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        jokesAsyncTask = new JokesAsyncTask();
        waiter = new ConditionVariable();
    }

    public void testJokeIsNotEmpty() {
        jokesAsyncTask.execute(this);
        waiter.block();
    }

    @Override
    public void onGetJokeCompleted(String jokeText) {
        assertFalse(TextUtils.isEmpty(jokeText));
        waiter.open();
    }
}
