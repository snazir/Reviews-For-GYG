package gyg.android.reviews.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initViews(savedInstanceState);
    }

    public abstract void initViews(Bundle savedInstanceState);

    public abstract int getLayoutId();
}
