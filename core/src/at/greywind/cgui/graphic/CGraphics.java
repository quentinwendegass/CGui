package at.greywind.cgui.graphic;


import at.greywind.cgui.component.CComponent;
import at.greywind.cgui.text.CFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.badlogic.gdx.graphics.GL20.GL_BLEND;
import static com.badlogic.gdx.graphics.GL20.GL_ONE_MINUS_SRC_ALPHA;
import static com.badlogic.gdx.graphics.GL20.GL_SRC_ALPHA;

public class CGraphics {

    private int componentX, componentY;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private CFont font;

    private CColorGradient color;

    public CGraphics(){
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        spriteBatch = new SpriteBatch();
        color = new CColorGradient(CColor.WHITE);
    }

    public void begin(){
        Gdx.gl.glEnable(GL_BLEND);
        Gdx.gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin();
    }

    public void end(){
        shapeRenderer.end();
        Gdx.gl.glDisable(GL_BLEND);
    }

    public void setComponent(CComponent c){
        componentX = c.getWindowX();
        componentY = c.getWindowY();
    }

    public void setColor(CColor color){
        setColor(new CColorGradient(color));
    }

    public void setColor(CColorGradient gradient){
        color = gradient;
        shapeRenderer.setColor(getColor(gradient.getBottomLeft()));
    }

    public void setFont(CFont font){
        this.font = font;
    }

    public CFont getFont(){
        return font;
    }

    public void drawText(CharSequence text, float x, float y){
        if(shapeRenderer.isDrawing()){
            end();
            spriteBatch.begin();
            font.draw(spriteBatch, componentX + x, componentY + y, text);
            spriteBatch.end();
            begin();
        }else{
            spriteBatch.begin();
            font.draw(spriteBatch, componentX + x, componentY + y, text);
            spriteBatch.end();
        }
    }

    public void drawTexture(Texture texture, float x, float y){
        drawTexture(texture, x, y, texture.getWidth(), texture.getHeight(), 0, CColor.WHITE);
    }

    public void drawTexture(Texture texture, float x, float y, float width, float height, float angle, CColor color){
        if(shapeRenderer.isDrawing()){
            end();
            spriteBatch.setColor(getColor(color));
            spriteBatch.begin();
            spriteBatch.draw(new TextureRegion(texture), componentX + x, componentY + y, width / 2, height / 2, width, height, 1, 1, angle);
            spriteBatch.end();
            begin();
        }else{
            spriteBatch.begin();
            spriteBatch.draw(texture, x, y);
            spriteBatch.end();
        }
    }

    public void drawRect(float x, float y, float width, float height){
        drawRect(x,y,width / 2,height/2,width,height,1,1,0);
    }

    public void drawRect(float x, float y, float width, float height, float lineWidth){
        drawLine(x - lineWidth / 2, y, x + width + lineWidth / 2, y, lineWidth);
        drawLine(x + width, y, x + width, y + height, lineWidth);
        drawLine(x + width + lineWidth / 2, y + height, x - lineWidth / 2, y + height, lineWidth);
        drawLine(x, y + height, x, y, lineWidth);
    }

    public void drawRect(float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float degree){
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(componentX + x, componentY + y, originX, originY, width, height, scaleX, scaleY, degree, getColor(color.getBottomLeft()), getColor(color.getBottomRight()), getColor(color.getTopRight()), getColor(color.getTopLeft()));
    }

    public void drawFilledRect(float x, float y, float width, float height){
        drawFilledRect(x,y,width / 2,height / 2,width,height,1,1,0);
    }

    public void drawFilledRect(float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float degree){
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(componentX + x, componentY + y, originX, originY, width, height, scaleX, scaleY, degree, getColor(color.getBottomLeft()), getColor(color.getBottomRight()), getColor(color.getTopRight()), getColor(color.getTopLeft()));
    }

    public void drawCircle(float x, float y, float radius){
        drawCircle(x,y,radius, Math.max(1, (int)(6 * (float)Math.cbrt(radius))));
    }

    public void drawCircle(float x, float y, float radius, int segments){
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(componentX + x,componentY + y,radius, segments);
    }

    public void drawFilledCircle(float x, float y, float radius){
        drawFilledCircle(x,y,radius, Math.max(1, (int)(6 * (float)Math.cbrt(radius))));
    }

    public void drawFilledCircle(float x, float y, float radius, int segments){
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(componentX + x,componentY + y,radius, segments);
    }

    public void drawLine(float x1, float y1, float x2, float y2, float lineWidth){
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rectLine(componentX + x1,componentY + y1,componentX + x2, componentY + y2, lineWidth, getColor(color.getBottomLeft()), getColor(color.getBottomRight()));
    }

    public void drawLine(float x1, float y1, float x2, float y2){
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(componentX + x1, componentY + y1, componentX + x2, componentY + y2, getColor(color.getBottomLeft()), getColor(color.getBottomRight()));
    }

    public void drawPoint(float x, float y){
        shapeRenderer.set(ShapeRenderer.ShapeType.Point);
        shapeRenderer.point(componentX + x, componentY + y, 0);
    }

    private Color getColor(CColor c){
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
    }

}
