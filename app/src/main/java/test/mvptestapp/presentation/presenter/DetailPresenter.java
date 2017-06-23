package test.mvptestapp.presentation.presenter;

import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import test.mvptestapp.presentation.view.DetailView;

/**
 * Created by user on 23.06.2017.
 */
@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    public void sendDetailFragment(String link, String title, String displayName){
        Bundle bundle = new Bundle();
        bundle.putString("link",link);
        bundle.putString("title",title);
        bundle.putString("name",displayName);
        getViewState().openDetailFragment(bundle);

    }
}
