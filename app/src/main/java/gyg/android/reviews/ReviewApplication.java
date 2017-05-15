package gyg.android.reviews;

import android.app.Application;
import android.content.Context;

import gyg.android.reviews.network.ReviewService;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class ReviewApplication extends Application {

    private ReviewService reviewService;
    private Scheduler defaultSubscribeScheduler;

    public static ReviewApplication get(Context context) {
        return (ReviewApplication) context.getApplicationContext();
    }

    public ReviewService getReviewService() {
        if (reviewService == null) {
            reviewService = ReviewService.Factory.create();
        }
        return reviewService;
    }

    //For setting mocks during testing
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    public Scheduler defaultSubscribeScheduler() {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io();
        }
        return defaultSubscribeScheduler;
    }

    //User to change scheduler from tests
    public void setDefaultSubscribeScheduler(Scheduler scheduler) {
        this.defaultSubscribeScheduler = scheduler;
    }
}
