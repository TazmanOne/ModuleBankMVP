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
import test.mvptestapp.model.retrofit.AnswerModel;


public class SearchRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AnswerModel.Items> posts;
    private final OnItemClickListener listener;

    public SearchRecyclerAdapter(OnItemClickListener listener) {
        posts = new ArrayList<>();
        this.listener = listener;
    }

    public void setPosts(List<AnswerModel.Items> posts) {
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_item, parent, false);
        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MainViewHolder vh = (MainViewHolder) holder;
        vh.bind(posts.get(position), listener);
    }

//        LocalViewHolder lvh = (LocalViewHolder) holder;
//                lvh.statLayout.setVisibility(View.GONE);
//                lvh.vLine.setVisibility(View.GONE);
//                lvh.authorName.setText(postsCopy.get(position).getOwner().getDisplayName());
//                lvh.questionName.setText(Html.fromHtml(postsCopy.get(position).getTitle()));
//                lvh.questionItem.setOnClickListener(v -> {
//                    BrowseAnswerFragment fragment = new BrowseAnswerFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("link", postsCopy.get(position).getLink());
//                    bundle.putString("title", postsCopy.get(position).getTitle());
//                    bundle.putString("name", postsCopy.get(position).getOwner().getDisplayName());
//                    fragment.setArguments(bundle);
//                    FragmentManager manager = MainActivity.getInstance().getFragmentManager();
//                    manager.beginTransaction()
//                            .replace(R.id.container, fragment, BrowseAnswerFragment.class.getSimpleName())
//                            .addToBackStack(null)
//                            .commit();
//                      MainActivity.getInstance().searchView.clearFocus();
//
//                });

    @Override
    public int getItemCount() {
        if (posts == null)
            return 0;
        return posts.size();
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

//    public void clear() {
//        this.postsCopy.clear();
//    }
//
//    public void filter(String text) {
//        postsCopy.clear();
//        if (text.isEmpty()) {
//            postsCopy.addAll(posts);
//        } else {
//            text = text.toLowerCase();
//            for (AnswerModel.Items item : posts) {
//                if (item.getTitle().toLowerCase().contains(text)) {
//                    postsCopy.add(item);
//                }
//            }
//        }
//    }

    private class MainViewHolder extends RecyclerView.ViewHolder {
        TextView votesCount;
        TextView answersCount;
        TextView viewsCount;
        TextView questionName;
        TextView authorName;
        LinearLayout questionItem;

        MainViewHolder(View itemView) {
            super(itemView);
            questionName = (TextView) itemView.findViewById(R.id.questionName);
            authorName = (TextView) itemView.findViewById(R.id.authorName);
            viewsCount = (TextView) itemView.findViewById(R.id.views_count);
            answersCount = (TextView) itemView.findViewById(R.id.answers_count);
            votesCount = (TextView) itemView.findViewById(R.id.votes_count);
            questionItem = (LinearLayout) itemView.findViewById(R.id.answerItemLayout);
        }

        public void bind(AnswerModel.Items item, OnItemClickListener onItemClickListener) {
            answersCount.setText(String.valueOf(item.getAnswerCount()));
            authorName.setText(Html.fromHtml(item.getOwner().getDisplayName()));
            questionName.setText(Html.fromHtml(item.getTitle()));
            votesCount.setText(String.valueOf(item.getScore()));
            viewsCount.setText(String.valueOf(item.getViewCount()));
            questionItem.setOnClickListener(v -> onItemClickListener.onItemClick(item));
        }
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
    }
}

