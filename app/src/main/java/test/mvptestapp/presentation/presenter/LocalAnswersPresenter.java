package test.mvptestapp.presentation.presenter;


import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import test.mvptestapp.model.db.FavoriteDBModel;
import test.mvptestapp.presentation.view.LocalAnswersView;

@InjectViewState
public class LocalAnswersPresenter extends MvpPresenter<LocalAnswersView> {

    public void prepareWebDetailFragment(String link, String title, String displayName) {
        Bundle bundle = new Bundle();
        bundle.putString("link", link);
        bundle.putString("title", title);
        bundle.putString("name", displayName);
        getViewState().openDetailFragment(bundle);
    }

    public List<FavoriteDBModel> getLocalAnswers() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<FavoriteDBModel> realmResults = realm.where(FavoriteDBModel.class).findAll();
        List<FavoriteDBModel> list = new ArrayList<>();
        list.addAll(realmResults);
        realm.close();
        return list;
    }
}
