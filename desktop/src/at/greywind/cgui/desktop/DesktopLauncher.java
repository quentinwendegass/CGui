package at.greywind.cgui.desktop;

import at.greywind.cgui.WindowLauncher;
import at.greywind.cgui.test.TestWindow;

public class DesktopLauncher {
	public static void main (String[] arg) {
		WindowLauncher launcher = new WindowLauncher();
		launcher.setTitle("Test");
		launcher.setWidth(500);
		launcher.setHeight(500);
		launcher.setResizable(false);
		launcher.start(new TestWindow(launcher));
	}
}
