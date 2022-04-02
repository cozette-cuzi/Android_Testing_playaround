package com.example.myapplication;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTestBehavior1() {
        ViewInteraction stringInput = onView(withId(R.id.editText_string));
        ViewInteraction numberInput = onView(withId(R.id.editText_number));
        ViewInteraction materialButton = onView(withId(R.id.button));
        ViewInteraction output = onView(withId(R.id.textView_output));

        String s = "Example Text";
        int n = 7;

        stringInput.perform(replaceText(s), closeSoftKeyboard());
        numberInput.perform(replaceText(Integer.toString(n)), closeSoftKeyboard());
        materialButton.perform(click());
        try {
            CropString c = new CropString(n, s);
            output.check(matches(withText(c.calculate())));
        } catch (Exception e) {
            output.check(matches(withText(e.getMessage())));
        }

    }

    @Test
    public void mainActivityTestBehavior2() {
        ViewInteraction stringInput = onView(withId(R.id.editText_string));
        ViewInteraction numberInput = onView(withId(R.id.editText_number));
        ViewInteraction materialButton = onView(withId(R.id.button));
        ViewInteraction output = onView(withId(R.id.textView_output));

        String a = "Example Text";
        String b = "string";

        stringInput.perform(replaceText(a), closeSoftKeyboard());
        numberInput.perform(replaceText(b), closeSoftKeyboard());
        materialButton.perform(click());

        output.check(matches(withText(R.string.not_a_number_err)));
    }

    @Test
    public void mainActivityTestBehavior3() {
        ViewInteraction stringInput = onView(withId(R.id.editText_string));
        ViewInteraction numberInput = onView(withId(R.id.editText_number));
        ViewInteraction materialButton = onView(withId(R.id.button));
        ViewInteraction output = onView(withId(R.id.textView_output));

        String s = "Example Text";
        int n = 20;

        stringInput.perform(replaceText(s), closeSoftKeyboard());
        numberInput.perform(replaceText(Integer.toString(n)), closeSoftKeyboard());
        materialButton.perform(click());
        try {
            CropString c = new CropString(n, s);
            output.check(matches(withText(c.calculate())));

        } catch (Exception e) {
            output.check(matches(withText(e.getMessage())));
        }
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
