package tp.xmaihh.trainingthings.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.drakeet.multitype.ItemViewBinder;
import tp.xmaihh.trainingthings.R;

public class HorizontalPostViewBinder extends ItemViewBinder<PostList, HorizontalPostViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_horizontal_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull PostList item) {
        holder.setPosts(item.posts);
        assertGetAdapterNonNull();
    }

    private void assertGetAdapterNonNull() {
        // noinspection ConstantConditions
        if (getAdapter() == null) {
            throw new NullPointerException("getAdapter() == null");
        }
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        private PostAdapter postAdapter;

        public ViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.post_list);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
            new LinearSnapHelper().attachToRecyclerView(recyclerView);
            postAdapter = new PostAdapter();
            recyclerView.setAdapter(postAdapter);
        }

        private void setPosts(List<Post> posts) {
            postAdapter.setPosts(posts);
            postAdapter.notifyDataSetChanged();
        }
    }
}
