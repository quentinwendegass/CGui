package at.greywind.cgui.component.button;

import at.greywind.cgui.event.ClickEvent;
import at.greywind.cgui.event.MouseEvent;
import at.greywind.cgui.util.Icon;
import at.greywind.cgui.event.ChangeEvent;

public class CIconButton extends IconButton implements IButton{

    public CIconButton(Icon icon) {
        this(icon, icon, 0,0);
    }

    public CIconButton(Icon upIcon, Icon downIcon) {
        this(upIcon, downIcon, 0,0);
    }

    public CIconButton(Icon upIcon, Icon downIcon, int x, int y) {
        this(upIcon, downIcon, x, y, 0,0);
    }

    public CIconButton(Icon upIcon, Icon downIcon, int x, int y, int width, int height) {
        super(upIcon, downIcon, x, y, width, height);

    }

    @Override
    public void mouseExit() {
        if(isEnabled){
            setIcon(upIcon);
            setBackground(upColor);

            if(pressed){
                pressed = false;
                addEventToQueue(new ChangeEvent(pressed, this));
            }

        }
    }

    @Override
    public void mouseEnter() {
        if(isEnabled){
            if(!pressed) {
                setIcon(mouseOverIcon);
                setBackground(mouseOverColor);
            }
        }
    }

    @Override
    public boolean isPressed() {
        return pressed;
    }


    @Override
    public void touchUp(int x, int y, ClickEvent e) {
        if(isEnabled){
            setIcon(mouseOverIcon);
            setBackground(mouseOverColor);
            addEventToQueue(new ChangeEvent(pressed, this));
        }
    }

    @Override
    public void touchDown(int x, int y, ClickEvent e) {
        if(isEnabled) {
            setIcon(downIcon);
            setBackground(downColor);
            addEventToQueue(new ChangeEvent(pressed, this));
        }
    }
}
