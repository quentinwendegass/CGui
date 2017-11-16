package at.greywind.cgui.component.button;

import at.greywind.cgui.event.ClickEvent;
import at.greywind.cgui.event.MouseEvent;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.util.Icon;
import at.greywind.cgui.event.ChangeEvent;

public class CToggleIconButton extends IconButton implements IToggleButton {

    private boolean touchDown = false;

    public CToggleIconButton(Icon icon) {
        this(icon, icon, 0, 0);
    }

    public CToggleIconButton(Icon upIcon, Icon downIcon) {
        this(upIcon, downIcon, 0, 0);
    }

    public CToggleIconButton(Icon upIcon, Icon downIcon, int x, int y) {
        this(upIcon, downIcon, x, y, 0,0);
    }

    public CToggleIconButton(Icon upIcon, Icon downIcon, int x, int y, int width, int height) {
        super(upIcon, downIcon, x, y, width, height);

        setBackground(upColor);
        setIcon(upIcon);
    }

    @Override
    public void mouseEnter(){
        if(!pressed && isEnabled) {
            setIcon(mouseOverIcon);
            setBackground(mouseOverColor);
        }
    }

    @Override
    public void mouseExit(){
        if(isEnabled){
            set();
            touchDown = false;
        }
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
        if(isEnabled){
            this.pressed = pressed;
            set();
        }
    }

    @Override
    public void touchUp(int x, int y, ClickEvent e) {
        if(isEnabled && touchDown){
            pressed = !pressed;
            set();
            addEventToQueue(new ChangeEvent(pressed, this));
            touchDown = false;
        }
    }

    @Override
    public void touchDown(int x, int y, ClickEvent e) {
        if(isEnabled){
            setIcon(downIcon);
            setBackground(downColor);
            touchDown = true;
        }
    }

    private void set(){
        if(pressed){
            setIcon(downIcon);
            setBackground(downColor);
        }else{
            setIcon(upIcon);
            setBackground(upColor);
        }
    }
}
