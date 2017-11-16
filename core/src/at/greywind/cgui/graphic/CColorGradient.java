package at.greywind.cgui.graphic;

public final class CColorGradient {

    public enum GradientDirection {
        VERTICAL, HORIZONTAL
    }

    private CColor topLeft;
    private CColor topRight;
    private CColor bottomLeft;
    private CColor bottomRight;

    public CColorGradient(CColor color){
        init(color, color, color, color);
    }

    public CColorGradient(CColor first, CColor second){
        this(first, second, GradientDirection.HORIZONTAL);
    }

    public CColorGradient(CColor first, CColor second, GradientDirection direction){
        switch (direction){
            case VERTICAL:
                init(first, first, second, second);
                break;
            case HORIZONTAL:
                init(first, second, first, second);
        }
    }

    public CColorGradient(CColor topLeft, CColor topRight, CColor bottomLeft, CColor bottomRight) {
        init(topLeft, topRight, bottomLeft, bottomRight);
    }

    private void init(CColor topLeft, CColor topRight, CColor bottomLeft, CColor bottomRight){
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }

    public void setTopLeft(CColor topLeft) {
        this.topLeft = topLeft;
    }

    public void setTopRight(CColor topRight) {
        this.topRight = topRight;
    }

    public void setBottomLeft(CColor bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public void setBottomRight(CColor bottomRight) {
        this.bottomRight = bottomRight;
    }

    public CColor getTopLeft() {
        return topLeft;
    }

    public CColor getTopRight() {
        return topRight;
    }

    public CColor getBottomLeft() {
        return bottomLeft;
    }

    public CColor getBottomRight() {
        return bottomRight;
    }
}
