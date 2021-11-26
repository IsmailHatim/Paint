import java.awt.*;
import javax.swing.*;

public class Square extends Rectangle {

    public Square(Color c,boolean filled){super(c,filled);}

    public Square(){
        super(0,0,Color.black);
    }

    public Square(int px, int py, Color c){
        super(px,py,c);

    }

    @Override
    public void setLength(int length){
        this.length = length;
        this.width = length;
    }

    @Override
    public void setWidth(int width){
        this.width = width;
        this.length = width;
    }
    @Override
    public void setBoundingBox(int lengthBB, int widthBB){
        width = Math.min(lengthBB,widthBB);
        length = Math.min(lengthBB,widthBB);
    }

    @Override
    public void draw(Graphics g){
        int x = getOrigin().getX();
        int y = getOrigin().getY();
        g.setColor(c);
        if (filled){
            g.fillRect(x, y, Math.min(getWidth(), getLength()), Math.min(getWidth(), getLength()));
        }
        else {
            g.drawRect(x, y, Math.min(getWidth(), getLength()), Math.min(getWidth(), getLength()));
        }
    }

    @Override
    public String toString(){return "Carré d'origine : " + o + " et de couleur " + c + "\nLongueur d'un coté : " + length + "\nRempli :" + filled;}

}
