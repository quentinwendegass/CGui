package at.greywind.cgui;

import com.badlogic.gdx.ApplicationListener;

public interface CApplicationListener extends ApplicationListener {

    @Override
    void create();

    @Override
    void resize(int width, int height);

    @Override
    void render();

    @Override
    default void pause(){}

    @Override
    default void resume(){}

    @Override
    void dispose();
}
