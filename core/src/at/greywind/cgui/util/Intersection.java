package at.greywind.cgui.util;

public class Intersection {

    public static boolean isPointInRect(int rX, int rY, int rWidth, int rHeight, int pX, int pY){
        if(pX > rX && pX < rX + rWidth && pY > rY && pY < rY + rHeight)
            return true;
        else return false;
    }

    public static boolean collideRectangles(int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2){
        if(x1 + width1 > x2 && x2 + width2 > x1 && y1 + height1 > y2 && y2 + height2 > y1)
            return true;
        else return false;
    }
}
