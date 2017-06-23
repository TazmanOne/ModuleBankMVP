package test.mvptestapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import test.mvptestapp.R;
import test.mvptestapp.presentation.presenter.WebDetailPresenter;
import test.mvptestapp.presentation.view.WebDetailView;

public class WebDetailFragment extends MvpAppCompatFragment implements WebDetailView {
    public static final String TAG = "WebDetailFragment";
    @InjectPresenter
    WebDetailPresenter mWebDetailPresenter;

    public static WebDetailFragment newInstance() {
        WebDetailFragment fragment = new WebDetailFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
