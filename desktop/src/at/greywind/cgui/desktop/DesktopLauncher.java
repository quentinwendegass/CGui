package at.greywind.cgui.desktop;

import at.greywind.cgui.CWindow;
import at.greywind.cgui.WindowLauncher;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import at.greywind.cgui.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class DesktopLauncher {
	public static void main (String[] arg) {
		WindowLauncher launcher = new WindowLauncher();
		launcher.setTitle("Test");
		launcher.setWidth(500);
		launcher.setHeight(500);
		launcher.setResizable(false);
		launcher.start(new CWindow(launcher));
	}
}
