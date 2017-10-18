package at.greywind.cgui.component;

import at.greywind.cgui.event.ClickEvent;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;
import com.badlogic.gdx.Gdx;

public class CSlider extends CComponent{

    private SliderStyle style;

    private float maxValue;
    private float minValue;

    private int value = 50;

    private boolean sliderMoved = false;

    private float paddingHorizontal = 0;
    private float paddingVertical = 0;

    private float sliderLength;


    public CSlider(){
        this(0, 0);
    }

    public CSlider(int x, int y) {
        this(x,y, 0, 100);
    }

    public CSlider(int x, int y, int minValue, int maxValue) {
        this(x,y, 100, 20, minValue, maxValue, new SliderStyle());
    }

    public CSlider(int x, int y, int width, int height, int minValue, int maxValue, SliderStyle style) {
        super();
        setPosition(x, y);
        setSize(width, height);
        setBoundaries(minValue, maxValue);
        setBackground(CColor.CLEAR);
        setStyle(style);
    }

    @Override
    public void updateComponent(CGraphics g) {
        super.updateComponent(g);

        if(!Gdx.input.isTouched() && sliderMoved) sliderMoved = false;

        if(sliderMoved){
            float indicatorX = Gdx.input.getX() - getWindowX() + style.getIndicatorWidth(getHeight()) / 2;
            sliderLength = getWidth() - style.getIndicatorWidth(getHeight());

            value = (int)(indicatorX / sliderLength * (maxValue - minValue));
        }

        if(value > maxValue) value = (int)maxValue;
        else if(value < minValue) value = (int)minValue;
    }

    @Override
    public void drawComponent(CGraphics g) {
        super.drawComponent(g);

        sliderLength = getWidth() - style.getIndicatorWidth(getHeight());
        float indicatorX = value / (maxValue - minValue) * sliderLength;

        g.setColor(style.getSliderColor());
        g.drawFilledRect(style.getIndicatorWidth(getHeight()) / 2 + paddingHorizontal, (style.getIndicatorHeight(getHeight()) - style.getThickness(getHeight())) / 2 + paddingVertical,
                getWidth() - style.getIndicatorWidth(getHeight()), style.getThickness(getHeight()));

        g.setColor(style.getIndicatorColor());
        g.drawFilledRect(indicatorX + paddingHorizontal, paddingVertical, style.getIndicatorWidth(getHeight()), style.getIndicatorHeight(getHeight()));
    }

    @Override
    public void touchDown(int x, int y, ClickEvent e) {
        sliderMoved = true;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public void setBoundaries(int minValue, int maxValue){
        setMaxValue(maxValue);
        setMinValue(minValue);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public SliderStyle getStyle() {
        return style;
    }

    public void setStyle(SliderStyle style) {
        this.style = style;
    }

}
