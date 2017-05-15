package gyg.android.reviews.presenter;

import android.util.Log;

import java.util.ArrayList;

import gyg.android.reviews.R;
import gyg.android.reviews.ReviewApplication;
import gyg.android.reviews.model.Review;
import gyg.android.reviews.model.ServerResponse;
import gyg.android.reviews.network.ReviewService;
import gyg.android.reviews.view.ReviewsListView;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by thebr_000 on 13/05/2017.
 */

public class ReviewPresenter implements Presenter<ReviewsListView> {

    public static String TAG = "ReviewListPresenter";
    private ReviewsListView mReviewsListView;
    private Subscription subscription;
    private ArrayList<Review> mReviewListFromServer;
    private int totalReviewCount;

    @Override
    public void attachView(ReviewsListView view) {

        this.mReviewsListView = view;
    }

    @Override
    public void detachView() {
        this.mReviewsListView = null;
        if (subscription != null) subscription.unsubscribe();

    }

    public void loadReviewsFromServer() {
        if (subscription != null) subscription.unsubscribe();
        ReviewApplication application = ReviewApplication.get(mReviewsListView.getContext());
        ReviewService reviewService = application.getReviewService();
        subscription = reviewService.getReviewsList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscribeScheduler())
                .subscribe(new Subscriber<ServerResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "Review loaded count " + mReviewListFromServer.size()+"");
                        if (!mReviewListFromServer.isEmpty()) {
                            mReviewsListView.updateTotalReviewCount(totalReviewCount);
                            mReviewsListView.showReviewsInRecyclerView(mReviewListFromServer);
                        } else {
                            mReviewsListView.showEmptyReviewMessage(R.string.text_empty_reviews);
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e(TAG, "Error loading Reviews ", error);
                        if (isHttp404(error)) {
                            mReviewsListView.showEmptyReviewMessage(R.string.error_from_server);
                        } else {
                            mReviewsListView.showEmptyReviewMessage(R.string.error_loading_reviews);
                        }
                    }

                    @Override
                    public void onNext(ServerResponse response) {
                        totalReviewCount = response.getTotalReviews();
                        mReviewListFromServer = response.getReviewList();
                    }
                });
    }


    private static boolean isHttp404(Throwable error) {
        return error instanceof HttpException && ((HttpException) error).code() == 404;
    }

    public void handleReviewItemClick(Review review) {
    }
}