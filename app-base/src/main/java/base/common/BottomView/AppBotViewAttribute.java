package base.common.BottomView;

/**
 * Created by Yondervision on 2019/4/26.
 */

public class AppBotViewAttribute {
    private int pressImage;
    private int normalImage;


    private String text;

    public AppBotViewAttribute(int pressImage, int normalImage, String text) {
        this.pressImage = pressImage;
        this.normalImage = normalImage;
        this.text = text;
    }

    public int getPressImage() {
        return pressImage;
    }

    public void setPressImage(int pressImage) {
        this.pressImage = pressImage;
    }

    public int getNormalImage() {
        return normalImage;
    }

    public void setNormalImage(int normalImage) {
        this.normalImage = normalImage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
