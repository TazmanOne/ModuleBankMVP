package test.mvptestapp.presentation.view;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


interface LoadProgress {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showLoadingProgress(Boolean isLoading);

}
