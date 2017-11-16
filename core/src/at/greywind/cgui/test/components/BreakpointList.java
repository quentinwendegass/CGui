package at.greywind.cgui.test.components;

import at.greywind.cgui.component.CList;
import at.greywind.cgui.component.button.CTextButton;
import at.greywind.cgui.event.ClickListener;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CColorGradient;
import at.greywind.cgui.layout.Alignment;
import at.greywind.cgui.test.Breakpoint;
import at.greywind.cgui.text.CFont;
import at.greywind.cgui.text.CFontFactory;


import java.util.ArrayList;

public class BreakpointList extends CList{

    private final static CColorGradient SECONDARY_COLOR = new CColorGradient(new CColor(70,70,70));
    private static final CColorGradient BACKGROUND_COLOR = new CColorGradient(new CColor(50,50,50));
    private static final CColorGradient PRESSED_COLOR = new CColorGradient(new CColor(50,0,0));

    private static ArrayList<Breakpoint> breakpoints;

    private static ClickListener breakpointListener;

    private static CFont font;

    private static BreakpointList instance;


    private BreakpointList(int x, int y, int width, int height) {
        super(x, y, width, height);
        setBackground(BACKGROUND_COLOR);
        resizeTo(Alignment.TOP);
        font = CFontFactory.createTrueTypeFont("arial.ttf", 15, new CColor(150, 0,0), CColor.GREY, 1);

        breakpointListener = ((x1, y1, e) -> {
            ConfigPanel.setBreakpointConfig(breakpoints.get(e.getComponent().getId()));
        });
    }

    public static void setBreakpoints(ArrayList<Breakpoint> _breakpoints){
        breakpoints = _breakpoints;

        getInstance().childComponents.clear();

        if(breakpoints != null) {
            for (int i = 0; i < breakpoints.size(); i++) {
                CTextButton tmp = new CTextButton("Breakpoint " + i, font, 0, 0, getInstance().getWidth(), 30);
                if (i % 2 == 0) {
                    tmp.setUpBackgroundColor(BACKGROUND_COLOR);
                    tmp.setMouseOverBackground(BACKGROUND_COLOR);
                } else {
                    tmp.setUpBackgroundColor(SECONDARY_COLOR);
                    tmp.setMouseOverBackground(SECONDARY_COLOR);
                }
                tmp.setId(i);
                tmp.addClickListener(breakpointListener);
                tmp.setDownBackgroundColor(PRESSED_COLOR);

                getInstance().add(tmp);
            }
        }
    }

    public static BreakpointList createInstance(int x, int y, int width, int height){
        if(instance == null) instance = new BreakpointList(x, y, width, height);
        return instance;
    }

    public static BreakpointList getInstance() {
        return instance;
    }
}
