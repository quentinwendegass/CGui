package at.greywind.cgui.component;

import at.greywind.cgui.border.CBorderFactory;
import at.greywind.cgui.event.FocusListener;
import at.greywind.cgui.event.MouseListener;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.text.CFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;

import java.awt.*;

public abstract class TextComponent extends CComponent implements MouseListener, FocusListener, Enableable{

    protected float timer = 0;
    protected boolean cursorActive = true;
    protected boolean drawCursor = false;
    protected int cursorPosition = 0;

    protected boolean isEnabled = true;

    protected StringBuilder text = new StringBuilder();
    protected CFont font;

    protected final CColor DISABLED_BACKGROUND_OVERLAY = new CColor(115,115,115, 155);
    private final static float BLINK_TIME = 0.5f;


    public TextComponent(CFont font, int x, int y, int width, int height){
        this.font = font;
        setBackground(CColor.WHITE);
        setBorder(CBorderFactory.createLineBorder(1));
        addFocusListener(this);
        addMouseListener(this);
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    public void keyTyped(int keycode){
        if (keycode == 8) {
            if (text.length() > 0 && cursorPosition > 0) {
                cursorPosition--;
                text.deleteCharAt(cursorPosition);
            }
        } else if (keycode >= 32 && keycode <= 126) {
            cursorPosition++;
            text.insert(cursorPosition - 1, (char) keycode);
        }
    }

    @Override
    public void updateComponent(CGraphics g) {
        super.updateComponent(g);

        if(isEnabled && isVisible()) {
            timer += Gdx.graphics.getDeltaTime();

            if (timer > BLINK_TIME) {
                cursorActive = !cursorActive;
                timer = 0;
            }
        }
    }

    @Override
    public void enter() {
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Ibeam);
    }

    @Override
    public void exit() {
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
    }

    @Override
    public void move(int x, int y) {

    }

    @Override
    public void lostFocus(CComponent component) {
        drawCursor = false;
    }

    @Override
    public void gainFocus(CComponent component) {
        drawCursor = true;
    }

    @Override
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setText(String text){
        this.text = new StringBuilder(text);
        cursorPosition = this.text.length();
    }

    public String getText(){
        return text.toString();
    }

    public void append(String text){
        this.text.append(text);
        cursorPosition = this.text.length();
    }

    public void setFont(CFont font){
        this.font = font;
    }

    public CFont getFont(){
        return font;
    }



}
