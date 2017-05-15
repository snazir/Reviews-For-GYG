package gyg.android.reviews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by thebr_000 on 13/05/2017.
 */


public class Review implements Serializable {

    @SerializedName("review_id")
    @Expose
    private int reviewId;

    @SerializedName("rating")
    @Expose
    private String rating;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("foreignLanguage")
    @Expose
    private boolean foreignLanguage;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("languageCode")
    @Expose
    private String languageCode;

    @SerializedName("traveler_type")
    @Expose
    private Object travelerType;

    @SerializedName("reviewerName")
    @Expose
    private String reviewerName;
    @SerializedName("reviewerCountry")
    @Expose
    private String reviewerCountry;
    private final static long serialVersionUID = -5673997774593569085L;

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isForeignLanguage() {
        return foreignLanguage;
    }

    public void setForeignLanguage(boolean foreignLanguage) {
        this.foreignLanguage = foreignLanguage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public Object getTravelerType() {
        return travelerType;
    }

    public void setTravelerType(Object travelerType) {
        this.travelerType = travelerType;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewerCountry() {
        return reviewerCountry;
    }

    public void setReviewerCountry(String reviewerCountry) {
        this.reviewerCountry = reviewerCountry;
    }

}

