package at.greywind.cgui;

import at.greywind.cgui.util.CApplicationListener;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class WindowLauncher implements CApplicationListener {

    private Lwjgl3ApplicationConfiguration configuration;
    private CWindow window;

    private String title;
    private boolean isFullscreen = false;
    private boolean isResizable = true;


    public WindowLauncher() {
        configuration = new Lwjgl3ApplicationConfiguration();
    }

    public void start(CWindow window){
        this.window = window;
        new Lwjgl3Application(this, configuration);

    }

    public void setWindowSize(int width, int height){
        configuration.setWindowedMode(width, height);
    }

    public void setWindowPosition(int x, int y){
        configuration.setWindowPosition(x, y);
    }

    public void setIdleFPS(int fps){
        configuration.setIdleFPS(fps);
    }

    public void setFullscreen(boolean fullscreen){
        configuration.setFullscreenMode(Gdx.graphics.getDisplayMode());
        this.isFullscreen = fullscreen;
    }

    public void setResizable(boolean resizable){
        configuration.setResizable(resizable);
        this.isResizable = resizable;
    }

    public void setTitle(String title){
        configuration.setTitle(title);
        this.title = title;
    }

    public void setIcon(String internalPath){
        configuration.setWindowIcon(Files.FileType.Internal, internalPath);
    }

    public int getWidth(){
        return Gdx.graphics.getWidth();
    }

    public int getHeight(){
        return Gdx.graphics.getHeight();
    }

    public String getTitle(){
        return title;
    }

    public boolean isFullscreen(){
        return isFullscreen;
    }

    public boolean isResizable(){
        return isResizable;
    }

    @Override
    public void create() {
        window.init();
        window.initComponents();
    }

    @Override
    public void resize(int width, int height) {
        window.windowResized(width, height);
    }

    @Override
    public void render() {
        window.updateComponent();
        window.drawComponent();
    }

    @Override
    public void dispose() {

    }
}
