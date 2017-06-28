package test.mvptestapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.mvptestapp.R;
import test.mvptestapp.model.db.FavoriteDBModel;


public class LocalSearchRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final OnItemClickListener listener;
    private List<FavoriteDBModel> localAnswersList;
    private List<FavoriteDBModel> locAnsListCopy;

    public LocalSearchRecyclerAdapter(OnItemClickListener listener) {
        this.localAnswersList = new ArrayList<>();
        this.locAnsListCopy = new ArrayList<>();
        this.listener = listener;
    }

    public void setData(List<FavoriteDBModel> posts) {
        this.locAnsListCopy.clear();
        this.localAnswersList.clear();
        this.locAnsListCopy.addAll(posts);
        this.localAnswersList.addAll(posts);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_item, parent, false);
        return new LocalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LocalViewHolder vh = (LocalViewHolder) holder;
        vh.bind(localAnswersList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        if (locAnsListCopy == null)
            return 0;
        return locAnsListCopy.size();
    }

    public void filter(String text) {
        locAnsListCopy.clear();
        if (text.isEmpty()) {
            locAnsListCopy.addAll(localAnswersList);
        } else {
            text = text.toLowerCase();
            for (FavoriteDBModel item : localAnswersList) {
                if (item.getTitle().toLowerCase().contains(text)) {
                    locAnsListCopy.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    private class LocalViewHolder extends RecyclerView.ViewHolder {
        TextView questionName;
        TextView authorName;
        LinearLayout questionItem;
        LinearLayout statLayout;
        ImageView vLine;

        LocalViewHolder(View itemView) {
            super(itemView);
            questionName = (TextView) itemView.findViewById(R.id.questionName);
            authorName = (TextView) itemView.findViewById(R.id.authorName);
            questionItem = (LinearLayout) itemView.findViewById(R.id.answerItemLayout);
            statLayout = (LinearLayout) itemView.findViewById(R.id.statLayout);
            vLine = (ImageView) itemView.findViewById(R.id.vLine);

        }

        void bind(FavoriteDBModel item, OnItemClickListener onItemClickListener) {

            statLayout.setVisibility(View.GONE);
            vLine.setVisibility(View.GONE);
            authorName.setText(Html.fromHtml(item.getName()));
            questionName.setText(Html.fromHtml(item.getTitle()));
            questionItem.setOnClickListener(v -> onItemClickListener.onItemClick(item));
        }
    }
}
