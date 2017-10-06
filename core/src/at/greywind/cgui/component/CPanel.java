package at.greywind.cgui.component;

import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.layout.CLayout;
import at.greywind.cgui.layout.Layoutable;

public class CPanel extends CComponent implements Layoutable, Debugable {

    private CLayout layout;

    private boolean debugMode = false;

    public CPanel(int x, int y, int width, int height){
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void updateComponent(CGraphics g) {
        super.updateComponent(g);
        if(layout != null && debugMode) layout.debugDraw(g);
    }

    @Override
    public void setLayout(CLayout layout) {
        this.layout = layout;
        layout.setLayoutable(this);
    }

    @Override
    public CLayout getLayout() {
        return layout;
    }

    @Override
    public Layoutable layout(CComponent component) {
        add(component);
        layout.set(component);
        return this;
    }

    @Override
    public Layoutable width(float width) {
        layout.width(width);
        return this;
    }

    @Override
    public Layoutable height(float height) {
        layout.height(height);
        return this;
    }

    @Override
    public Layoutable margin(float margin) {
        layout.marginBottom(margin);
        layout.marginTop(margin);
        layout.marginLeft(margin);
        layout.marginRight(margin);
        return this;
    }

    @Override
    public Layoutable marginTop(float margin) {
        layout.marginTop(margin);
        return this;
    }

    @Override
    public Layoutable marginBottom(float margin) {
        layout.marginBottom(margin);
        return this;
    }

    @Override
    public Layoutable marginRight(float margin) {
        layout.marginRight(margin);
        return this;
    }

    @Override
    public Layoutable marginLeft(float margin) {
        layout.marginLeft(margin);
        return this;
    }

    @Override
    public Layoutable padding(float padding) {
        layout.paddingBottom(padding);
        layout.paddingTop(padding);
        layout.paddingLeft(padding);
        layout.paddingRight(padding);
        return this;
    }

    @Override
    public Layoutable paddingTop(float padding) {
        layout.paddingTop(padding);
        return this;
    }

    @Override
    public Layoutable paddingBottom(float padding) {
        layout.paddingBottom(padding);
        return this;
    }

    @Override
    public Layoutable paddingRight(float padding) {
        layout.paddingRight(padding);
        return this;
    }

    @Override
    public Layoutable paddingLeft(float padding) {
        layout.paddingLeft(padding);
        return this;
    }

    @Override
    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    @Override
    public boolean getDebugMode() {
        return debugMode;
    }
}
