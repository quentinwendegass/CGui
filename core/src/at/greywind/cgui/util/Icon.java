package at.greywind.cgui.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Icon {

    private Texture texture;
    private float height;
    private float width;
    private float angle;


    public Icon(String internalPath){
        this(internalPath, 0,0,0,true);
    }

    public Icon(String internalPath, float width, float height){
        this(internalPath, width, height, 0, true);
    }

    public Icon(String internalPath, float width, float height, float angle, boolean linearFilter){
        this.texture = new Texture(Gdx.files.internal(internalPath));
        this.angle = angle;
        if(width == 0 || height == 0){
            this.width = texture.getWidth();
            this.height = texture.getHeight();
        }else{
            this.width = width;
            this.height = height;
        }

        if(linearFilter)
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void setAngle(float angle){
        this.angle = angle;
    }

    public Texture getTexture(){
        return texture;
    }

    public float getAngle(){
        return angle;
    }

    public void setScale(float scale){
        height*=scale;
        width*=scale;
    }

    public void setWidth(float width){
        this.width = width;
    }

    public float getWidth(){
        return width;
    }

    public void setHeight(float height){
        this.height = height;
    }

    public float getHeight(){
        return height;
    }
}
