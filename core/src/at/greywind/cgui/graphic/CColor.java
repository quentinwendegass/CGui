package at.greywind.cgui.graphic;

public final class CColor {

    public static final CColor WHITE = new CColor(1f, 1f, 1f);
    public static final CColor GREY = new CColor(115, 115, 115, 255);
    public static final CColor CLEAR = new CColor(0f, 0f, 0f, 0f);
    public static final CColor BLACK = new CColor(0f, 0f, 0f);
    public static final CColor RED = new CColor(1f, 0f, 0f);
    public static final CColor GREEN = new CColor(0, 128, 0);
    public static final CColor BLUE = new CColor(0f, 0f, 1f);
    public static final CColor LIGHT_BLUE = new CColor(100, 200, 255);
    public static final CColor BLUE_VIOLET = new CColor(138, 43, 226);
    public static final CColor BEIGE = new CColor(245, 245, 220);
    public static final CColor CORAL = new CColor(255, 127, 80);
    public static final CColor CYAN = new CColor(0, 255, 255);
    public static final CColor DARK_BLUE = new CColor(0, 0, 139);
    public static final CColor GOLD = new CColor(184, 134, 11);
    public static final CColor DARK_MAGENTA = new CColor(139, 0, 139);
    public static final CColor ORANGE = new CColor(255, 140, 0);
    public static final CColor TURQUOISE = new CColor(0, 206, 209);
    public static final CColor PINK = new CColor(255, 20, 147);
    public static final CColor INDIGO = new CColor(75, 0, 130);
    public static final CColor LIGHT_GREEN = new CColor(144, 238, 144);
    public static final CColor LIGHT_PINK = new CColor(255, 182, 193);
    public static final CColor NAVY = new CColor(0, 0, 128);
    public static final CColor OLIVE = new CColor(128, 128, 0);
    public static final CColor BROWN = new CColor(165, 42, 42);
    public static final CColor YELLOW= new CColor(255, 255, 0);
    public static final CColor AQUA = new CColor(0, 255, 255);


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
