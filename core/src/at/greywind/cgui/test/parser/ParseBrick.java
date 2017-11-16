package at.greywind.cgui.test.parser;

import java.util.ArrayList;

public class ParseBrick {

    private int colorRed;
    private int colorGreen;
    private int colorBlue;
    private int shapeType;
    private ArrayList<ParseBrickBreakpoint> breakpoints;

    public ParseBrick(int colorRed, int colorGreen, int colorBlue, int shapeType, ArrayList<ParseBrickBreakpoint> breakpoints) {
        this.colorRed = colorRed;
        this.colorGreen = colorGreen;
        this.colorBlue = colorBlue;
        this.breakpoints = breakpoints;
        this.shapeType = shapeType;
    }

    public int getColorRed() {
        return colorRed;
    }

    public void setColorRed(int colorRed) {
        this.colorRed = colorRed;
    }

    public int getColorGreen() {
        return colorGreen;
    }

    public void setColorGreen(int colorGreen) {
        this.colorGreen = colorGreen;
    }

    public int getColorBlue() {
        return colorBlue;
    }

    public void setColorBlue(int colorBlue) {
        this.colorBlue = colorBlue;
    }

    public ArrayList<ParseBrickBreakpoint> getBreakpoints() {
        return breakpoints;
    }

    public void setBreakpoints(ArrayList<ParseBrickBreakpoint> breakpoints) {
        this.breakpoints = breakpoints;
    }

    public int getShapeType() {
        return shapeType;
    }

    public void setShapeType(int shapeType) {
        this.shapeType = shapeType;
    }
}
