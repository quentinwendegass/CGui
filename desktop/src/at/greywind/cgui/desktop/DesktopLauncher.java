package at.greywind.cgui.desktop;

import at.greywind.cgui.WindowLauncher;
import at.greywind.cgui.test.EditorWindow;

public class DesktopLauncher {
	public static void main (String[] arg) {
		WindowLauncher launcher = new WindowLauncher();
		launcher.setTitle("Test");
		launcher.setWindowSize(650, 750);
		launcher.setWindowSizeLimits(650,750, 4092, 4092);
		launcher.setIdleFPS(120);
		launcher.setResizable(true);
		launcher.start(new EditorWindow(launcher));
	}
}
