package at.greywind.cgui.event;

import at.greywind.cgui.component.CComponent;

public abstract class AbstractEvent<T extends CEventListener> implements CEvent<T>{

    protected CComponent component;

    public AbstractEvent(CComponent cComponent){
        this.component = cComponent;
    }

    public CComponent getComponent() {
        return component;
    }

    public void setComponent(CComponent component){
        this.component = component;
    }
}
