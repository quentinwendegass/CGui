package at.greywind.cgui.component.button;

import at.greywind.cgui.Icon;
import at.greywind.cgui.event.ChangeEvent;

public class CIconButton extends IconButton implements IButton{

    public CIconButton(Icon upIcon, Icon downIcon, int x, int y) {
        super(upIcon, downIcon, x, y);

        addMouseListener(this);
        addClickListener(this);
    }

    @Override
    public boolean isPressed() {
        return pressed;
    }


    @Override
    public void touchUp(int x, int y) {
        if(isEnabled){
            setIcon(upIcon);
            addEventToQueue(new ChangeEvent(pressed, this));
        }
    }

    @Override
    public void touchDown(int x, int y) {
        if(isEnabled) {
            setIcon(downIcon);
            addEventToQueue(new ChangeEvent(pressed, this));
        }
    }

    @Override
    public void exit() {
        if(isEnabled){
            if(pressed) {
                setIcon(upIcon);
                addEventToQueue(new ChangeEvent(pressed, this));
            }
        }
    }
}
