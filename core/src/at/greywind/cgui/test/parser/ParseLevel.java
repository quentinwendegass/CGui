package at.greywind.cgui.test.parser;

import java.util.ArrayList;

public class ParseLevel {

    private ArrayList<ParseBall> balls;
    private ArrayList<ParseBrick> bricks;
    private ParseShooter shooter;

    public ParseLevel(ArrayList<ParseBall> balls, ArrayList<ParseBrick> bricks, ParseShooter shooter) {
        this.balls = balls;
        this.bricks = bricks;
        this.shooter = shooter;
    }

    public ArrayList<ParseBall> getBalls() {
        return balls;
    }

    public void setBalls(ArrayList<ParseBall> balls) {
        this.balls = balls;
    }

    public ArrayList<ParseBrick> getBricks() {
        return bricks;
    }

    public void setBricks(ArrayList<ParseBrick> bricks) {
        this.bricks = bricks;
    }

    public ParseShooter getShooter() {
        return shooter;
    }

    public void setShooter(ParseShooter shooter) {
        this.shooter = shooter;
    }
}
