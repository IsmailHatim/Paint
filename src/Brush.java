import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Brush extends Figure {
    protected ArrayList<Point> pointList = new ArrayList<>();

    public Brush(Color c, int border){
        super(c,new Point(0,0),true);
        pointList.add(new Point(0,0));
        this.brushBborder = border;
    }

    public void setBoundingBox(int lengthBB, int widthBB){
        Point tmp = new Point(lengthBB,widthBB);
        if (pointList.get(pointList.size()-1) != tmp){
            pointList.add(tmp);
        }
    }

    public void draw(Graphics g){
        g.setColor(c);
        for (Point p : pointList) {
            g.fillOval(p.getX() - Math.round(brushBborder / 2), p.getY() + Math.round(brushBborder / 2), brushBborder, brushBborder);
        }
    }

    public void update(){

    }

    //Getter et Setter -----
    public ArrayList<Point> getPointList(){return pointList;}

    public void setPointList(ArrayList<Point> pointList){this.pointList = pointList;}

    @Override
    public String toString(){return "Brush d'origine : " + o + " et de couleur " + c + "\nListe de points : " + pointList;}
}
