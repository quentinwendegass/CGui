package at.greywind.cgui.cgraphic;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class CGraphics {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    private CColorGradient color;

    public CGraphics(){
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        spriteBatch = new SpriteBatch();
        color = new CColorGradient(CColor.WHITE);
    }

    public void begin(){
        shapeRenderer.begin();
    }

    public void end(){
        shapeRenderer.end();
    }

    public void setColor(CColor color){
        setColor(new CColorGradient(color));
    }

    public void setColor(CColorGradient gradient){
        color = gradient;
    }

    public void drawTexture(Texture texture, float x, float y){
        drawTexture(texture, x, y, CColor.WHITE);
    }

    public void drawTexture(Texture texture, float x, float y, CColor color){
        if(shapeRenderer.isDrawing()){
            shapeRenderer.end();
            spriteBatch.setColor(getColor(color));
            spriteBatch.begin();
            spriteBatch.draw(texture, x, y);
            spriteBatch.end();
            shapeRenderer.begin();
        }else{
            spriteBatch.begin();
            spriteBatch.draw(texture, x, y);
            spriteBatch.end();
        }
    }

    public void drawRect(float x, float y, float width, float height){
        drawRect(x,y,x,y,width,height,1,1,0);
    }

    public void drawRect(float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float degree){
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(x, y, originX, originY, width, height, scaleX, scaleY, degree, getColor(color.getBottomLeft()), getColor(color.getBottomRight()), getColor(color.getTopRight()), getColor(color.getTopLeft()));
    }

    public void drawFilledRect(float x, float y, float width, float height){
        drawFilledRect(x,y,x,y,width,height,1,1,0);
    }

    public void drawFilledRect(float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float degree){
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(x, y, originX, originY, width, height, scaleX, scaleY, degree, getColor(color.getBottomLeft()), getColor(color.getBottomRight()), getColor(color.getTopRight()), getColor(color.getTopLeft()));
    }

    public void drawCircle(float x, float y, float radius){
        drawCircle(x,y,radius, Math.max(1, (int)(6 * (float)Math.cbrt(radius))));
    }

    public void drawCircle(float x, float y, float radius, int segments){
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(x,y,radius, segments);
    }

    public void drawFilledCircle(float x, float y, float radius){
        drawCircle(x,y,radius, Math.max(1, (int)(6 * (float)Math.cbrt(radius))));
    }

    public void drawFilledCircle(float x, float y, float radius, int segments){
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x,y,radius, segments);
    }

    public void drawLine(float x1, float y1, float x2, float y2){
        shapeRenderer.line(x1, y1, x2, y2, getColor(color.getBottomLeft()), getColor(color.getBottomRight()));
    }

    private Color getColor(CColor c){
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
    }

}
