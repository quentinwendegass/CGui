package at.greywind.cgui;

import at.greywind.cgui.cevent.ClickEvent;
import at.greywind.cgui.cevent.ClickListener;
import at.greywind.cgui.cgraphic.CColor;
import at.greywind.cgui.cgraphic.CColorGradient;
import at.greywind.cgui.cgraphic.CGraphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class CWindow {

    WindowLauncher launcher;
    CPanel contentPanel;


    CGraphics graphics;
    Texture texture;


    public CWindow(WindowLauncher launcher) {
        this.launcher = launcher;
    }

    public void init(){
        contentPanel = new CPanel(0,0, launcher.getWidth(), launcher.getHeight());
        contentPanel.setEventIndex(0);

        CPanel panel = new CPanel(100, 100, 300, 300);
        panel.setBackground(CColorGradient.SKY);

        CPanel panel2 = new CPanel(100, 100, 100, 100);
        panel2.setBackground(new CColorGradient(CColor.GREEN));

        contentPanel.add(panel);

        panel.add(panel2);


        contentPanel.addClickListener(new ClickListener() {
            @Override
            public void clicked(int x, int y) {
                System.out.println("Clicked " + x + " " + y);
            }

            @Override
            public void pressed(int x, int y) {
                System.out.println("Pressed " + x + " " + y);

            }

            @Override
            public void touchUp(int x, int y) {
                System.out.println("Up " + x + " " + y);

            }

            @Override
            public void touchDown(int x, int y) {
                System.out.println("Down " + x + " " + y);

            }
        });

        graphics = new CGraphics();
        texture = new Texture("badlogic.jpg");
    }

    boolean touchedLF = false;
    public void fireEvents() {
        if (Gdx.input.justTouched()) {
            ClickEvent event = new ClickEvent(Gdx.input.getX(),Gdx.graphics.getHeight() - Gdx.input.getY(), ClickEvent.ClickType.CLICKED);
            event.setComponentPosition(contentPanel.getX(), contentPanel.getY());
            contentPanel.addClickEventToQueue(event);
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
            contentPanel.addClickEventToQueue(event);
        }

        touchedLF = Gdx.input.isTouched();
    }






/**   Rectangle window = new Rectangle(0,0, launcher.getWidth(), launcher.getHeight());

    boolean containsMouseLF = window.contains(Gdx.input.getX(), Gdx.input.getY());
    int xLF = Gdx.input.getX();
    int yLF = Gdx.input.getY();
    public void handleMoveEvents(){
        if(window.contains(Gdx.input.getX(), Gdx.input.getY()) && !containsMouseLF){
            //Fire Mouse Enter Event
        }else if(!window.contains(Gdx.input.getX(), Gdx.input.getY()) && containsMouseLF){
            //Fire Mouse Exit Event
        }

        if(xLF != Gdx.input.getX() || yLF != Gdx.input.getY()){
            //Fire move Event
        }

        xLF = Gdx.input.getX();
        yLF = Gdx.input.getY();
        containsMouseLF = window.contains(Gdx.input.getX(), Gdx.input.getY());
    }
*/

    public void drawComponent(){
        fireEvents();

        graphics.begin();

        contentPanel.drawComponent(graphics);

        graphics.end();
    }
}
