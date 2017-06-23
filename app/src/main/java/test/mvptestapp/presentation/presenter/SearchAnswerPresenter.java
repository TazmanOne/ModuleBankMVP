package test.mvptestapp.presentation.presenter;


import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import test.mvptestapp.model.retrofit.AnswerModel;
import test.mvptestapp.model.retrofit.Repository;
import test.mvptestapp.presentation.view.SearchAnswerView;

@InjectViewState
public class SearchAnswerPresenter extends MvpPresenter<SearchAnswerView> {
    public void startSearching(String query, int page) {
        getViewState().showLoadingProgress(true);
        if (!TextUtils.isEmpty(query.trim())) {
            Observable<AnswerModel> data = Repository.getRepository().getAnswers(query, page);
            data.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<AnswerModel>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull AnswerModel answerModel) {
                            getViewState().showData(answerModel);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {
                            getViewState().showLoadingProgress(false);

                        }
                    });


        }
    }

}
