package test.mvptestapp.presentation.view;

import android.os.Bundle;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import test.mvptestapp.model.retrofit.AnswerModel;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface SearchAnswerView extends MvpView, LoadProgress {

    void showData(AnswerModel answerModel);

    @StateStrategyType(SkipStrategy.class)
    void openDetailFragment(Bundle bundle);

    void updateFavCount(int count);
}
