package base.common.BottomView;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;

import base.common.R;

/**
 * Created by wangyang on 2019/4/26.
 */

public class AppBottomView extends LinearLayout {
    private Context context;
    private ColorStateList mTextColor;

    private AppBottomViewOnClickListener appBottomViewOnClickListener;

    private LinkedList<AppBotState> appBotStates = new LinkedList<>();

    public AppBottomView(Context context) {
        this(context, null);
    }

    public AppBottomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public AppBottomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AppBottomView);
            mTextColor = typedArray.getColorStateList(R.styleable.AppBottomView_AppBottomView_Button_Text_Color);
            typedArray.recycle();
        }

        if (mTextColor == null) {
            mTextColor = ContextCompat.getColorStateList(context, R.color.color_text_app);
        }
        initView();
    }

    private void initView() {
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setOrientation(HORIZONTAL);
        setBackgroundColor(ContextCompat.getColor(context, R.color.color_ffffff));
    }


    /**
     * 需要添加对应的图片，文字
     *
     * @param attributes
     * @return
     */
    public AppBottomView init(LinkedList<AppBotViewAttribute> attributes) {
        if (attributes == null) {
            throw new RuntimeException("初始化的数据问题：请注意init()这个方法传的参数！！！！");
        }
        appBotStates.clear();
        removeAllViews();
        for (int i = 0; i < attributes.size(); i++) {
            final LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_app_bottom_view, null);
            StateListDrawable stateListDrawable = new StateListDrawable();
            Drawable normal = AppCompatResources.getDrawable(context, attributes.get(i).getNormalImage());
            Drawable press = AppCompatResources.getDrawable(context, attributes.get(i).getPressImage());
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, press);
            stateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, press);
            stateListDrawable.addState(new int[]{}, normal);
            ImageView imageView = linearLayout.findViewById(R.id.app_item);
            imageView.setImageDrawable(stateListDrawable);
            TextView textView = linearLayout.findViewById(R.id.app_text);
            textView.setText(attributes.get(i).getText());
            textView.setTextColor(mTextColor);
            appBotStates.add(new AppBotState(linearLayout, imageView, textView));
            final int position = i;
            linearLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (appBottomViewOnClickListener != null && appBotStates.size() > position) {
                        changeState(position);
                        appBottomViewOnClickListener.appBottomViewClick(position);
                    }
                }
            });
            linearLayout.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            addView(linearLayout);
        }
        return this;
    }

    public AppBottomView setAppBottomViewOnClickListener(AppBottomViewOnClickListener appBottomViewOnClickListener) {
        this.appBottomViewOnClickListener = appBottomViewOnClickListener;
        return this;
    }

    public AppBottomView changeState(int position) {
        if (appBotStates == null || appBotStates.size() <= position) {
            return this;
        }
        for (int i = 0; i < appBotStates.size(); i++) {
            if (position == i) {
                appBotStates.get(i).getLinearLayout().setEnabled(false);
                appBotStates.get(i).getImageView().setEnabled(false);
                appBotStates.get(i).getTextView().setEnabled(false);
            } else {
                appBotStates.get(i).getLinearLayout().setEnabled(true);
                appBotStates.get(i).getImageView().setEnabled(true);
                appBotStates.get(i).getTextView().setEnabled(true);
            }
        }
        return this;
    }

    public interface AppBottomViewOnClickListener {
        void appBottomViewClick(int position);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeAllViews();
        if (appBotStates != null) {
            appBotStates.clear();
        }
    }
}
