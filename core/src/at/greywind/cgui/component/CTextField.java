package at.greywind.cgui.component;

import at.greywind.cgui.event.ClickEvent;
import at.greywind.cgui.event.KeyEvent;
import at.greywind.cgui.event.KeyListener;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.text.CFont;

import java.security.Key;

public class CTextField extends TextComponent implements KeyListener{

    private final static int STANDARD_PADDING = 5;

    private int charOffset = 0;

    private float horizontalPadding;

    private boolean allowOnlyNumeric = false;

    private int maxCharacters = 500;


    public CTextField(CFont font){
        this(font, 0, 0);
    }

    public CTextField(CFont font, int x, int y){
        this(font, x, y, 0, 0);
    }

    public CTextField(CFont font, int x, int y, int width, int height){
      super(font, x, y, width, height);

        if(width == 0|| height == 0){
            setWidth(100);
            setHeight((int) (font.getHeight(text) + 2* STANDARD_PADDING));
        }else{
            setWidth(width);
            setHeight(height);
        }

        horizontalPadding = STANDARD_PADDING;

      addKeyListener(this);
    }


    @Override
    public void drawComponent(CGraphics g) {
        super.drawComponent(g);

        g.setFont(font);

        if(font.getHeight(text) < getHeight()){

            g.drawText(text.substring(charOffset), horizontalPadding, getHeight() / 2 + font.getHeight(text) / 2);

            if(drawCursor && cursorActive && isEnabled){
                g.setColor(CColor.BLACK);
                g.drawLine(horizontalPadding + font.getWidth(text.substring(charOffset, cursorPosition)), getHeight() / 6,
                        horizontalPadding + font.getWidth(text.substring(charOffset, cursorPosition)), getHeight() * 5/6);
            }
        }

        if(!isEnabled){
            g.setColor(DISABLED_BACKGROUND_OVERLAY);
            g.drawFilledRect(0,0, getWidth(), getHeight());
        }
    }



    @Override
    public void keyDown(int keycode, String key, KeyEvent e) {
        if(isEnabled){
            if(keycode == 22 && cursorPosition < text.length()){
                cursorPosition++;
            }else if(keycode == 21 && cursorPosition > 0){
                cursorPosition--;
            }
        }
    }

    @Override
    public void keyTyped(int keycode, String key, KeyEvent e) {

        if(isEnabled) {
            if(getText().length() < maxCharacters || keycode == 8){
                if(allowOnlyNumeric) {
                    if (keycode >= 48 && keycode <= 57 || keycode == 8)
                        keyTyped(keycode);
                }else
                    keyTyped(keycode);
            }

            if (font.getWidth(text) > getWidth() - horizontalPadding * 2) {
                for (int i = 0; i < text.length(); i++) {
                    if (font.getWidth(text.substring(i)) < getWidth() - horizontalPadding * 2) {
                        charOffset = i;
                        break;
                    }
                }
            } else {
                charOffset = 0;
            }
        }
    }

    @Override
    public void keyUp(int keycode, String key, KeyEvent e) { }

    @Override
    public void touchDown(int x, int y, ClickEvent e) {
        if(isEnabled) {
            for (int i = charOffset; i < text.length(); i++) {
                if (font.getWidth(text.substring(charOffset, i)) < x - horizontalPadding && font.getWidth(text.substring(charOffset, i + 1)) > x - horizontalPadding) {
                    if (font.getWidth(Character.toString(text.charAt(i))) / 2 + font.getWidth(text.substring(charOffset, i)) > x - horizontalPadding) {
                        cursorPosition = i;
                    } else {
                        cursorPosition = i + 1;
                    }
                }
            }
        }
    }

    public float getHorizontalTextPadding(){
        return horizontalPadding;
    }

    public void setHorizontalTextPadding(float padding){
        horizontalPadding = padding;
    }

    public void setMaxCharacters(int chars){
        this.maxCharacters = chars;
    }

    public void allowOnlyNumericInput(boolean allowOnlyNumeric){
        this.allowOnlyNumeric = allowOnlyNumeric;
    }
}
