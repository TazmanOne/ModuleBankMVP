package test.mvptestapp.presentation.view;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by user on 27.06.2017.
 */

interface LoadProgress {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showLoadingProgress(Boolean isLoading);

}
