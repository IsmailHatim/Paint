import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public abstract class Figure implements Serializable {
    protected Color c;
    protected Point o;
    protected boolean filled;
    protected int brushBborder;

    public Figure(Color c, Point o,boolean filled){
        this.filled = filled;
        this.c = c;
        this.o = o;
    }
    public abstract void setBoundingBox(int lengthBB, int widthBB);

    public abstract void draw(Graphics g);

    //Getter et Setter -----
    public Color getColor(){return c;}
    public Point getOrigin(){return o;}
    public boolean getFilled(){return filled;}
    public int getBrushBborder(){return brushBborder;}

    public void setOrigin(Point o){this.o = o;}
    public void setColor(Color c){this.c = c;}
    public void setFilled(boolean filled){this.filled=filled;}
    public void setBrushBborder(int border){this.brushBborder = border;}

    @Override
    public String toString() {return "Origine:"+o+", couleur:"+c+", rempli:"+filled;}
}
