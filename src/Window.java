import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Window extends JFrame implements ActionListener {
    private final String TITLE_ADDER = " - Paint";
    private final Color[] COLOR_LIST = {Color.black, Color.red,Color.green,Color.blue,Color.yellow,Color.pink,Color.magenta,Color.orange};
    private final String[] BORDER_CHOICES = {"1 px", "3 px", "5 px", "7 px"};
    protected Drawing drawingPanel = new Drawing();
    protected JCheckBox filledCheckbox = new JCheckBox("Filled");
    protected JPanel currentColor = new JPanel();



    public Window(String Title, int x, int y)
    {
        super(Title);
        this.setSize(x,y);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//CONTENT PANEL---
        Container contentPanel = this.getContentPane();

    //MENU BAR---
        JMenuBar m = new JMenuBar();

        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("About");

        JMenuItem news = new JMenuItem("New");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem quit = new JMenuItem("Quit");
        JMenuItem authors = new JMenuItem("Authors");

        save.setAccelerator(KeyStroke.getKeyStroke("control S"));
        quit.setBorder(BorderFactory.createMatteBorder(1,0,0,0,Color.decode("#6382bf")));

        authors.addActionListener(this);
        news.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        quit.addActionListener(this);

        menu1.add(news);
        menu1.add(open);
        menu1.add(save);
        menu1.add(quit);
        menu2.add(authors);

        m.add(menu1);
        m.add(menu2);

    //SOUTH PANEL---
        JPanel southPanel = new JPanel();



        JPanel drawings = new JPanel();
        JPanel settings = new JPanel();
        JPanel colors = new JPanel();

        southPanel.setLayout(new GridLayout(1,2));

        //COLORS---
        southPanel.add(colors);
        southPanel.add(settings);
        southPanel.add(drawings);

        colors.setLayout(new GridLayout(2,4));
        drawings.setLayout(new GridLayout(2,2));
        settings.setLayout(new GridLayout(2,2));

        JButton noir = new JButton("Noir");
        JButton rouge = new JButton("Rouge");
        JButton vert = new JButton("Vert");
        JButton bleu = new JButton("Bleu");
        JButton jaune = new JButton("Jaune");
        JButton rose = new JButton("Rose");
        JButton magenta = new JButton("Magenta");
        JButton orange = new JButton("Orange");


        noir.setMargin(new Insets(0,0,0,0));
        rouge.setMargin(new Insets(0,0,0,0));
        vert.setMargin(new Insets(0,0,0,0));
        bleu.setMargin(new Insets(0,0,0,0));
        jaune.setMargin(new Insets(0,0,0,0));
        rose.setMargin(new Insets(0,0,0,0));
        magenta.setMargin(new Insets(0,0,0,0));
        orange.setMargin(new Insets(0,0,0,0));

        noir.setBackground(Color.black);
        rouge.setBackground(Color.red);
        vert.setBackground(Color.green);
        bleu.setBackground(Color.blue);
        jaune.setBackground(Color.yellow);
        rose.setBackground(Color.pink);
        magenta.setBackground(Color.magenta);
        orange.setBackground(Color.orange);

        noir.setForeground(Color.white);
        bleu.setForeground(Color.white);

        noir.addActionListener(this);
        rouge.addActionListener(this);
        vert.addActionListener(this);
        bleu.addActionListener(this);
        jaune.addActionListener(this);
        rose.addActionListener(this);
        magenta.addActionListener(this);
        orange.addActionListener(this);

        colors.add(noir);
        colors.add(rouge);
        colors.add(vert);
        colors.add(bleu);
        colors.add(jaune);
        colors.add(rose);
        colors.add(magenta);
        colors.add(orange);

        //SETTINGS
        JButton brush = new JButton("Brush");
        JComboBox<String> borderCombobox = new JComboBox<>(BORDER_CHOICES);

        currentColor.setBackground(drawingPanel.getColor());
        //currentColor.setBounds();

        filledCheckbox.addActionListener(this);
        brush.addActionListener(this);
        borderCombobox.addActionListener(this);

        settings.add(filledCheckbox);
        settings.add(currentColor);
        settings.add(brush);
        settings.add(borderCombobox);


        //DRAWINGS---
        JButton paintPackageRectangle = new JButton("Rectangle");
        JButton paintPackageSquare = new JButton("Square");
        JButton paintPackageEllipse = new JButton("Ellipse");
        JButton paintPackageCircle = new JButton("Circle");

        paintPackageRectangle.addActionListener(this);
        paintPackageSquare.addActionListener(this);
        paintPackageEllipse.addActionListener(this);
        paintPackageCircle.addActionListener(this);


        drawings.add(paintPackageRectangle);
        drawings.add(paintPackageSquare);
        drawings.add(paintPackageEllipse);
        drawings.add(paintPackageCircle);




        contentPanel.addMouseMotionListener(drawingPanel);
        contentPanel.addMouseListener(drawingPanel);

        contentPanel.add(m,"North");
        contentPanel.add(drawingPanel);
        contentPanel.add(southPanel,"South");



        this.setVisible(true);
    }

@Override
    public void actionPerformed(ActionEvent e)
    {
        String cmd=e.getActionCommand();
        switch(cmd)
        {
            //MENU BAR---
            case "New":
                if (drawingPanel.getTouched() != true){
                    drawingPanel.clear();
                }
                else{
                    Object[] choices = {"Save","Don't Save"};
                    Object defaultChoice = choices[0];
                    JOptionPane save = new JOptionPane();
                    int returnValuesaved = save.showOptionDialog(this,"Do you want to save your file ?", "Paint", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,choices,defaultChoice);
                    if (returnValuesaved == JOptionPane.YES_OPTION){
                        drawingPanel.save();
                        drawingPanel.clear();
                    }
                    else{
                        drawingPanel.clear();
                    }
                }
                this.setTitle(drawingPanel.getFileName()+TITLE_ADDER);
                break;
            case "Open":
                if (drawingPanel.getTouched() != true){
                    drawingPanel.open();
                    this.setTitle(drawingPanel.getFileName()+TITLE_ADDER);
                }
                else{
                    Object[] choices = {"Save","Don't Save"};
                    Object defaultChoice = choices[0];
                    JOptionPane save = new JOptionPane();
                    int returnValuesaved = save.showOptionDialog(this,"Do you want to save your file ?", "Paint", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,choices,defaultChoice);
                    if (returnValuesaved == JOptionPane.YES_OPTION){
                            drawingPanel.save();
                            drawingPanel.open();
                            this.setTitle(drawingPanel.getFileName()+TITLE_ADDER);
                        }
                    else{
                            drawingPanel.open();
                            this.setTitle(drawingPanel.getFileName()+TITLE_ADDER);
                    }

                }
                    break;
            case "Save":
                if (drawingPanel.getSaved()){
                    System.out.println("Sauvegardé à : "+drawingPanel.getFilePath());
                }
                else{
                    drawingPanel.save();
                }
                this.setTitle(drawingPanel.getFileName()+" - Paint");
                break;
            case "Quit":
                this.dispose();
                break;
            case "Authors":
                JOptionPane info = new JOptionPane();
                info.showInternalMessageDialog(info,"Author :  HATIM Ismail", "information", JOptionPane.INFORMATION_MESSAGE);
                break;

            //COLORS---
            case "Noir":
                drawingPanel.setColor(Color.black);
                currentColor.setBackground(Color.black);
                break;
            case "Rouge":
                drawingPanel.setColor(Color.red);
                currentColor.setBackground(Color.red);
                break;
            case "Vert":
                drawingPanel.setColor(Color.green);
                currentColor.setBackground(Color.green);
                break;
            case "Bleu":
                drawingPanel.setColor(Color.blue);
                currentColor.setBackground(Color.blue);
                break;
            case "Jaune":
                drawingPanel.setColor(Color.yellow);
                currentColor.setBackground(Color.yellow);
                break;
            case "Rose":
                drawingPanel.setColor(Color.pink);
                currentColor.setBackground(Color.pink);
                break;
            case "Magenta":
                drawingPanel.setColor(Color.magenta);
                currentColor.setBackground(Color.magenta);
                break;
            case "Orange":
                drawingPanel.setColor(Color.orange);
                currentColor.setBackground(Color.orange);
                break;

            //SETTINGS---
            case "Filled":
                drawingPanel.setFilled(filledCheckbox.isSelected());
                break;
            case "Brush":
                drawingPanel.setNameFigure("Brush");
                break;
            case "comboBoxChanged":
                String comboBoxcmd = ((JComboBox) e.getSource()).getSelectedItem().toString();
                switch (comboBoxcmd){
                    case "1 px":
                        drawingPanel.setBrushBorder(1);
                        break;
                    case "3 px":
                        drawingPanel.setBrushBorder(3);
                        break;
                    case "5 px":
                        drawingPanel.setBrushBorder(5);
                        break;
                    case "7 px":
                        drawingPanel.setBrushBorder(7);
                        break;
                }
                break;

            //DRAWINGS---
            case "Rectangle":
                drawingPanel.setNameFigure("Rectangle");
                break;
            case "Square":
                drawingPanel.setNameFigure("Square");
                break;
            case "Ellipse":
                drawingPanel.setNameFigure("Ellipse");
                break;
            case "Circle":
                drawingPanel.setNameFigure("Circle");
                break;
        }

    }
}
