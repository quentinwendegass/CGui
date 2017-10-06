package at.greywind.cgui.text;

import at.greywind.cgui.graphic.CColor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class Bitmap extends CFont {

    protected Bitmap(String path, Path p) {
        super(path, p);

        font = new BitmapFont(handle);
        setColor(CColor.BLACK);
    }
}
