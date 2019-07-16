package tp.xmaihh.trainingthings.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.drakeet.multitype.ItemViewBinder;
import tp.xmaihh.trainingthings.R;

public class PostViewBinder extends ItemViewBinder<Post, PostViewBinder.ViewHolder> {


    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_post, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Post item) {
        holder.setData(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView cover;

        ViewHolder(View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.cover);
            title = itemView.findViewById(R.id.title);
        }

        void setData(Post post) {
            cover.setImageResource(post.coverResId);
            title.setText(post.title);
        }
    }
}
