import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;

public class Drawing extends JPanel implements MouseMotionListener, MouseListener, Serializable {
    private final static int MOUSE_EVENT_Y_OFFSET = 23;
    private final static String EXTENSION = "pnt";
    protected Color c;
    protected Figure figure;
    protected String nameFigure;
    protected ArrayList<Figure> list = new ArrayList<>();
    protected boolean filled;
    protected boolean saved;
    protected boolean touched;
    protected int brushBorder;
    protected String fileName;
    protected String filePath;




    protected int x1;
    protected int y1;


    public Drawing(){
        this.setBackground(Color.white);
        setColor(Color.black);
        setFigure(new Rectangle(getColor(),getFilled()));
        setNameFigure("Rectangle");
        setFilled(false);
        setSaved(false);
        setBrushBorder(1);
        setFileName("Untitled");


    }

    public void clear(){
        setList(new ArrayList<>());
        setFileName("Untitled");
        setSaved(false);
        setTouched(false);
        repaint();
    }

    public void extract(String fileName){
        try{
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            list = (ArrayList<Figure>) ois.readObject();
            ois.close();
        }
        catch (Exception e){
            System.out.println("Problème rencontré lors de la sauvegarde du fichier.");
        }

    }

    public void write(String fileName){
        try{
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(list);
            oos.close();
        }
        catch (Exception e){
            System.out.println("Problème rencontré lors de la sauvegarde du fichier.");
        }
    }

    public void open() {
        JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fc.setApproveButtonText("Open");
        fc.setDialogTitle("Open File");
        fc.setFileFilter(new FileNameExtensionFilter("Paint Files",EXTENSION));
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();
            getList().clear();
            extract(selectedFile.getAbsolutePath());
            repaint();
            setFileName(selectedFile.getName());
            setFilePath(selectedFile.getAbsolutePath());
        }
    }

    public void save() {
        JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fc.setApproveButtonText("Save");
        fc.setDialogTitle("Save File");
        fc.setFileFilter(new FileNameExtensionFilter("Paint Files",EXTENSION));
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();
            write(selectedFile.getAbsolutePath()+"."+EXTENSION);
            setSaved(true);
            setTouched(false);
            setFileName(selectedFile.getName());
            setFilePath(selectedFile.getAbsolutePath());
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for (Figure f : list){
            f.draw(g);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e){
    }


    public void mouseDragged(MouseEvent e){
        int dX = Math.abs(e.getX()-x1);
        int dY = Math.abs(e.getY()-y1);
        if (getNameFigure() == "Circle" | getNameFigure() =="Square"){
            repaint();
            if (e.getX() < x1){
                figure.setOrigin(new Point(Math.max(x1 - dY, e.getX()),y1 - MOUSE_EVENT_Y_OFFSET));
            }
            if (e.getY() < y1){
                figure.setOrigin(new Point(x1,Math.max(y1 - dX, e.getY()) - MOUSE_EVENT_Y_OFFSET));
            }
            if (e.getX() < x1 && e.getY() < y1){
                figure.setOrigin(new Point(Math.max(x1 - dY, e.getX()),Math.max(y1 - dX, e.getY()) - MOUSE_EVENT_Y_OFFSET));
            }
            figure.setBoundingBox(dX,dY);
        }
        else {
            if (getNameFigure() == "Brush") {
                repaint();
                figure.setBoundingBox(e.getX(), e.getY() - MOUSE_EVENT_Y_OFFSET);
            } else {
                repaint();
                if (e.getX() < x1) {
                    figure.setOrigin(new Point(e.getX(), y1 - MOUSE_EVENT_Y_OFFSET));
                }
                if (e.getY() < y1) {
                    figure.setOrigin(new Point(x1, e.getY() - MOUSE_EVENT_Y_OFFSET));
                }
                if (e.getX() < x1 && e.getY() < y1) {
                    figure.setOrigin(new Point(e.getX(), e.getY() - MOUSE_EVENT_Y_OFFSET));
                }
                figure.setBoundingBox(dX,dY);
            }
        }

    }


    @Override
    public void mouseEntered(MouseEvent e){

    }

    @Override
    public void mouseExited(MouseEvent e){

    }


    public void mouseMoved(MouseEvent e){

    }

    @Override
    public void mousePressed(MouseEvent e){
        if (e.getButton() == MouseEvent.BUTTON1) {
            setTouched(true);
            x1 = e.getX();
            y1 = e.getY();
            if (getNameFigure() == "Brush") {
                setFigure(new Brush(getColor(),getBrushBorder()));
                list.add(getFigure());
                figure.setColor(c);
                figure.setBrushBborder(this.getBrushBorder());
            }
            else {
                switch (getNameFigure()) {
                    case "Rectangle":
                        setFigure(new Rectangle(getColor(), getFilled()));
                        break;
                    case "Ellipse":
                        setFigure(new Ellipse(getColor(), getFilled()));
                        break;
                    case "Square":
                        setFigure(new Square(getColor(), getFilled()));
                        break;
                    case "Circle":
                        setFigure(new Circle(getColor(), getFilled()));
                        break;
                }
                list.add(getFigure());
                figure.setColor(c);
                figure.setFilled(filled);
                figure.setOrigin(new Point(e.getX(), e.getY() - MOUSE_EVENT_Y_OFFSET));
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e){
    }



    //Getter et Setter -----
    public Color getColor(){return c;}
    public Figure getFigure(){return figure;}
    public String getNameFigure(){return nameFigure;}
    public ArrayList<Figure> getList(){return list;}
    public boolean getFilled(){return filled;}
    public boolean getSaved(){return saved;}
    public boolean getTouched(){return touched;}
    public int getBrushBorder(){return brushBorder;}
    public String getFileName(){return fileName;}
    public String getFilePath(){return filePath;}



    public void setColor(Color c){this.c = c;}
    public void setFigure(Figure figure){this.figure = figure;}
    public void setNameFigure(String nameFigure){this.nameFigure = nameFigure;}
    public void setList(ArrayList<Figure> list){this.list = list;}
    public void setFilled(boolean filled){this.filled = filled;}
    public void setSaved(boolean saved){this.saved = saved;}
    public void setTouched(boolean touched){this.touched = touched;}
    public void setBrushBorder(int brushBorder){this.brushBorder = brushBorder;}
    public void setFileName(String fileName){this.fileName = fileName;}
    public void setFilePath(String filePath){this.filePath = filePath;}

}

