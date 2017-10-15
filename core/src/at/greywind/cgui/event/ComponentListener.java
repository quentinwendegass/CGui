package at.greywind.cgui.event;

import at.greywind.cgui.component.CComponent;

public interface ComponentListener extends CEventListener {

    default void resized(int width, int height){}

    default void moved(int x, int y){}

    default void added(CComponent parent){}
}
