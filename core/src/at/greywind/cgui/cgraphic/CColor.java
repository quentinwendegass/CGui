package at.greywind.cgui.cgraphic;

public final class CColor {

    public static final CColor WHITE = new CColor(1f, 1f, 1f);
    public static final CColor BLACK = new CColor(0f, 0f, 0f);
    public static final CColor RED = new CColor(1f, 0f, 0f);
    public static final CColor GREEN = new CColor(0f, 1f, 0f);
    public static final CColor BLUE = new CColor(0f, 0f, 1f);
    public static final CColor LIGHT_BLUE = new CColor(100, 200, 255);

    private float red;
    private float green;
    private float blue;
    private float alpha;

    public CColor() {
        this(0f, 0f, 0f);
    }

    public CColor(float red, float green, float blue) {
        this(red, green, blue, 1f);
    }

    public CColor(float red, float green, float blue, float alpha) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
        setAlpha(alpha);
    }

    public CColor(int red, int green, int blue) {
        this(red, green, blue, 255);
    }

    public CColor(int red, int green, int blue, int alpha) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
        setAlpha(alpha);
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        if (red < 0f) {
            red = 0f;
        }
        if (red > 1f) {
            red = 1f;
        }
        this.red = red;
    }

    public void setRed(int red) {
        setRed(red / 255f);
    }

    public float getGreen() {
        return green;
    }

    public void setGreen(float green) {
        if (green < 0f) {
            green = 0f;
        }
        if (green > 1f) {
            green = 1f;
        }
        this.green = green;
    }

    public void setGreen(int green) {
        setGreen(green / 255f);
    }

    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        if (blue < 0f) {
            blue = 0f;
        }
        if (blue > 1f) {
            blue = 1f;
        }
        this.blue = blue;
    }

    public void setBlue(int blue) {
        setBlue(blue / 255f);
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        if (alpha < 0f) {
            alpha = 0f;
        }
        if (alpha > 1f) {
            alpha = 1f;
        }
        this.alpha = alpha;
    }

    public void setAlpha(int alpha) {
        setAlpha(alpha / 255f);
    }
}
