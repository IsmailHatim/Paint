import java.awt.*;
import javax.swing.*;

public class Circle extends Ellipse {

    public Circle(Color c, boolean filled){super(c, filled);}

    public Circle(){
        super(0,0,Color.black);
    }

    public Circle(int px, int py, Color c){
        super(px, py, c);
    }

    @Override
    public void setSemiAxysX(int semiAxysX){
        this.semiAxysX = semiAxysX;
        this.semiAxysY = semiAxysX;
    }

    @Override
    public void setSemiAxysY(int semiAxysY){
        this.semiAxysY = semiAxysY;
        this.semiAxysX = semiAxysY;
    }

    @Override
    public void setBoundingBox(int lengthBB, int widthBB){
        semiAxysX = Math.min(lengthBB,widthBB);
        semiAxysY = Math.min(lengthBB,widthBB);
    }

    @Override
    public void draw(Graphics g){
        int x = getOrigin().getX();
        int y = getOrigin().getY();
        g.setColor(c);
        if (filled){
            g.fillOval(x, y, Math.min(getSemiAxysX(), getSemiAxysY()), Math.min(getSemiAxysY(), getSemiAxysY()));
        }
        else {
            g.drawOval(x, y, Math.min(getSemiAxysX(), getSemiAxysY()), Math.min(getSemiAxysY(), getSemiAxysY()));
        }
    }

    @Override
    public String toString(){return "Cercle d'origine : " + o + " et de couleur " + c + "\nRayon : " + semiAxysX + "\nRempli :" + filled;}

}
