package gyg.android.reviews.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gyg.android.reviews.R;
import gyg.android.reviews.model.Review;

public class ReviewsListAdapter extends RecyclerView.Adapter<ReviewsListAdapter.ReviewViewHolder> {

    private List<Review> mReviewList;
    Context mContext;

    public ReviewsListAdapter(Context context) {
        mContext = context;
        this.mReviewList = Collections.emptyList();
    }

    public ReviewsListAdapter(List<Review> reviewList) {
        this.mReviewList = reviewList;
    }

    public void setReviews(List<Review> reviews) {
        this.mReviewList = reviews;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_list_item, parent, false);

        return new ReviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {

        Review review = mReviewList.get(position);
        holder.review = review;
        holder.reviewDateTextView.setText(review.getDate());
        holder.reviewerNameTextView.setText(review.getAuthor());
        holder.reviewMessageTextView.setText(review.getMessage());
        holder.reviewTitleTextView.setText(review.getTitle());
        holder.reviewRatingBar.setRating(Float.parseFloat(review.getRating()));


    }

    @Override
    public int getItemCount() {
        return mReviewList.size();
    }

    public void clearAdapter() {
        mReviewList.clear();
        notifyDataSetChanged();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {

        //  Define the bindings to the ViewHolder's views

        @BindView(R.id.tv_review_title)
        TextView reviewTitleTextView;

        @BindView(R.id.tv_review)
        TextView reviewMessageTextView;

        @BindView(R.id.tv_reviewer_name)
        TextView reviewerNameTextView;

        @BindView(R.id.tv_review_date)
        TextView reviewDateTextView;

        @BindView(R.id.rating_bar)
        RatingBar reviewRatingBar;

        public Review review;

        public ReviewViewHolder(View itemView) {
            super(itemView);

            //  Bind Butter knife to this view holder
            ButterKnife.bind(this, itemView);

        }
    }
}
