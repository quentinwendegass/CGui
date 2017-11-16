package at.greywind.cgui.test.components;

import at.greywind.cgui.border.CBorderFactory;
import at.greywind.cgui.component.CPanel;
import at.greywind.cgui.event.ClickEvent;
import at.greywind.cgui.event.ClickListener;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;

public class LevelPanel extends CPanel {

    private int gridSize = 15;


    public LevelPanel(int gridSize) {
        this.gridSize = gridSize;
        setBorder(CBorderFactory.createDottedBorder());
        setSize(405, 720);
        setBackground(CColor.CLEAR);
    }

    @Override
    public void drawThisComponent(CGraphics g) {
        super.drawThisComponent(g);
        g.setComponent(getParentComponent());
        g.setColor(new CColor(0f, 0f, 0f, 0.3f));

        drawGrid(g);
    }

    private void drawGrid(CGraphics g){
        for(int i = getX(); i < getParentComponent().getWidth(); i+=gridSize)
            g.drawLine(i, 0, i, getParentComponent().getHeight());
        for(int i = getX(); i > 0; i-=gridSize)
            g.drawLine(i, 0, i, getParentComponent().getHeight());

        for(int i = getY(); i < getParentComponent().getHeight(); i+=gridSize)
            g.drawLine(0, i, getParentComponent().getWidth(), i);
        for(int i = getY(); i > 0; i-=gridSize)
            g.drawLine(0, i, getParentComponent().getWidth(), i);

    }
}
