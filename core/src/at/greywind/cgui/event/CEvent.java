package at.greywind.cgui.event;

public interface CEvent<T extends CEventListener> {

    void fire(T listener);
}
