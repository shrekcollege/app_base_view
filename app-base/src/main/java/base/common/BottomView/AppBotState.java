package base.common.BottomView;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Yondervision on 2019/4/26.
 */

public class AppBotState {
    private LinearLayout linearLayout;
    private ImageView imageView;
    private TextView textView;

    public AppBotState() {
    }

    public AppBotState(LinearLayout linearLayout, ImageView imageView, TextView textView) {
        this.linearLayout = linearLayout;
        this.imageView = imageView;
        this.textView = textView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }
}
