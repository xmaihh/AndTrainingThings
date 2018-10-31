package tp.xmaihh.trainingthings.multitype;

import android.support.annotation.NonNull;

public class Post {
    public int coverResId;

    @NonNull
    public String title;

    public Post(int coverResId, String title) {
        this.coverResId = coverResId;
        this.title = title;
    }
}
