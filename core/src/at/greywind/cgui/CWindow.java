package at.greywind.cgui;

import at.greywind.cgui.component.*;
import at.greywind.cgui.event.ClickEvent;
import at.greywind.cgui.event.MouseEvent;
import at.greywind.cgui.graphic.CGraphics;
import com.badlogic.gdx.Gdx;

import static com.badlogic.gdx.graphics.GL20.*;

public abstract class CWindow {

    protected WindowLauncher launcher;
    protected CPanel contentPanel;

    protected CGraphics graphics;

    private boolean initialized = false;


    public CWindow(WindowLauncher launcher) {
        this.launcher = launcher;
    }

    protected void init(){
        if(!initialized){
            graphics = new CGraphics();
            contentPanel = new CPanel(0,0, launcher.getWidth(), launcher.getHeight());
            initialized = true;
        }
    }

    public abstract void initComponents();


    boolean touchedLF = false;
    int xLF;
    int yLF;

    public void fireEvents() {
        //Click
        if (Gdx.input.justTouched()) {
            ClickEvent event = new ClickEvent(Gdx.input.getX(),Gdx.graphics.getHeight() - Gdx.input.getY(), ClickEvent.ClickType.CLICKED);
            event.setComponentPosition(contentPanel.getX(), contentPanel.getY());
            contentPanel.addEventToQueue(event);
        }

        ClickEvent event = null;
        if (!Gdx.input.isTouched() && touchedLF) {
            event = new ClickEvent(ClickEvent.ClickType.TOUCH_UP);
        } else if (Gdx.input.isTouched() && !touchedLF) {
            event = new ClickEvent(ClickEvent.ClickType.TOUCH_DOWN);
        } else if (Gdx.input.isTouched() && touchedLF) {
            event = new ClickEvent(ClickEvent.ClickType.PRESS);
        }

        if (event != null) {
            event.setWindowPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            event.setComponentPosition(contentPanel.getX(), contentPanel.getY());
            contentPanel.addEventToQueue(event);
        }

        //Mouse
        if((xLF != Gdx.input.getX() || yLF != Gdx.graphics.getHeight() - Gdx.input.getY()) && contentPanel.containsPoint(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())){
            MouseEvent e = new MouseEvent(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), xLF, yLF, MouseEvent.MouseType.MOVE);
            e.setComponentPosition(contentPanel.getWindowX(), contentPanel.getWindowY());
            contentPanel.addEventToQueue(e);
        }

        touchedLF = Gdx.input.isTouched();
        xLF = Gdx.input.getX();
        yLF = Gdx.graphics.getHeight() - Gdx.input.getY();
    }




    public void updateComponent(){
        fireEvents();
    }

    public void drawComponent(){
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        graphics.begin();

        contentPanel.updateComponent(graphics);

        graphics.end();
    }
}
