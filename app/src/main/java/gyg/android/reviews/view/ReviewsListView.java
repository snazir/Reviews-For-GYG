package gyg.android.reviews.view;

import java.util.List;
import gyg.android.reviews.model.Review;

public interface ReviewsListView extends MvpView {


    void showEmptyReviewMessage(int resId);

    void showReviewsInRecyclerView(List<Review> mReviewListFromServer);

    void updateTotalReviewCount(int totalReviewCount);
}
