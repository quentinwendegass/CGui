package at.greywind.cgui.component.button;

import at.greywind.cgui.event.ClickEvent;
import at.greywind.cgui.event.ClickListener;
import at.greywind.cgui.event.MouseEvent;
import at.greywind.cgui.event.MouseListener;

public interface IButton extends ClickListener, MouseListener {

    boolean isPressed();

    @Override
    default void clicked(int x, int y, ClickEvent e){}

    @Override
    default void pressed(int x, int y, ClickEvent e){}

    @Override
    void touchUp(int x, int y, ClickEvent e);

    @Override
    void touchDown(int x, int y, ClickEvent e);

    @Override
    default void enter(MouseEvent e){}

    @Override
    void exit(MouseEvent e);

    @Override
    default void move(int x, int y, MouseEvent e){}
}
