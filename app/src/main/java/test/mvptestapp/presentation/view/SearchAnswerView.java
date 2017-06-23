package test.mvptestapp.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import test.mvptestapp.model.retrofit.AnswerModel;

public interface SearchAnswerView extends MvpView {
    void onSearchStart(String query);

    @StateStrategyType(SingleStateStrategy.class)
    void showLoadingProgress(Boolean isLoading);
    @StateStrategyType(SingleStateStrategy.class)
    void showData(AnswerModel answerModel);
}
