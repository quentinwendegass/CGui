package at.greywind.cgui.border;

import at.greywind.cgui.graphic.CColor;

public abstract class CBorderFactory {

    public static CBorder createLineBorder(){
        return createLineBorder(CColor.BLACK);
    }

    public static CBorder createLineBorder(CColor color){
        return createLineBorder(2, color);
    }

    public static CBorder createLineBorder(float width){
        return createLineBorder(width, CColor.BLACK);
    }

    public static CBorder createLineBorder(float width, CColor color){
        return createLineBorder(width, color, color);
    }

    public static CBorder createLineBorder(float width, CColor color1, CColor color2){
        return new LineBorder(width, color1, color2);
    }

    public static CBorder createDottedBorder(){
        return createDottedBorder(CColor.BLACK);
    }

    public static CBorder createDottedBorder(CColor color){
        return createDottedBorder(5,5, color);
    }

    public static CBorder createDottedBorder(float dotSize, float spacing){
        return createDottedBorder(dotSize, spacing, CColor.BLACK);
    }

    public static CBorder createDottedBorder(float dotSize, float spacing, CColor color){
        return createDottedBorder(dotSize, spacing, color, color);
    }

    public static CBorder createDottedBorder(float dotSize, float spacing, CColor color1, CColor color2){
        return new DottedBorder(dotSize, spacing, color1, color2);
    }

    public static CBorder createDashedBorder(){
        return createDashedBorder(CColor.BLACK);
    }

    public static CBorder createDashedBorder(CColor color){
        return createDashedBorder(15,15, 2, color, color);
    }

    public static CBorder createDashedBorder(float dashLength, float space, float lineWidth){
        return createDashedBorder(lineWidth,dashLength, space, CColor.BLACK);
    }

    public static CBorder createDashedBorder(float dashLength, float space, float lineWidth, CColor color){
        return createDashedBorder(lineWidth,dashLength, space, color, color);
    }

    public static CBorder createDashedBorder(float dashLength, float space, float lineWidth, CColor color1, CColor color2){
        return new DashedBorder(lineWidth,dashLength, space, color1, color2);
    }

}
