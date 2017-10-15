package at.greywind.cgui.event;

import at.greywind.cgui.component.CComponent;

public class ComponentEvent implements CEvent<ComponentListener> {

    public enum Type{
        RESIZED, MOVED, ADDED
    }
    private int arg1, arg2;
    private Type type;
    private CComponent component = null;

    public ComponentEvent(int arg1, int arg2, Type type){
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.type = type;
    }

    public ComponentEvent(CComponent component){
        this.type = Type.ADDED;
        this.component = component;
    }

    @Override
    public void fire(ComponentListener listener) {
        if(type == Type.RESIZED)
            listener.resized(arg1, arg2);
        else if(type == Type.MOVED)
            listener.moved(arg1, arg2);
        else if(type == Type.ADDED && component != null)
            listener.added(component);
    }
}
