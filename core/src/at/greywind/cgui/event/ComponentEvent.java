package at.greywind.cgui.event;

import at.greywind.cgui.component.CComponent;

public class ComponentEvent extends AbstractEvent<ComponentListener> {

    public enum Type{
        RESIZED, MOVED, ADDED, HIDDEN, SHOWN
    }
    private int arg1, arg2;
    private Type type;
    private CComponent component = null;

    public ComponentEvent(CComponent component, Type type){
       this(component, 0,0, type);
    }

    public ComponentEvent(CComponent component, int arg1, int arg2, Type type){
        super(component);
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.type = type;
    }

    @Override
    public void fire(ComponentListener listener) {
        if(type == Type.RESIZED)
            listener.resized(arg1, arg2, this);
        else if(type == Type.MOVED)
            listener.moved(arg1, arg2, this);
        else if(type == Type.ADDED)
            listener.added(component, this);
        else if(type == Type.SHOWN)
            listener.shown(this);
        else if(type == Type.HIDDEN)
            listener.hidden(this);
    }
}
