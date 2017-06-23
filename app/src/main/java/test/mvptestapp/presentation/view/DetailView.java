package test.mvptestapp.presentation.view;

import android.os.Bundle;

import com.arellomobile.mvp.MvpView;

/**
 * Created by user on 23.06.2017.
 */

public interface DetailView extends MvpView{
    void openDetailFragment(Bundle bundle);
}
