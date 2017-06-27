package test.mvptestapp.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.mvptestapp.R;
import test.mvptestapp.presentation.presenter.WebDetailPresenter;
import test.mvptestapp.presentation.view.WebDetailView;

public class WebDetailFragment extends MvpAppCompatFragment implements WebDetailView {
    public static final String TAG = "WebDetailFragment";
    @InjectPresenter
    WebDetailPresenter mWebDetailPresenter;
    @BindView(R.id.webView)
    public WebView webView;
    @BindView(R.id.back_arr)
    ImageButton back_arr;
    @BindView(R.id.add_fav)
    ImageButton add_fav;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private String name;
    private String title;
    private String link;

    public void setDbCallback(DBCallback dbCallback) {
        this.dbCallback = dbCallback;
    }

    private DBCallback dbCallback;

    @OnClick(R.id.add_fav)
    void add_fav_clicked() {
        if (add_fav.isSelected()) {
            mWebDetailPresenter.deleteFromDBFavAnswer(link);
            dbCallback.onUpdateDBValue();
            Toast.makeText(getActivity(), "Удалено из избранного", Toast.LENGTH_SHORT).show();
            add_fav.setSelected(false);
        } else {
            mWebDetailPresenter.saveToDbFavAnswer(title, link, name);
            dbCallback.onUpdateDBValue();
            Toast.makeText(getActivity(), "Добавлено в избранное", Toast.LENGTH_SHORT).show();
            add_fav.setSelected(true);
            getFragmentManager().popBackStack();
        }

    }

    @OnClick(R.id.back_arr)
    void back_arr_clicked() {
        getFragmentManager().popBackStack();
    }

    public static WebDetailFragment newInstance() {
        WebDetailFragment fragment = new WebDetailFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            link = bundle.getString("link");
            title = bundle.getString("title");
            name = bundle.getString("name");
            mWebDetailPresenter.checkSavedAnswer(link);
            webView.loadUrl(link);
            webView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    showLoadingProgress(false);
                }
            });

        }

    }

    @Override
    public void showLoadingProgress(Boolean isLoading) {
        if (isLoading) {
            refreshLayout.setEnabled(true);
            refreshLayout.setRefreshing(true);
        } else {
            refreshLayout.setEnabled(false);
            refreshLayout.setRefreshing(false);
        }
    }



    @Override
    public void setStarIcon(Boolean isSave) {
        if (isSave){
            add_fav.setSelected(true);
        }else {
            add_fav.setSelected(false);
        }
    }

    public interface DBCallback{
        void onUpdateDBValue();
    }
}
