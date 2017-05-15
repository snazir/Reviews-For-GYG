package gyg.android.reviews.activity;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gyg.android.reviews.R;
import gyg.android.reviews.RecyclerViewItemCountAssertion;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ReviewsActivityTest {

    @Rule
    public ActivityTestRule<ReviewsActivity> mActivityTestRule = new ActivityTestRule<>(ReviewsActivity.class);

    @Test
    public void reviewsActivityRecyclerViewCountTest() {
        onView(withId(R.id.reviews_recycler_view)).check(new RecyclerViewItemCountAssertion(322));

    }

}
