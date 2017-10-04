package at.greywind.cgui;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class WindowLauncher implements CApplicationListener{

    private LwjglApplicationConfiguration configuration;
    private CWindow window;

    public WindowLauncher() {
        configuration = new LwjglApplicationConfiguration();
    }

    public void start(CWindow window){
        this.window = window;
        new LwjglApplication(this, configuration);
    }

    public void setWidth(int width){
        configuration.width = width;
    }

    public void setHeight(int height){
        configuration.height = height;
    }

    public void setX(int x){
        configuration.x = x;
    }

    public void setY(int y){
        configuration.y = y;
    }

    public void setForegroundFPS(int fps){
        configuration.foregroundFPS = fps;
    }

    public void setBackgroundFPS(int fps){
        configuration.backgroundFPS = fps;
    }

    public void setFullscreen(boolean fullscreen){
        configuration.fullscreen = fullscreen;
    }

    public void setResizable(boolean resizable){
        configuration.resizable = resizable;
    }

    public void setTitle(String title){
        configuration.title = title;
    }

    public void setIcon(String internalPath){
        configuration.addIcon(internalPath, Files.FileType.Internal);
    }

    public int getX(){
        return configuration.x;
    }

    public int getY(){
        return configuration.y;
    }

    public int getWidth(){
        return configuration.width;
    }

    public int getHeight(){
        return configuration.height;
    }

    public String getTitle(){
        return configuration.title;
    }

    public boolean isFullscreen(){
        return configuration.fullscreen;
    }

    public boolean isResizable(){
        return configuration.resizable;
    }

    @Override
    public void create() {
        window.init();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        window.drawComponent();
    }

    @Override
    public void dispose() {

    }
}
