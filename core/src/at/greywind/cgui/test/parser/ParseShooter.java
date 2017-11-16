package at.greywind.cgui.test.parser;

import java.util.ArrayList;

public class ParseShooter {

    private ArrayList<ParseShooterBreakpoint> breakpoints;

    public ParseShooter(ArrayList<ParseShooterBreakpoint> breakpoints) {
        this.breakpoints = breakpoints;
    }

    public ArrayList<ParseShooterBreakpoint> getBreakpoints() {
        return breakpoints;
    }

    public void setBreakpoints(ArrayList<ParseShooterBreakpoint> breakpoints) {
        this.breakpoints = breakpoints;
    }
}
