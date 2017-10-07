package at.greywind.cgui.desktop;

import at.greywind.cgui.WindowLauncher;
import at.greywind.cgui.test.TestWindow;

public class DesktopLauncher {
	public static void main (String[] arg) {
		WindowLauncher launcher = new WindowLauncher();
		launcher.setTitle("Test");
		launcher.setWindowSize(500, 500);
		launcher.setIdleFPS(120);
		launcher.setResizable(true);
		launcher.start(new TestWindow(launcher));
	}
}
