import java.awt.*;

public class Ellipse extends Figure {
    protected int semiAxysX;
    protected int semiAxysY;

    public Ellipse (Color c, boolean filled){super(c, new Point(0,0),filled);}

    public Ellipse (){
        super(Color.black, new Point(0,0),false);
    }

    public Ellipse(int px, int py, Color c){
        super(c, new Point(px,py),false);
    }

    public void setBoundingBox(int lengthBB, int widthBB){
        semiAxysX = lengthBB/2;
        semiAxysY = widthBB/2;

    }

    public void draw(Graphics g){
        int x = getOrigin().getX();
        int y = getOrigin().getY();
        g.setColor(c);
        if (filled){
            g.fillOval(x, y, 2 * getSemiAxysX(), 2 * getSemiAxysY());
        }
        else {
            g.drawOval(x, y, 2 * getSemiAxysX(), 2 * getSemiAxysY());
        }
    }

    //Getter et Setter -----
    public int getSemiAxysX(){return semiAxysX;}
    public int getSemiAxysY(){return semiAxysY;}
    public double getPerimeter(){
        double p;
        double PI = Math.PI;
        p = 2*PI*Math.sqrt((semiAxysX^2+semiAxysY^2)/2);
        return p;
    }
    public double getSurface(){return Math.PI*semiAxysY*semiAxysX;}



    public void setSemiAxysX(int semiAxysX){this.semiAxysX = semiAxysX;}
    public void setSemiAxysY(int semiAxysY){this.semiAxysY = semiAxysY;}

    @Override
    public String toString(){return "Ellipse d'origine : " + o + " et de couleur " + c + "\nDemi axe X : " + semiAxysX + "\nDemi axe Y : " + semiAxysY + "\nRempli :" + filled;}

}
