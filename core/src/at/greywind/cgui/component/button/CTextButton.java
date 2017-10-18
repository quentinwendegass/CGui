package at.greywind.cgui.component.button;

import at.greywind.cgui.event.ChangeEvent;
import at.greywind.cgui.event.ClickEvent;
import at.greywind.cgui.event.MouseEvent;
import at.greywind.cgui.text.CFont;

public class CTextButton extends TextButton implements IButton {

    public CTextButton(CFont font) {
        this("", font);
    }

    public CTextButton(String text, CFont font) {
        this(text, font, 0, 0);
    }

    public CTextButton(String text, CFont font, int x, int y) {
        this(text, font, x, y, 0, 0);
    }

    public CTextButton(String text, CFont font, int x, int y, int width, int height) {
        super(text, font, x, y, width,height);

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
    public void touchUp(int x, int y, ClickEvent e) {
        if(isEnabled) {
            setUp();
        }
    }

    @Override
    public void touchDown(int x, int y, ClickEvent e) {
        if(isEnabled) {
            setDown();
        }
    }

    @Override
    public void enter(MouseEvent e) {
        if(isEnabled){
            if(!pressed){
                setBackground(mouseOverBackground);
                setBorder(mouseOverBorder);
            }
        }
    }

    @Override
    public void exit(MouseEvent e) {
        if(isEnabled) {
            setBackground(upBackground);
            setBorder(upBorder);

            if (pressed) {
                pressed = false;
                addEventToQueue(new ChangeEvent(pressed, this));
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
