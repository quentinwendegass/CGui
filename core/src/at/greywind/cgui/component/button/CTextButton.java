package at.greywind.cgui.component.button;

import at.greywind.cgui.event.ChangeEvent;
import at.greywind.cgui.text.CFont;

public class CTextButton extends TextButton implements IButton {

    public CTextButton(String text, CFont font, int x, int y) {
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
    public void touchUp(int x, int y) {
        if(isEnabled) {
            setUp();
        }
    }

    @Override
    public void touchDown(int x, int y) {
        if(isEnabled) {
            setDown();
        }
    }

    @Override
    public void exit() {
        if(isEnabled) {
            if (pressed) {
                setUp();
            }
        }
    }

    private void setUp(){
        setBackground(upBackground);
        setBorder(upBorder);
        pressed = false;
        addEventToQueue(new ChangeEvent(pressed, this));
    }

    private void setDown(){
        setBackground(downBackground);
        setBorder(downBorder);
        pressed = true;
        addEventToQueue(new ChangeEvent(pressed, this));
    }
}
