package at.greywind.cgui.component;

import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CColorGradient;

public class SliderStyle {

    private static final CColorGradient DEFAULT_BACKGROUND_COLOR = new CColorGradient(CColor.GREY);
    private static final CColor DEFAULT_INDICATOR_COLOR = CColor.BLACK;
    private static final int DEFAULT_THICKNESS = 30;


    private CColorGradient sliderBackgroundColor = DEFAULT_BACKGROUND_COLOR;
    private CColor sliderIndicatorColor = DEFAULT_INDICATOR_COLOR;


    /**
     * In Prozent % (0-100)
     */
    private float sliderThickness;
    private float indicatorWidth;
    private float indicatorHeight;

    public SliderStyle() {
        this(DEFAULT_THICKNESS);
    }

    public SliderStyle(float sliderThickness) {
        this(sliderThickness, 100, DEFAULT_THICKNESS);
    }

    public SliderStyle(float sliderThickness, float indicatorHeight, float indicatorWidth) {
        this(new CColorGradient(CColor.GREY), CColor.BLACK, sliderThickness, indicatorHeight, indicatorWidth);
    }

    public SliderStyle(CColorGradient sliderBackgroundColor, CColor sliderIndicatorColor, float sliderThickness, float indicatorHeight, float indicatorWidth) {
        this.sliderBackgroundColor = sliderBackgroundColor;
        this.sliderIndicatorColor = sliderIndicatorColor;
        this.sliderThickness = sliderThickness;
        this.indicatorHeight = indicatorHeight;
        this.indicatorWidth = indicatorWidth;
    }

    public CColorGradient getSliderColor() {
        return sliderBackgroundColor;
    }

    public void setSliderColor(CColorGradient sliderBackgroundColor) {
        this.sliderBackgroundColor = sliderBackgroundColor;
    }

    public CColor getIndicatorColor() {
        return sliderIndicatorColor;
    }

    public void setIndicatorColor(CColor sliderIndicatorColor) {
        this.sliderIndicatorColor = sliderIndicatorColor;
    }

    public float getThickness(float height) {
        return sliderThickness / 100 * height;
    }

    public float getThickness() {
        return sliderThickness;
    }

    public void setThickness(float sliderThickness) {
        this.sliderThickness = sliderThickness;
    }

    public float getIndicatorWidth(float height) {
        return indicatorWidth / 100 * height;
    }

    public float getIndicatorWidth() {
        return indicatorWidth;
    }

    public void setIndicatorWidth(float indicatorWidth) {
        this.indicatorWidth = indicatorWidth;
    }

    public float getIndicatorHeight(float height) {
        return indicatorHeight / 100 * height;
    }

    public float getIndicatorHeight() {
        return indicatorHeight;
    }

    public void setIndicatorHeight(float indicatorHeight) {
        this.indicatorHeight = indicatorHeight;
    }
}
