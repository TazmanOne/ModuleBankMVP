package test.mvptestapp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpView;

import test.mvptestapp.R;
import test.mvptestapp.ui.fragment.SearchAnswerFragment;

public class StartActivity extends MvpAppCompatActivity implements MvpView {
    public static final String TAG = "StartActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        if (savedInstanceState == null) {
            startSearchFragment();
        }
    }

    public void startSearchFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.empty_container, new SearchAnswerFragment(), SearchAnswerFragment.TAG)
                .commit();
    }
}
