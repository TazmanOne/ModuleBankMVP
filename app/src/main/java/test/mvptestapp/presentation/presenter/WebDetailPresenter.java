package test.mvptestapp.presentation.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.realm.Realm;
import io.realm.RealmResults;
import test.mvptestapp.model.db.FavoriteDBModel;
import test.mvptestapp.presentation.view.WebDetailView;

@InjectViewState
public class WebDetailPresenter extends MvpPresenter<WebDetailView> {

    public void saveToDbFavAnswer(String title, String link, String name) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            FavoriteDBModel answerToSave = realm1.createObject(FavoriteDBModel.class);
            answerToSave.setTitle(title);
            answerToSave.setLink(link);
            answerToSave.setName(name);

        });

    }

    public void deleteFromDBFavAnswer(String link) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            RealmResults<FavoriteDBModel> result = getExistAnswers(realm1, link);
            result.deleteAllFromRealm();
        });

    }

    public void checkSavedAnswer(String link) {
        getViewState().showLoadingProgress(true);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            RealmResults<FavoriteDBModel> realmResults = getExistAnswers(realm1,link);
            if (realmResults.size()>0){
                getViewState().setStarIcon(true);
            }else {
                getViewState().setStarIcon(false);
            }
        });
    }

    private RealmResults<FavoriteDBModel> getExistAnswers(Realm realm, String link) {
        return realm.where(FavoriteDBModel.class).equalTo("link", link).findAll();
    }
}
