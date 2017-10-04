package at.greywind.cgui.cevent;

public interface CEvent<T extends CEventListener> {

    void fire(T listener);
}
