package at.greywind.cgui.text;

import at.greywind.cgui.graphic.CColor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class TrueType extends CFont {

    protected TrueType(String path, Path p, int size, Color color, Color borderColor, int borderWidth, Color shadowColor, int shadowOffsetX, int shadowOffsetY) {
        super(path, p);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(handle);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        parameter.borderColor = borderColor;
        parameter.borderWidth = borderWidth;
        parameter.shadowColor = shadowColor;
        parameter.shadowOffsetX = shadowOffsetX;
        parameter.shadowOffsetY = shadowOffsetY;

        font = generator.generateFont(parameter);
        generator.dispose();
    }
}
