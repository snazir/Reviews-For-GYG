package gyg.android.reviews.presenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import gyg.android.reviews.BuildConfig;
import gyg.android.reviews.R;
import gyg.android.reviews.ReviewApplication;
import gyg.android.reviews.model.ServerResponse;
import gyg.android.reviews.network.ReviewService;
import gyg.android.reviews.util.MockModelServiceResponse;
import gyg.android.reviews.view.ReviewsListView;
import rx.Observable;
import rx.schedulers.Schedulers;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ReviewListPresenterTest {


    ReviewPresenter mReviewListPresenter;
    ReviewsListView mReviewListView;
    ReviewService mService;

//    @Test
//    public void sanityCheck() {
//        assertTrue(true);
//    }



    @Before
    public void initMocksAndSetup() {

        ReviewApplication application = (ReviewApplication) RuntimeEnvironment.application;
        mService = mock(ReviewService.class);
        // Mock the retrofit service so we don't call the API directly
        application.setReviewService(mService);
        // Change the default subscribe schedulers so all observables
        // will now run on the same thread
        application.setDefaultSubscribeScheduler(Schedulers.immediate());
        mReviewListPresenter = new ReviewPresenter();
        mReviewListView = mock(ReviewsListView.class);
        when(mReviewListView.getContext()).thenReturn(application);
        mReviewListPresenter.attachView(mReviewListView);

    }

    @After
    public void tearDown() {
        mReviewListPresenter.detachView();
    }


    @Test
    public void shouldShowReviewsOnRecyclerViewWhenLoadedFromServer() {

        ServerResponse response = MockModelServiceResponse.responseFromServer(10);
        when(mService.getReviewsList())
                .thenReturn(Observable.just(response));

        mReviewListPresenter.loadReviewsFromServer();
        verify(mReviewListView).showReviewsInRecyclerView(response.getReviewList());
    }

    @Test
    public void shouldShowEmptyMessageWhenNoReviewLoadedFromServer() {
        ServerResponse response = MockModelServiceResponse.responseFromServer(0);

        when(mService.getReviewsList())
                .thenReturn(Observable.just(response));

        mReviewListPresenter.loadReviewsFromServer();
        verify(mReviewListView).showEmptyReviewMessage(R.string.text_empty_reviews);
    }

    @Test
    public void shouldShowDefaultErrorMessageWhenSomeErrorOccurred() {
        when(mService.getReviewsList())
                .thenReturn(Observable.<ServerResponse>error(new RuntimeException("error")));

        mReviewListPresenter.loadReviewsFromServer();
        verify(mReviewListView).showEmptyReviewMessage(R.string.error_loading_reviews);
    }


}
