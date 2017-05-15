package gyg.android.reviews.util;

import java.util.ArrayList;
import java.util.Random;

import gyg.android.reviews.model.Review;
import gyg.android.reviews.model.ServerResponse;


public class MockModelServiceResponse {

    public static ServerResponse responseFromServer(int numReviews) {

        ServerResponse response = new ServerResponse();
        ArrayList<Review> reviewList = new ArrayList<>(numReviews);
        for (int i = 0; i < numReviews; i++) {
            reviewList.add(newReview(i));
        }
        response.setReviewList(reviewList);
        return response;
    }

    private static Review newReview(int number) {
        Random random = new Random();

        Review review = new Review();
        review.setReviewId(random.nextInt(10000));
        review.setAuthor("Author"+number);
        review.setTitle("Title"+number);
        review.setMessage("Message"+number);
        review.setRating("4.5");
        return review;
    }
}
