package com.example.myapplication;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnitRunner;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.example.myapplication.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import java.util.concurrent.atomic.AtomicBoolean;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivitySystemTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTestBehavior1() {
        ViewInteraction stringInput = onView(withId(R.id.editText_string));
        ViewInteraction numberInput = onView(withId(R.id.editText_number));
        ViewInteraction materialButton = onView(withId(R.id.button));
        ViewInteraction output = onView(withId(R.id.textView_output));

        stringInput.perform(replaceText("Example Text"), closeSoftKeyboard());
        numberInput.perform(replaceText("7"), closeSoftKeyboard());
        materialButton.perform(click());
        output.check(matches(withText("Example")));
    }

    @Test
    public void mainActivityTestBehavior2() {
        ViewInteraction stringInput = onView(withId(R.id.editText_string));
        ViewInteraction numberInput = onView(withId(R.id.editText_number));
        ViewInteraction materialButton = onView(withId(R.id.button));
        ViewInteraction output = onView(withId(R.id.textView_output));

        stringInput.perform(replaceText("Example Text"), closeSoftKeyboard());
        numberInput.perform(replaceText("ss"), closeSoftKeyboard());
        materialButton.perform(click());
        output.check(matches(withText(R.string.not_a_number_err)));
    }

    @Test
    public void mainActivityTestBehavior3() {
        ViewInteraction stringInput = onView(withId(R.id.editText_string));
        ViewInteraction numberInput = onView(withId(R.id.editText_number));
        ViewInteraction materialButton = onView(withId(R.id.button));
        ViewInteraction output = onView(withId(R.id.textView_output));

        stringInput.perform(replaceText("Example Text"), closeSoftKeyboard());
        numberInput.perform(replaceText("20"), closeSoftKeyboard());
        materialButton.perform(click());
        output.check(matches(withText(R.string.greater_than_length_err)));
    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
