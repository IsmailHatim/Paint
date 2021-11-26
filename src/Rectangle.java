import java.awt.*;

public class Rectangle extends Figure {
    protected int length;
    protected int width;


    public Rectangle(Color c, boolean filled){super(c,new Point(0,0),filled);}

    public Rectangle(){
        super(Color.black,new Point(0,0),false);
    }

    public Rectangle(int px, int py, Color c){
        super(c,new Point(px,py),false);
    }

    public void setBoundingBox(int lengthBB, int widthBB){
        length = lengthBB;
        width = widthBB;

    }

    public void draw(Graphics g){
        int x = this.getOrigin().getX();
        int y = this.getOrigin().getY();
        g.setColor(c);
        if (filled) {
            g.fillRect(x, y, this.getLength(), this.getWidth());
        }
        else{
            g.drawRect(x, y, this.getLength(), this.getWidth());
        }
    }

    //Getter et Setter -----
    public int getLength(){return length;}
    public int getWidth(){return width;}
    public int getPerimeter(){return 2*(length+width);}
    public int getSurface(){return length*width;}

    public void setLength(int length){this.length = length;}
    public void setWidth(int width){this.width = width;}

    @Override
    public String toString(){return "Rectangle d'origine : " + o + " et de couleur " + c + "\nLongueur : " + length + "\nLargeur : " + width + "\nRempli :" + filled;}

}
