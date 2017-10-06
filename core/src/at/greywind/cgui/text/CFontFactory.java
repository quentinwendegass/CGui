package at.greywind.cgui.text;

import at.greywind.cgui.graphic.CColor;
import com.badlogic.gdx.graphics.Color;

public class CFontFactory {

    public static CFont createBitmapFont(String path){
       return createBitmapFont(path, CFont.Path.INTERNAL);
    }

    public static CFont createBitmapFont(String path, CFont.Path location){
        return new Bitmap(path, location);
    }

    public static CFont createTrueTypeFont(String path, int size){
        return createTrueTypeFont(path, CFont.Path.INTERNAL, size, CColor.BLACK, CColor.BLACK, 0, CColor.BLACK, 0,0);
    }

    public static CFont createTrueTypeFont(String path, int size, CColor color, CColor borderColor, int borderWidth){
        return createTrueTypeFont(path, CFont.Path.INTERNAL, size, color, borderColor, borderWidth, CColor.BLACK, 0,0);
    }

    public static CFont createTrueTypeFont(String path, int size, CColor color, CColor shadowColor, int shadowOffsetX, int shadowOffsetY){
        return createTrueTypeFont(path, CFont.Path.INTERNAL, size, color, CColor.BLACK, 0, shadowColor, shadowOffsetX,shadowOffsetY);
    }

    public static CFont createTrueTypeFont(String path, CFont.Path location, int size, CColor color, CColor borderColor, int borderWidth, CColor shadowColor, int shadowOffsetX, int shadowOffsetY){
        return new TrueType(path, location, size, getColor(color), getColor(borderColor), borderWidth, getColor(shadowColor), shadowOffsetX,shadowOffsetY);
    }

    private static Color getColor(CColor c){
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
    }
}
