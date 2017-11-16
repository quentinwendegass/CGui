package at.greywind.cgui.test.parser;

import at.greywind.cgui.test.components.BallComponent;
import at.greywind.cgui.test.Breakpoint;
import at.greywind.cgui.test.components.ShapeComponent;
import at.greywind.cgui.test.components.ShootComponent;
import com.badlogic.gdx.utils.Json;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LevelParser {

    private static String path = "test.lvl";

    public static boolean writeToJson(ArrayList<ShapeComponent> bricks, ArrayList<BallComponent> balls, ShootComponent shooter){

        if(path.equals("")) return false;


        ArrayList<ParseBrick> parseBricks = new ArrayList<>();

        for(ShapeComponent brick : bricks){

            ArrayList<ParseBrickBreakpoint> parseBrickBreakpoints = new ArrayList<>();

            for(Breakpoint breakpoint : brick.getBreakpoints()){
                parseBrickBreakpoints.add(new ParseBrickBreakpoint(breakpoint.getX(), breakpoint.getY(), breakpoint.getWidth(), breakpoint.getHeight(), breakpoint.getAngle(), breakpoint.getAlpha(), breakpoint.getDuration()));
            }
            parseBricks.add(new ParseBrick((int)(brick.getShapeColor().getRed() * 256), (int)(brick.getShapeColor().getGreen() * 256), (int)(brick.getShapeColor().getBlue() * 256), brick.getShapeType(), parseBrickBreakpoints));
        }


        ArrayList<ParseBall> parseBalls = new ArrayList<>();

        for(BallComponent ball : balls){
            parseBalls.add(new ParseBall((int)(ball.getShapeColor().getRed() * 256), (int)(ball.getShapeColor().getGreen() * 256), (int)(ball.getShapeColor().getBlue() * 256)));
        }


        ArrayList<ParseShooterBreakpoint> parseShooterBreakpoints = new ArrayList<>();

        for(Breakpoint breakpoint : shooter.getBreakpoints()){
            parseShooterBreakpoints.add(new ParseShooterBreakpoint(breakpoint.getX(), breakpoint.getY(), breakpoint.getAngle(), breakpoint.getDuration()));
        }

        ParseShooter parseShooter = new ParseShooter(parseShooterBreakpoints);



        ParseLevel parseLevel = new ParseLevel(parseBalls, parseBricks, parseShooter);


        File file = new File(path);
        if(!file.exists()) try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!file.canWrite()) return false;

        Json json = new Json();
        String output = json.prettyPrint(parseLevel);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            writer.write(output);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }

    public static void setPath(String _path){
        path = _path;
    }
}
