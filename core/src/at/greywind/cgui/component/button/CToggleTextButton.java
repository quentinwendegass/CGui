package at.greywind.cgui.component.button;

import at.greywind.cgui.event.ChangeEvent;
import at.greywind.cgui.text.CFont;

public class CToggleTextButton extends TextButton implements IToggleButton {

    public CToggleTextButton(String text, CFont font, int x, int y) {
        super(text, font, x, y, 0,0);

        setBackground(upBackground);
        setBorder(upBorder);

        addMouseListener(this);
        addClickListener(this);
    }

    @Override
    public boolean isPressed() {
        return pressed;
    }

    @Override
    public void setPressed(boolean pressed) {
        if(isEnabled){
            this.pressed = pressed;
            set();
            addEventToQueue(new ChangeEvent(pressed, this));
        }
    }

    @Override
    public void toggle() {
        if(isEnabled) {
            pressed = !pressed;
            set();
            addEventToQueue(new ChangeEvent(pressed, this));
        }
    }

    @Override
     public void setPressedWithoutEvent(boolean pressed) {
        if(isEnabled) {
            this.pressed = pressed;
            set();
        }
    }

    @Override
    public void touchUp(int x, int y) {
        if(isEnabled) {
            pressed = !pressed;
            set();
            addEventToQueue(new ChangeEvent(pressed, this));
        }
    }

    @Override
    public void touchDown(int x, int y) {
        if(isEnabled) {
            setBackground(downBackground);
            setBorder(downBorder);
        }
    }

    @Override
    public void exit() {
        if(isEnabled) {
            if (!pressed) {
                setBackground(upBackground);
                setBorder(upBorder);
            }
        }
    }

    private void set(){
        if(pressed){
            setBackground(downBackground);
            setBorder(downBorder);
        }else{
            setBackground(upBackground);
            setBorder(upBorder);
        }
    }
}
