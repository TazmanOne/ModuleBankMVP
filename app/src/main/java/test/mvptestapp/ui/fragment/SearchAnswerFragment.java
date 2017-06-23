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

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.mvptestapp.R;
import test.mvptestapp.model.retrofit.AnswerModel;
import test.mvptestapp.presentation.presenter.DetailPresenter;
import test.mvptestapp.presentation.presenter.SearchAnswerPresenter;
import test.mvptestapp.presentation.view.DetailView;
import test.mvptestapp.presentation.view.SearchAnswerView;
import test.mvptestapp.ui.adapter.AnswerRecyclerViewAdapter;

public class SearchAnswerFragment extends MvpAppCompatFragment implements SearchAnswerView, DetailView {
    public static final String TAG = "SearchAnswerFragment";
    @InjectPresenter
    SearchAnswerPresenter mSearchAnswerPresenter;
    @InjectPresenter
    DetailPresenter mDetailPresenter;

    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.refreshLoading)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    AnswerRecyclerViewAdapter mAdapter;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mAdapter = new AnswerRecyclerViewAdapter(getMvpDelegate(),String.valueOf(0));
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


    }

    @Override
    public void onSearchStart(String query) {
        mSearchAnswerPresenter.startSearching(query, 1);
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
        mAdapter.setItemsList(answerModel.getItems());

    }

    @Override
    public void openDetailFragment(Bundle bundle) {
        WebDetailFragment fragment = new WebDetailFragment();
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.empty_container, fragment, WebDetailFragment.TAG)
                .addToBackStack(null)
                .commit();
    }
}
