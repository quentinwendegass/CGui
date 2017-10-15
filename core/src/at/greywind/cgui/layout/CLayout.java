package at.greywind.cgui.layout;


public interface CLayout{


    Cell align(int alignment);

    Cell padLeft(int padding);

    Cell padRight(int padding);

    Cell padBottom(int padding);

    Cell padTop(int padding);

    Cell pad(int padding);

    Cell pad(int left, int right, int bottom, int top);

    Cell center();

    Cell top();

    Cell right();

    Cell bottom();

    Cell left();

    Cell width(int width);

    Cell height(int height);
}
