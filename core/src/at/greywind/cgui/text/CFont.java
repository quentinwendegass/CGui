package at.greywind.cgui.text;

import at.greywind.cgui.graphic.CColor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public abstract class CFont {


    protected FileHandle handle;
    protected BitmapFont font;
    protected GlyphLayout glyphLayout;

    public enum Path{
        INTERNAL, EXTERNAL, ABSOLUT
    }

    protected CFont(String path, Path p){
        glyphLayout = new GlyphLayout();

        switch (p){
            case ABSOLUT:
                handle = Gdx.files.absolute(path);
                break;
            case EXTERNAL:
                handle = Gdx.files.external(path);
                break;
            case INTERNAL:
                handle = Gdx.files.internal(path);
                break;
            default:
                throw new NullPointerException();
        }
    }

    public void draw(Batch batch, float x, float y, CharSequence text){
        font.draw(batch, text, x, y);
    }

    public float getWidth(CharSequence text){
        glyphLayout.setText(font, text);
        return glyphLayout.width;
    }

    public float getHeight(CharSequence text){
        glyphLayout.setText(font, text);
        return glyphLayout.height;
    }

    public float getHeight(){
        glyphLayout.setText(font, "Test");
        return glyphLayout.height;
    }

    public CColor getColor(){
        Color c = font.getColor();
        return new CColor(c.r, c.g, c.b, c.a);
    }

    public void setColor(CColor c){
        font.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()));
    }
}
