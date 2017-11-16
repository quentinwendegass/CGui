package at.greywind.cgui.component.button;

import at.greywind.cgui.event.ChangeEvent;
import at.greywind.cgui.event.ClickEvent;
import at.greywind.cgui.event.MouseEvent;
import at.greywind.cgui.text.CFont;

public class CToggleTextButton extends TextButton implements IToggleButton {

    private boolean touchDown = false;

    public CToggleTextButton(CFont font){
        this("", font);
    }

    public CToggleTextButton(String text, CFont font){
        this(text, font, 0, 0);
    }

    public CToggleTextButton(String text, CFont font, int x, int y){
        this(text, font, x, y, 0,0);
    }

    public CToggleTextButton(String text, CFont font, int x, int y, int width, int height) {
        super(text, font, x, y, width,height);

        setBackground(upBackground);
        setBorder(upBorder);
    }

    @Override
    public void mouseExit() {
        if(isEnabled){
            set();
            touchDown = false;
        }
    }

    @Override
    public void mouseEnter() {
        if(isEnabled && !pressed){
            setBackground(mouseOverBackground);
            setBorder(mouseOverBorder);
        }
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
    public void touchUp(int x, int y, ClickEvent e) {
        if(isEnabled && touchDown) {
            pressed = !pressed;
            set();
            touchDown = false;
            addEventToQueue(new ChangeEvent(pressed, this));
        }
    }

    @Override
    public void touchDown(int x, int y, ClickEvent e) {
        if(isEnabled) {
            setBackground(downBackground);
            setBorder(downBorder);
            touchDown = true;
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
