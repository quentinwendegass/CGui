package at.greywind.cgui.layout;

import at.greywind.cgui.component.CComponent;

public interface Layoutable {

    enum Alignment{
        LEFT, RIGHT, BOTTOM, TOP, CENTER
    }

    void setLayout(CLayout layout);

    CLayout getLayout();

    Layoutable layout(CComponent component);

    Layoutable width(float width);

    Layoutable height(float height);

    Layoutable margin(float margin);

    Layoutable marginTop(float margin);

    Layoutable marginBottom(float margin);

    Layoutable marginRight(float margin);

    Layoutable marginLeft(float margin);

    Layoutable padding(float padding);

    Layoutable paddingTop(float padding);

    Layoutable paddingBottom(float padding);

    Layoutable paddingRight(float padding);

    Layoutable paddingLeft(float padding);
}
