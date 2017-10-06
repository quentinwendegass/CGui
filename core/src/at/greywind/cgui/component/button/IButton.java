package at.greywind.cgui.component.button;

import at.greywind.cgui.event.ClickListener;
import at.greywind.cgui.event.MouseListener;

public interface IButton extends ClickListener, MouseListener {

    boolean isPressed();

    @Override
    default void clicked(int x, int y){}

    @Override
    default void pressed(int x, int y){}

    @Override
    void touchUp(int x, int y);

    @Override
    void touchDown(int x, int y);

    @Override
    default void enter(){}

    @Override
    void exit();

    @Override
    default void move(int x, int y){}
}
