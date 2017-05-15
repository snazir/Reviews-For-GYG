package gyg.android.reviews.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import gyg.android.reviews.R;
import gyg.android.reviews.adapters.ReviewsListAdapter;
import gyg.android.reviews.base.BaseActivity;
import gyg.android.reviews.model.Review;
import gyg.android.reviews.presenter.ReviewPresenter;
import gyg.android.reviews.view.ReviewsListView;

public class ReviewsActivity extends BaseActivity
        implements ReviewsListView, SwipeRefreshLayout.OnRefreshListener {

    ReviewPresenter mReviewPresenter;
    ReviewsListAdapter mAdapter;

    @BindView(R.id.reviews_recycler_view)
    RecyclerView reviewListRecyclerView;

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.tv_total_reviews)
    TextView tvTotalReviews;
    @BindView(R.id.empty_reviews_text_view)
    TextView tvEmptyReviews;


    @Override
    public void initViews(Bundle savedInstanceState) {
        if (mReviewPresenter == null) {
            mReviewPresenter = new ReviewPresenter();
        }
        mReviewPresenter.attachView(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        setupRecyclerView(reviewListRecyclerView);
        onRefresh();
    }

    private void setupRecyclerView(RecyclerView reviewListRecyclerView) {

        mAdapter = new ReviewsListAdapter(this);
        reviewListRecyclerView.setAdapter(mAdapter);
        reviewListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;

    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mAdapter.clearAdapter();
        tvTotalReviews.setVisibility(View.INVISIBLE);
        mReviewPresenter.loadReviewsFromServer();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showEmptyReviewMessage(int resId) {

        updateScreenViews(false);
        tvEmptyReviews.setText(getString(resId));

    }

    private void updateScreenViews(boolean isContainReview) {
        mSwipeRefreshLayout.setRefreshing(false);

        if (isContainReview) {
            tvTotalReviews.setVisibility(View.VISIBLE);
            tvEmptyReviews.setVisibility(View.INVISIBLE);
            reviewListRecyclerView.setVisibility(View.VISIBLE);
        } else {
            tvTotalReviews.setVisibility(View.INVISIBLE);
            tvEmptyReviews.setVisibility(View.VISIBLE);
            reviewListRecyclerView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showReviewsInRecyclerView(List<Review> mReviewListFromServer) {
        mAdapter.setReviews(mReviewListFromServer);
        mAdapter.notifyDataSetChanged();
        reviewListRecyclerView.requestFocus();
//        progressBar.setVisibility(View.INVISIBLE);

        updateScreenViews(true);
    }

    @Override
    public void updateTotalReviewCount(int totalReviewCount) {
        tvTotalReviews.setText("Total Reviews : " + totalReviewCount);
    }

    @Override
    protected void onDestroy() {
        mReviewPresenter.detachView();
        super.onDestroy();
    }


}
