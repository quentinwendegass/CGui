package at.greywind.cgui.component;

import at.greywind.cgui.border.CBorder;
import at.greywind.cgui.event.*;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.util.Intersection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class CList extends CComponent implements ScrollListener{

    private int scrollY;

    private int scrollVelocity = 5;

    public CList(int x, int y, int width, int height){
        super();
        setPosition(x, y);
        setSize(width, height);
        setBackground(CColor.GREY);
        useEventIndex(true);
        addScrollListener(this);
    }

    public void addEventToQueue(CEvent event){
        if(event instanceof ClickEvent) {
            if (Intersection.isPointInRect(getWindowX(), getWindowY(), getWidth(), getHeight(), Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()))
                super.addEventToQueue(event);
        }else{
            super.addEventToQueue(event);
        }
    }

    @Override
    public void add(CComponent cComponent) {
        if(window!=null) cComponent.registerToWindow(window);
        cComponent.parentComponent = this;
        cComponent.setEventIndex(getEventIndex());
        cComponent.addEventToQueue(new ComponentEvent(this, ComponentEvent.Type.ADDED));
        childComponents.add(0, cComponent);

        scrollToTop();
    }


    @Override
    public void drawChildComponents(CGraphics g) {
        updatePosition();

        g.end();

        Gdx.gl.glClearDepthf(1f);
        Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glDepthFunc(GL20.GL_LESS);
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);

        Gdx.gl.glDepthMask(true);
        Gdx.gl.glColorMask(false, false, false, false);

        g.begin();
        g.setColor(new CColor(1f, 0f, 0f, 0.5f));
        g.drawFilledRect(0 + getBorder().getWidth() / 2,0 + getBorder().getWidth() / 2, getWidth() - getBorder().getWidth(), getHeight() - getBorder().getWidth());
        g.end();

        g.begin();
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glDepthFunc(GL20.GL_EQUAL);

        g.setColor(new CColor(0f, 1f, 1f, 1f));
        for(CComponent component : getChildComponents()){
            component.updateComponent(g);
        }
        g.end();

        Gdx.gl.glDepthFunc(GL20.GL_ALWAYS);
        g.begin();
    }

    private void updatePosition(){
        int y = scrollY;
        for(CComponent c : getChildComponents()){
            c.setPosition(getWidth() / 2 - c.getWidth() / 2, y);
            y+=c.getHeight();
        }
    }

    public void scrollToTop(){
        int compHeight = 0;
        for(CComponent c : getChildComponents()) compHeight += c.getHeight();

        scrollY = 0 - (compHeight - getHeight());
    }


    @Override
    public void scrolled(int amount) {
        if(amount > 0)
            scrollY += scrollVelocity;
        else if(amount < 0)
            scrollY -= scrollVelocity;

        if(scrollY > 0) scrollY = 0;

        int compHeight = 0;
        for(CComponent c : getChildComponents()) compHeight += c.getHeight();

        if(0 - (compHeight - getHeight()) > scrollY) scrollY = 0 - (compHeight - getHeight());
    }
}
