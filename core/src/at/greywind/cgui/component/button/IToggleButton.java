package at.greywind.cgui.component.button;

public interface IToggleButton extends IButton{

    @Override
    boolean isPressed();

    void setPressed(boolean pressed);

    void toggle();

    void setPressedWithoutEvent(boolean pressed);
}
