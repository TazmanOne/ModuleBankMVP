package test.mvptestapp.presentation.view;

import com.arellomobile.mvp.MvpView;

public interface WebDetailView extends MvpView,LoadProgress {
    void setStarIcon(Boolean isSave);
}
