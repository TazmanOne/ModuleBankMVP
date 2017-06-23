package test.mvptestapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import test.mvptestapp.R;
import test.mvptestapp.presentation.presenter.StartPresenter;
import test.mvptestapp.presentation.view.StartView;
import test.mvptestapp.ui.fragment.SearchAnswerFragment;

public class StartActivity extends MvpAppCompatActivity implements StartView {
    public static final String TAG = "StartActivity";

    @InjectPresenter
    StartPresenter mStartPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, StartActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        onActivityStart();
    }

    @Override
    public void onActivityStart() {
        mStartPresenter.startSearchFragment();
    }

    @Override
    public void startSearchFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.empty_container,new SearchAnswerFragment(),SearchAnswerFragment.TAG)
                .commit();
    }
}
