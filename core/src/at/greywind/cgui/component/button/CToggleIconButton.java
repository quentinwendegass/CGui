package at.greywind.cgui.component.button;

import at.greywind.cgui.util.Icon;
import at.greywind.cgui.event.ChangeEvent;

public class CToggleIconButton extends IconButton implements IToggleButton {

    public CToggleIconButton(Icon upicon, Icon downIcon) {
        this(upicon, downIcon, 0, 0);
    }


    public CToggleIconButton(Icon upicon, Icon downIcon, int x, int y) {
        this(upicon, downIcon, x, y, 0,0);
    }


    public CToggleIconButton(Icon upicon, Icon downIcon, int x, int y, int width, int height) {
        super(upicon, downIcon, x, y, width, height);

        addMouseListener(this);
        addClickListener(this);
    }

    @Override
    public boolean isPressed() {
        return pressed;
    }

    @Override
    public void setPressed(boolean pressed) {
        if(isEnabled) {
            this.pressed = pressed;
            set();
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
        if(isEnabled){
            this.pressed = pressed;
            set();
            addEventToQueue(new ChangeEvent(pressed, this));
        }
    }

    @Override
    public void touchUp(int x, int y) {
        if(isEnabled){
            pressed = !pressed;
            set();
            addEventToQueue(new ChangeEvent(pressed, this));
        }
    }

    @Override
    public void touchDown(int x, int y) {
        if(isEnabled){
            setIcon(downIcon);
        }
    }

    @Override
    public void exit() {
        if(isEnabled){
            if(!pressed){
                setIcon(upIcon);
            }
        }
    }

    private void set(){
        if(pressed){
            setIcon(downIcon);
        }else{
            setIcon(upIcon);
        }
    }
}
