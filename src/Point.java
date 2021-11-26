import java.io.Serializable;

public class Point implements Serializable {
    private int X;
    private int Y;

    public Point(){
        this.X = 0;
        this.Y = 0;
    }

    public Point(int X, int Y){
        this.X = X;
        this.Y = Y;
    }

    //Getter et Setter -----
    public int getX() {return X;}
    public int getY() {return Y;}

    public void setX(int X) {this.X = X;}
    public void setY(int Y) {this.Y = Y;}




    @Override
    public String toString() {return "(" + X + "," + Y + ")";}


}
