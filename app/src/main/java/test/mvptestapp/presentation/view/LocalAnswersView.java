package test.mvptestapp.presentation.view;

import android.os.Bundle;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface LocalAnswersView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void openDetailFragment(Bundle bundle);
}