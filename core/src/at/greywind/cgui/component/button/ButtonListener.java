package at.greywind.cgui.component.button;

import at.greywind.cgui.event.ClickListener;

public interface ButtonListener extends ClickListener{

    @Override
    default void pressed(int x, int y){}

    @Override
    default void touchUp(int x, int y){}

    @Override
    default void touchDown(int x, int y){}
}
