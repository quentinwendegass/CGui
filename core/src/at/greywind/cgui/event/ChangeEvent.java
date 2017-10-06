package at.greywind.cgui.event;


import at.greywind.cgui.component.CComponent;

public class ChangeEvent extends AbstractEvent<ChangeListener>  {

    private boolean changedValue;

    public ChangeEvent(boolean changedValue, CComponent changedComponent) {
        super(0, 0, changedComponent);
        this.changedValue = changedValue;
    }

    @Override
    public void fire(ChangeListener listener) {
        listener.changed(changedValue, component);
    }
}
