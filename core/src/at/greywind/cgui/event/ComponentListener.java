package at.greywind.cgui.event;

import at.greywind.cgui.component.CComponent;

public interface ComponentListener extends CEventListener {

    default void resized(int width, int height, ComponentEvent event){}

    default void moved(int x, int y, ComponentEvent event){}

    default void added(CComponent parent, ComponentEvent event){}

    default void hidden(ComponentEvent event){}

    default void shown(ComponentEvent event){}

}
