package gyg.android.reviews.presenter;

interface Presenter<V> {

    void attachView(V view);

    void detachView();

}