package test.mvptestapp.presentation.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import test.mvptestapp.presentation.view.StartView;

@InjectViewState
public class StartPresenter extends MvpPresenter<StartView> {

    public void startSearchFragment(){
        getViewState().startSearchFragment();
    }

}
