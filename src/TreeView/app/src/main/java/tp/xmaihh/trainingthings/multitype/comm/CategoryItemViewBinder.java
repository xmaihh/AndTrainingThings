package tp.xmaihh.trainingthings.multitype.comm;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.drakeet.multitype.ItemViewBinder;
import tp.xmaihh.trainingthings.R;

public class CategoryItemViewBinder extends ItemViewBinder<Category, CategoryItemViewBinder.ViewHolder> {

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_category, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Category category) {
        holder.title.setText(category.title);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private @NonNull
        final TextView title;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }
}
