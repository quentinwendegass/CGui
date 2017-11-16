package at.greywind.cgui.test;

import at.greywind.cgui.CWindow;
import at.greywind.cgui.WindowLauncher;
import at.greywind.cgui.test.components.ConfigPanel;
import at.greywind.cgui.test.components.EditorPanel;
import at.greywind.cgui.test.components.LevelList;
import at.greywind.cgui.test.components.Toolbar;

public class EditorWindow extends CWindow{

    public EditorWindow(WindowLauncher launcher) {
        super(launcher);
    }

    @Override
    public void initComponents() {

        Toolbar toolbar = new Toolbar(0, launcher.getHeight() - 30, launcher.getWidth(), 30);

        //LevelList list = new LevelList(0,0, 100, launcher.getHeight() - toolbar.getHeight());

        EditorPanel editorPanel = new EditorPanel(0, 0, 405, 720);

        ConfigPanel configPanel = new ConfigPanel(editorPanel.getWidth() + editorPanel.getWindowX(), 0, launcher.getWidth() - (editorPanel.getWidth() + editorPanel.getWindowX()), launcher.getHeight() - toolbar.getHeight());

        contentPanel.add(editorPanel);
        contentPanel.add(configPanel);
        //contentPanel.add(list);
        contentPanel.add(toolbar);
    }
}
