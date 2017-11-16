package at.greywind.cgui.component;

import at.greywind.cgui.event.ComponentEvent;
import at.greywind.cgui.event.ComponentListener;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.util.Intersection;

public class CPanel extends CComponent implements ComponentListener, Enableable{

    private static final int DEFAULT_SIZE = 50;

    private boolean allowOverlapping = true;
    private boolean enabled = true;

    public CPanel(){
        this(0,0);
    }

    public CPanel(int x, int y){
        this(x,y, DEFAULT_SIZE,DEFAULT_SIZE);
    }

    public CPanel(int x, int y, int width, int height){
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void updateComponent(CGraphics g) {
        if(isVisible()){
            if(isEnabled()) processEvents();
            drawComponent(g);
        }
    }

    @Override
    public void add(CComponent cComponent) {
        super.add(cComponent);
        cComponent.addComponentListener(this);
    }

    @Override
    public void moved(int x, int y, ComponentEvent event) {
        if(!allowOverlapping) {
            CComponent movedComponent = event.getComponent();
            int movedComponentIndex = getChildComponents().indexOf(movedComponent);


            for (int i = 0; i < getChildComponents().size(); i++) {
                if (i != movedComponentIndex) {
                    CComponent c = getChildComponents().get(i);

                    if (Intersection.collideRectangles(c.getX(), c.getY(), c.getWidth(), c.getHeight(),
                            movedComponent.getX(), movedComponent.getY(), movedComponent.getWidth(), movedComponent.getHeight())) {

                        int intersectionX = 0;
                        int intersectionY = 0;

                        if (movedComponent.getX() >= c.getX())
                            intersectionX = c.getX() + c.getWidth() - movedComponent.getX();
                        else if (movedComponent.getX() < c.getX())
                            intersectionX = movedComponent.getX() + movedComponent.getWidth() - c.getX();

                        if (movedComponent.getY() >= c.getY())
                            intersectionY = c.getY() + c.getHeight() - movedComponent.getY();
                        else if (movedComponent.getY() < c.getY())
                            intersectionY = movedComponent.getY() + movedComponent.getHeight() - c.getY();


                        if (movedComponent.getX() >= c.getX() && intersectionX <= intersectionY)
                            movedComponent.setX(c.getX() + c.getWidth());
                        else if (movedComponent.getX() < c.getX() && intersectionX < intersectionY)
                            movedComponent.setX(c.getX() - movedComponent.getWidth());
                        else if (movedComponent.getY() >= c.getY() && intersectionY <= intersectionX)
                            movedComponent.setY(c.getY() + c.getHeight());
                        else if (movedComponent.getY() < c.getY() && intersectionY < intersectionX)
                            movedComponent.setY(c.getY() - movedComponent.getHeight());
                    }
                }
            }
        }
    }

    public boolean isOverlappingAllowed() {
        return allowOverlapping;
    }

    public void allowOverlapping(boolean allowOverlapping) {
        this.allowOverlapping = allowOverlapping;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
