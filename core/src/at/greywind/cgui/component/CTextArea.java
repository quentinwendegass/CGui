package at.greywind.cgui.component;

import at.greywind.cgui.event.KeyListener;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.text.CFont;

import java.util.ArrayList;

public class CTextArea extends TextComponent implements KeyListener, Padable{

    private static final float STANDART_PADDING = 5;

    private ArrayList<String> lines = new ArrayList<>();

    private int lineHeight = 20;

    private float verticalPadding = STANDART_PADDING;
    private float horizontalPadding = STANDART_PADDING;

    public CTextArea(CFont font){
        this(font, 0, 0);
    }

    public CTextArea(CFont font, int x, int y){
        this(font, x, y, 0,0);
    }

    public CTextArea(CFont font, int x, int y, int width, int height) {
        super(font, x, y, width, height);
        lines.add("");

        addKeyListener(this);
    }

    public void appendNewLine(String text){
        this.text.append((char)3);
        append(text);
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);

        if(lines != null)
            checkLines();
    }

    @Override
    public void drawComponent(CGraphics g) {
        super.drawComponent(g);
        g.setFont(font);

        int y = getHeight() - (int) verticalPadding;
        int renderedChars = 0;
        boolean cursorDrawed = false;

        for(String line : lines){
            if(y >= lineHeight) {
                g.drawText(line, horizontalPadding, y - font.getHeight(line) / 2);
                renderedChars += line.length();

                if (drawCursor && cursorActive && isEnabled) {
                    if (cursorPosition <= renderedChars && !cursorDrawed) {
                        g.setColor(CColor.BLACK);

                        g.drawLine(font.getWidth(line.substring(0, cursorPosition - (renderedChars - line.length()))) + horizontalPadding, y,
                                font.getWidth(line.substring(0, cursorPosition - (renderedChars - line.length()))) + horizontalPadding, y - lineHeight);
                        cursorDrawed = true;
                    }
                }
                y -= lineHeight;
            }
        }

        if(!isEnabled){
            g.setColor(DISABLED_BACKGROUND_OVERLAY);
            g.drawFilledRect(0,0, getWidth(), getHeight());
        }
    }

    @Override
    public void keyDown(int keycode, String key) {

    }

    @Override
    public void keyUp(int keycode, String key) {

    }

    @Override
    public void keyTyped(int keycode, String key) {
        if(isEnabled) {
           keyTyped(keycode);

           if(keycode == 10){
               cursorPosition++;
               text.insert(cursorPosition - 1, (char) 3);
           }

           checkLines();
        }
    }

    private void checkLines(){
        lines.clear();

        lines.add("");

        for(int i = 0; i < text.length(); i++){
            if((int)text.charAt(i) == 3){
                lines.add("");
            }
            lines.set(lines.size() - 1, lines.get(lines.size() - 1) + text.charAt(i));
            if(font.getWidth(lines.get(lines.size() - 1)) > getWidth() - 2*horizontalPadding){
                String oldLine = lines.get(lines.size() - 1);

                try{
                    String newLine = oldLine.substring(oldLine.lastIndexOf(' '), oldLine.length());
                    oldLine = oldLine.substring(0, oldLine.lastIndexOf(' '));

                    lines.add("");

                    lines.set(lines.size() - 2, oldLine);
                    lines.set(lines.size() - 1, newLine);
                }catch (IndexOutOfBoundsException e){
                    lines.add("");
                    lines.set(lines.size() - 1, lines.get(lines.size() - 2).substring(lines.get(lines.size() - 2).length() - 1));
                    lines.set(lines.size() - 2, lines.get(lines.size() - 2).substring(0, lines.get(lines.size() - 2).length() - 1));
                }
            }
        }
    }

    @Override
    public void touchDown(int x, int y) {
        if(isEnabled) {
            int topY = getHeight() - y - (int) verticalPadding;

            int row = (topY / lineHeight);

            if (lines.size() > row) {
                for (int i = 0; i < lines.get(row).length(); i++) {
                    if (font.getWidth(lines.get(row).substring(0, i)) < x - horizontalPadding && font.getWidth(lines.get(row).substring(0, i + 1)) > x - horizontalPadding) {
                        if (font.getWidth(Character.toString(lines.get(row).charAt(i))) / 2 + font.getWidth(lines.get(row).substring(0, i)) > x - horizontalPadding) {
                            int charCount = 0;
                            for (int j = 0; j < row; j++) {
                                charCount += lines.get(j).length();
                            }
                            cursorPosition = charCount + i;
                        } else {
                            int charCount = 0;
                            for (int j = 0; j < row; j++) {
                                charCount += lines.get(j).length();
                            }
                            cursorPosition = charCount + i + 1;
                        }
                    }
                }
            }
        }



    }

    @Override
    public void setVerticalPadding(float padding) {
        verticalPadding = padding;
    }

    @Override
    public void setHorizontalPadding(float padding) {
        horizontalPadding = padding;
    }

    @Override
    public float getVerticalPadding() {
        return verticalPadding;
    }

    @Override
    public float getHorizontalPadding() {
        return horizontalPadding;
    }
}
