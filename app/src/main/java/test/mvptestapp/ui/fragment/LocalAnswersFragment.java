package test.mvptestapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import test.mvptestapp.R;
import test.mvptestapp.model.db.FavoriteDBModel;
import test.mvptestapp.presentation.presenter.LocalAnswersPresenter;
import test.mvptestapp.presentation.view.LocalAnswersView;
import test.mvptestapp.ui.adapter.LocalSearchRecyclerAdapter;

public class LocalAnswersFragment extends MvpAppCompatFragment implements LocalAnswersView, WebDetailFragment.DBCallback {
    public static final String TAG = "LocalAnswersFragment";
    @InjectPresenter
    LocalAnswersPresenter mLocalAnswersPresenter;

    @BindView(R.id.local_recycler)
    RecyclerView local_recycler;
    @BindView(R.id.local_search_view)
    SearchView localSearch;
    @BindView(R.id.empty_state)
    LinearLayout empty_state;
    @BindView(R.id.back_arr)
    ImageButton back_arr;
    private LocalSearchRecyclerAdapter mAdapter;
    private Realm realm;

    @OnClick(R.id.back_arr)
    void backClicked() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_local_answers, container, false);
        ButterKnife.bind(this, rootView);
        realm = Realm.getDefaultInstance();
        return rootView;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        local_recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mAdapter = new LocalSearchRecyclerAdapter(item -> {
            FavoriteDBModel localItem = (FavoriteDBModel) item;
            mLocalAnswersPresenter.prepareWebDetailFragment(
                    localItem.getLink(),
                    localItem.getTitle(),
                    localItem.getName()
            );
        });
        List<FavoriteDBModel> localData = checkEmptyState();

        mAdapter.setData(localData);
        local_recycler.setAdapter(mAdapter);
        local_recycler.setHasFixedSize(true);


        localSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.filter(query);
                localSearch.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mAdapter.filter(query);
                return true;
            }
        });
    }

    @NonNull
    private List<FavoriteDBModel> checkEmptyState() {
        List<FavoriteDBModel> localData = mLocalAnswersPresenter.getLocalAnswers();
        if (localData.size() > 0) {
            empty_state.setVisibility(View.GONE);
        } else {
            empty_state.setVisibility(View.VISIBLE);
        }
        return localData;
    }


    @Override
    public void openDetailFragment(Bundle bundle) {
        localSearch.clearFocus();
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
    public void onUpdateDBValue() {
        List<FavoriteDBModel> localData = checkEmptyState();
        mAdapter.setData(localData);


    }
}
