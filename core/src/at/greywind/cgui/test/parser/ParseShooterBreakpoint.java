package at.greywind.cgui.test.parser;

public class ParseShooterBreakpoint {

    private int x;
    private int y;
    private int angle;
    private int duration;

    public ParseShooterBreakpoint(int x, int y, int angle, int duration) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.duration = duration;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
