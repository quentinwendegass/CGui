package at.greywind.cgui.component.button;

import at.greywind.cgui.component.CComponent;
import at.greywind.cgui.event.ChangeListener;

import java.util.ArrayList;

public class CButtonGroup implements ChangeListener{

    private ArrayList<IToggleButton> buttons;
    private boolean isNoButtonPressedAllowed = true;
    private IToggleButton pressedButton;

    public CButtonGroup(){
        buttons = new ArrayList<>();
    }

    @Override
    public void changed(boolean value, CComponent cComponent) {
        if(!isNoButtonPressedAllowed && value == false){
            if(cComponent instanceof IToggleButton){
                ((IToggleButton) cComponent).setPressedWithoutEvent(true);
            }
        }else if(value == false){
            pressedButton = null;
        }

        if(value == true){
            if(pressedButton != null) pressedButton.setPressedWithoutEvent(false);
            pressedButton = (IToggleButton) cComponent;
        }

        if(pressedButton != null){
            if(!pressedButton.isPressed())
                pressedButton.setPressedWithoutEvent(true);
        }

    }

    public void addButton(IToggleButton button){
        if(button.isPressed()){
            if(pressedButton != null) pressedButton.setPressedWithoutEvent(false);
            pressedButton = button;
        }
        if(!isNoButtonPressedAllowed && pressedButton == null)
            pressedButton = button;

        if(button instanceof CComponent) ((CComponent) button).addChangeListener(this);
        buttons.add(button);
    }

    public void addButtons(IToggleButton... buttons){
        for(IToggleButton b : buttons){
            if(b instanceof CComponent) ((CComponent) b).addChangeListener(this);
            if(b.isPressed()){
                if(pressedButton != null) pressedButton.setPressedWithoutEvent(false);
                pressedButton = b;
            }
            this.buttons.add(b);
        }

        if(!isNoButtonPressedAllowed && pressedButton == null)
            pressedButton = buttons[0];
    }

    public IToggleButton getPressedButton() {
        return pressedButton;
    }

    public boolean isNoButtonPressedAllowed() {
        return isNoButtonPressedAllowed;
    }

    public void setNoButtonPressedAllowed(boolean noButtonPressedAllowed) {
        isNoButtonPressedAllowed = noButtonPressedAllowed;
    }
}
