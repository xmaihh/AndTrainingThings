package tp.xmaihh.trainingthings.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import tp.xmaihh.trainingthings.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> posts = Collections.emptyList();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.cover.setImageResource(post.coverResId);
        holder.title.setText(post.title);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(@NonNull List<Post> posts) {
        this.posts = posts;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        ImageView cover;
        @NonNull
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.cover);
            title = itemView.findViewById(R.id.title);
            itemView.setOnClickListener(
                    v -> Toast.makeText(v.getContext(), String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show());
        }
    }
}
