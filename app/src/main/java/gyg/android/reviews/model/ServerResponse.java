package gyg.android.reviews.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thebr_000 on 13/05/2017.
 */

public class ServerResponse {

    @SerializedName("total_reviews")
    int totalReviews;

    @SerializedName("data")
    ArrayList<Review> reviewList;

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    public ArrayList<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(ArrayList<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
