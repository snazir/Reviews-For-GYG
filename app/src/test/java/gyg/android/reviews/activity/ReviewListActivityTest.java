package gyg.android.reviews.activity;

import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


import gyg.android.reviews.BuildConfig;
import gyg.android.reviews.R;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)

public class ReviewListActivityTest {

//    @Test
//    public void sanityCheck() {
//        assertTrue(false);
//    }


    private ReviewsActivity mMovieListActivity;

    @Before
    public void setUp() throws Exception {
        mMovieListActivity = Robolectric.buildActivity(ReviewsActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(mMovieListActivity);

        TextView mTotalReviewsText = (TextView) mMovieListActivity.findViewById(R.id.tv_total_reviews);
        assertNotNull(mTotalReviewsText);

        RecyclerView listRecyclerView = (RecyclerView) mMovieListActivity.findViewById(R.id.reviews_recycler_view);
        assertNotNull(listRecyclerView);
    }

    @Test
    public void shouldHaveCorrectApplicationName() throws Exception {
        String appName = mMovieListActivity.getResources().getString(R.string.app_name);
        assertThat(appName, equalTo("Reviews"));
    }
}
