package at.greywind.cgui.component.button;

import at.greywind.cgui.event.ClickEvent;
import at.greywind.cgui.event.MouseEvent;
import at.greywind.cgui.util.Icon;
import at.greywind.cgui.event.ChangeEvent;

public class CIconButton extends IconButton implements IButton{

    public CIconButton(Icon upIcon, Icon downIcon) {
        this(upIcon, downIcon, 0,0);
    }

    public CIconButton(Icon upIcon, Icon downIcon, int x, int y) {
        this(upIcon, downIcon, x, y, 0,0);
    }

    public CIconButton(Icon upIcon, Icon downIcon, int x, int y, int width, int height) {
        super(upIcon, downIcon, x, y, width, height);

        addMouseListener(this);
        addClickListener(this);
    }

    @Override
    public boolean isPressed() {
        return pressed;
    }


    @Override
    public void touchUp(int x, int y, ClickEvent e) {
        if(isEnabled){
            setIcon(upIcon);
            addEventToQueue(new ChangeEvent(pressed, this));
        }
    }

    @Override
    public void touchDown(int x, int y, ClickEvent e) {
        if(isEnabled) {
            setIcon(downIcon);
            addEventToQueue(new ChangeEvent(pressed, this));
        }
    }

    @Override
    public void enter(MouseEvent e){
        if(isEnabled){
            if(!pressed) {
                setIcon(mouseOverIcon);
            }
        }
    }

    @Override
    public void exit(MouseEvent e) {
        if(isEnabled){
            setIcon(upIcon);

            if(pressed){
                pressed = false;
                addEventToQueue(new ChangeEvent(pressed, this));
            }

        }
    }
}
