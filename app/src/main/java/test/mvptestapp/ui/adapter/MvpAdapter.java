package test.mvptestapp.ui.adapter;

import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpDelegate;

/**
 * Created by user on 23.06.2017.
 */

public abstract class MvpAdapter extends RecyclerView.Adapter {
    private MvpDelegate<? extends MvpAdapter> mMvpDelegate;
    private MvpDelegate<?> mParentDelegate;
    private String mChildId;

    public MvpAdapter(MvpDelegate<?> parentDelegate, String childId) {
        mParentDelegate = parentDelegate;
        mChildId = childId;

        getMvpDelegate().onCreate();
    }

    public MvpDelegate getMvpDelegate() {
        if (mMvpDelegate == null) {
            mMvpDelegate = new MvpDelegate<>(this);
            mMvpDelegate.setParentDelegate(mParentDelegate, mChildId);

        }
        return mMvpDelegate;
    }
}
