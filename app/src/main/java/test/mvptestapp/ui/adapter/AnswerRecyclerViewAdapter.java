package test.mvptestapp.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import test.mvptestapp.R;
import test.mvptestapp.model.retrofit.AnswerModel;
import test.mvptestapp.presentation.presenter.DetailPresenter;
import test.mvptestapp.presentation.view.DetailView;

/**
 * Created by user on 23.06.2017.
 */

public class AnswerRecyclerViewAdapter extends MvpAdapter {
    List<AnswerModel.Items> itemsList = new ArrayList<>();

    public AnswerRecyclerViewAdapter(MvpDelegate<?> parentDelegate, String childId) {
        super(parentDelegate, childId);
    }


    public void setItemsList(List<AnswerModel.Items> itemsList) {
        this.itemsList = itemsList;
        notifyDataSetChanged();
    }

    public void addItemsToList(List<AnswerModel.Items> addItems) {
        this.itemsList.addAll(addItems);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_item, parent, false);
        return new AnswerViewHolder(getMvpDelegate(), v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AnswerViewHolder viewHolder = (AnswerViewHolder) holder;
        viewHolder.bind(itemsList.get(position));

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }


    public class AnswerViewHolder extends MvpViewHolder implements DetailView {
        @InjectPresenter
        DetailPresenter mDetailPresenter;

        @ProvidePresenter
        DetailPresenter provideDetailPresenter() {
            return new DetailPresenter();
        }

        private AnswerModel.Items mAnswer;

        @BindView(R.id.answers_count)
        TextView answersCount;
        @BindView(R.id.authorName)
        TextView authorName;
        @BindView(R.id.questionName)
        TextView questionName;
        @BindView(R.id.votes_count)
        TextView votesCount;
        @BindView(R.id.views_count)
        TextView viewsCount;
        @BindView(R.id.answerItemLayout)
        LinearLayout answerItemLayout;


        AnswerViewHolder(MvpDelegate<?> parentDelegate, View itemView) {
            super(parentDelegate, itemView);
        }

        public void bind(AnswerModel.Items answer) {
            destroyMvpDelegate();
            this.mAnswer = answer;
            createMvpDelegate();
            answersCount.setText(String.valueOf(mAnswer.getAnswerCount()));
            authorName.setText(mAnswer.getOwner().getDisplayName());
            questionName.setText(Html.fromHtml(mAnswer.getTitle()));
            votesCount.setText(String.valueOf(mAnswer.getScore()));
            viewsCount.setText(String.valueOf(mAnswer.getViewCount()));
            answerItemLayout.setOnClickListener(v ->
                    mDetailPresenter.sendDetailFragment(mAnswer.getLink(),
                            mAnswer.getTitle(),
                            mAnswer.getOwner().getDisplayName()));
        }


        @Override
        public void openDetailFragment(Bundle bundle) {

        }

        @Override
        protected String getMvpChildId() {
            return mAnswer == null ? null : mAnswer.getLink();
        }
    }
}
