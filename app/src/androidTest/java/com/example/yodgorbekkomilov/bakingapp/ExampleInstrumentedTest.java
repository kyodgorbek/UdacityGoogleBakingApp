package com.example.yodgorbekkomilov.bakingapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Checks;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> menuActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);


    @Test
    public void clickItem() {
        matchToolbarTitle("BakingApp")
                .check(matches(isDisplayed()));
    }


    private static ViewInteraction matchToolbarTitle(
            CharSequence title) {
        return onView(isAssignableFrom(Toolbar.class))
                .check(matches(withToolbarTitle(is(title))));
    }

    private static Matcher<Object> withToolbarTitle(
            final Matcher<CharSequence> textMatcher) {
        return new BoundedMatcher<Object, Toolbar>(Toolbar.class) {
            @Override public boolean matchesSafely(Toolbar toolbar) {
                return textMatcher.matches(toolbar.getTitle());
            }
            @Override public void describeTo(Description description) {
                description.appendText("with toolbar title: ");
                textMatcher.describeTo(description);
            }
        };
    }



    public static Matcher<View> withBgColor(final int color) {
        Checks.checkNotNull(color);
        return new BoundedMatcher<View, RelativeLayout>(RelativeLayout.class) {
            @Override
            public boolean matchesSafely(RelativeLayout row) {
                return color == ((ColorDrawable) row.getBackground()).getColor();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with text color: ");
            }
        };
    }


    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.yodgorbekkomilov.bakingapp", appContext.getPackageName());

        // ActivityTestRule ingredientAdapterActivityTestRule =
        //       new ActivityTestRule(IngredientAdapter.class);
    }

    @Test
    public void listGoesOverTheFold() {
        //onView(withId(R.id.my_text_view)).perform(click());

        onView(withId(R.id.container)) // this matches the RelativeLayout
                .check(matches(withBgColor(Color.WHITE)));
    }
    // @Test
    //public void buttonIsClickable() throws InterruptedException {
    //    onView(withText("Nutella Pie")).perform(click());
    //  Thread.sleep(1000);
    //onView(withText("Nutella Pie")).check(matches(isDisplayed()));


  //  @Test
    //public void clickItem() {
        //  onView(with(R.id.card_recycler_view))
        //        .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
     //   onView(withId(R.id.my_child_toolbar)).check(matches(isDisplayed()));
       // onView(withText(R.string.app_name)).check(matches(withParent(withId(R.id.my_child_toolbar))));
        // onView(withId(R.id.my_text_view))
        //       .check(matches(withText("0")))
        //     .check(matches(isDisplayed()));

    }
//}







