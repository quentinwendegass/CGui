package at.greywind.cgui;

import at.greywind.cgui.component.*;
import at.greywind.cgui.event.ClickEvent;
import at.greywind.cgui.event.FocusListener;
import at.greywind.cgui.event.KeyEvent;
import at.greywind.cgui.event.MouseEvent;
import at.greywind.cgui.graphic.CGraphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import javax.swing.*;
import java.util.ArrayList;

import static com.badlogic.gdx.graphics.GL20.*;

public abstract class CWindow implements FocusListener {

    protected WindowLauncher launcher;
    protected CPanel contentPanel;

    private Camera camera;

    private CGraphics graphics;

    private boolean initialized = false;

    private ArrayList<Focusable> focusables = new ArrayList<>();
    private CComponent focusedComponent = null;


    public CWindow(WindowLauncher launcher) {
        this.launcher = launcher;
    }

    protected void init(){
        if(!initialized){
            graphics = new CGraphics();
            contentPanel = new CPanel(0,0, launcher.getWidth(), launcher.getHeight());
            contentPanel.registerToWindow(this);
            camera = new OrthographicCamera(launcher.getWidth(), launcher.getHeight());
            camera.translate(launcher.getWidth() / 2, launcher.getHeight() / 2, 0);
            camera.update();
            initialized = true;

            Gdx.input.setInputProcessor(new InputAdapter(){
                @Override
                public boolean keyDown(int keycode) {
                    if(focusedComponent != null){
                        focusedComponent.addEventToQueue(new KeyEvent(focusedComponent, keycode, Input.Keys.toString(keycode), KeyEvent.KeyType.KEY_DOWN));
                    }
                    return super.keyDown(keycode);
                }

                @Override
                public boolean keyUp(int keycode) {
                    if(focusedComponent != null){
                        focusedComponent.addEventToQueue(new KeyEvent(focusedComponent, keycode, Input.Keys.toString(keycode), KeyEvent.KeyType.KEY_UP));
                    }
                    return super.keyUp(keycode);
                }

                @Override
                public boolean keyTyped(char character) {
                    if(focusedComponent != null){
                        focusedComponent.addEventToQueue(new KeyEvent(focusedComponent, (int) character, Character.toString(character), KeyEvent.KeyType.KEY_TYPED));
                    }
                    return super.keyTyped(character);
                }
            });
        }
    }

    public abstract void initComponents();


    public void fireEvents() {
        fireClickEvents();
        fireMouseEvents();
    }

    public void updateComponent(){
        fireEvents();
    }

    public void windowResized(int width, int height){
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.position.x = width / 2;
        camera.position.y = height / 2;
        camera.update();

        contentPanel.setWidth(width);
        contentPanel.setHeight(height);

        graphics.setViewport(camera);
    }

    public void drawComponent(){
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        graphics.begin();

        contentPanel.updateComponent(graphics);

        graphics.end();
    }

    public CPanel getContentPanel(){
        return contentPanel;
    }

    public void setContentPanel(CPanel panel){
        contentPanel = panel;
    }

    boolean touchedLF = false;
    int xLF;
    int yLF;
    private void fireMouseEvents(){
        if((xLF != Gdx.input.getX() || yLF != Gdx.graphics.getHeight() - Gdx.input.getY()) && contentPanel.containsPoint(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())){
            MouseEvent e = new MouseEvent(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), xLF, yLF, MouseEvent.MouseType.MOVE);
            e.setComponentPosition(contentPanel.getWindowX(), contentPanel.getWindowY());
            contentPanel.addEventToQueue(e);
        }

        touchedLF = Gdx.input.isTouched();
        xLF = Gdx.input.getX();
        yLF = Gdx.graphics.getHeight() - Gdx.input.getY();
    }

    private void fireClickEvents(){
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
    }

    @Override
    public void gainFocus(CComponent component) {
        focusables.forEach(focusable -> {
            if(focusable != component && focusable.isFocused()) focusable.setFocus(false);
        });
        focusedComponent = component;
    }

    @Override
    public void lostFocus(CComponent component){}

    public void registerFocusComponent(Focusable component){
        component.addFocusListener(this);
        focusables.add(component);
    }

    public void resetFocus(){
        focusables.forEach(focusable -> {
            if(focusable.isFocused()) focusable.setFocus(false);
        });
        focusedComponent = null;
    }
}
