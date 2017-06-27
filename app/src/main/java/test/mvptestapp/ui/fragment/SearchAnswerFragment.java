package test.mvptestapp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.mvptestapp.R;
import test.mvptestapp.model.retrofit.AnswerModel;
import test.mvptestapp.presentation.presenter.SearchAnswerPresenter;
import test.mvptestapp.presentation.view.SearchAnswerView;
import test.mvptestapp.ui.adapter.SearchRecyclerAdapter;

public class SearchAnswerFragment extends MvpAppCompatFragment implements SearchAnswerView, WebDetailFragment.DBCallback {
    public static final String TAG = "SearchAnswerFragment";
    @InjectPresenter
    SearchAnswerPresenter mSearchAnswerPresenter;

    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.refreshLoading)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.badge_textView)
    TextView countFavAnswers;
    @BindView(R.id.btn_favorite)
    Button btnFavorite;
    @BindView(R.id.empty_state)
    LinearLayout emptyState;

    private SearchRecyclerAdapter mAdapter;


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_answer, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnFavorite.setText(getString(R.string.favorite) + " " + new String(Character.toChars(0x2B50)));
        showLoadingProgress(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mAdapter = new SearchRecyclerAdapter(
                item -> mSearchAnswerPresenter.openWebDetailFragment(
                        item.getLink(),
                        item.getTitle(),
                        item.getOwner().getDisplayName()));
        recyclerView.setAdapter(mAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                onSearchStart(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mSearchAnswerPresenter.updateCountFav();


    }


    public void onSearchStart(String query) {
        mSearchAnswerPresenter.startSearching(query, 1);
        mAdapter.clear();
        emptyState.setVisibility(View.GONE);
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
    public void showData(AnswerModel answerModel) {
        if (answerModel.getItems().size() > 0) {
            emptyState.setVisibility(View.GONE);
            mAdapter.setPosts(answerModel.getItems());
        } else {
            emptyState.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void openDetailFragment(Bundle bundle) {
        searchView.clearFocus();
        WebDetailFragment fragment = new WebDetailFragment();
        fragment.setDbCallback(this);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.container, fragment, WebDetailFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void updateFavCount(int count) {
        countFavAnswers.setText(String.valueOf(count));
    }

    @Override
    public void onUpdateDBValue() {
        mSearchAnswerPresenter.updateCountFav();
    }
}
