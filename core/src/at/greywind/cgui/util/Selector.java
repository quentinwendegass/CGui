package at.greywind.cgui.util;

import java.util.ArrayList;

public abstract class Selector<T>{

    private ArrayList<T> selectables = new ArrayList<>();
    private T selected = null;

    public abstract void executeAction(T selected, ArrayList<T> selectables);

    public void add(T selectable){
        selectables.add(selectable);

        if(selected == null) selected = selectable;
    }

    public void add(T... selectables){
        for(T s : selectables){
            this.selectables.add(s);
            if(selected == null) selected = s;
        }
    }

    public void set(int index){
        selected = selectables.get(index);
        executeAction(selected, selectables);
    }

    public void set(T selectable){
        if(selectables.contains(selectable)){
            selected = selectable;
            executeAction(selected, selectables);
        }else{
            throw new RuntimeException("Object not found in selector list!");
        }
    }
}
