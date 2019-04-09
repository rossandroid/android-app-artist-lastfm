//espresso
package com.lfm.rossellamorgante.lfm;

import android.content.Context;
import android.os.SystemClock;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.view.KeyEvent.KEYCODE_ENTER;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mactivity = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void searchArtists(){
        onView(withId(R.id.app_bar_search))
                .perform(click())
                .perform(typeText("cher") , pressKey(KEYCODE_ENTER));

        SystemClock.sleep(5000);

        onView(withId(R.id.artist_list)).check(matches(isDisplayed()));
    }

    @Test
    public void SelectArtist(){
        onView(withId(R.id.app_bar_search))
                .perform(click())
                .perform(typeText("cher") , pressKey(KEYCODE_ENTER));

        SystemClock.sleep(5000);

        onView(withId(R.id.artist_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, click()));

    }
}
