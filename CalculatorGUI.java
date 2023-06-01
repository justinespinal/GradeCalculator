import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;
import java.text.DecimalFormat;
public class CalculatorGUI extends JFrame implements ActionListener{

    JButton letterButton;
    JButton numberButton;
    JButton closeButton;
    Container myContentPane;
    
    public CalculatorGUI(){
        createGUI();
    }

    public void createGUI(){
        this.setSize(800,800);
        this.setLocation(500,250);
        this.setTitle("Grade Calculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        myContentPane= this.getContentPane();
        
        //gui icon set for code for all
        ImageIcon cfa = new ImageIcon("cfa-logo.png");
        this.setIconImage(cfa.getImage());

        JPanel panelIcon = new JPanel();
        panelIcon.setBounds(150,100, 500,525);

        JPanel buttonPanel = new JPanel();
        //buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.setBounds(150,650, 200,100);
        letterButton = new JButton("Letter grades");
        letterButton.addActionListener(this);
        buttonPanel.add(letterButton);

        JPanel buttonPanel2 = new JPanel();
       //buttonPanel2.setBackground(Color.DARK_GRAY);
        numberButton = new JButton("Number grades");
        buttonPanel2.add(numberButton);
        numberButton.addActionListener(this);
        buttonPanel2.setBounds(450,650, 200,100);

        //Main JLabel
        JLabel startLabel = new JLabel("Queens College Code for All Grade Calculator");
        startLabel.setIcon(cfa);
        startLabel.setHorizontalTextPosition(JLabel.CENTER);//sets text to center of the icon
        startLabel.setVerticalTextPosition(JLabel.TOP);//sets text to top of image
        startLabel.setHorizontalAlignment(JLabel.CENTER);//sets label to the CENTER of the screen relative to the GUI's height and width!
        startLabel.setFont(new Font("MV Boli",Font.BOLD,20));
        startLabel.setIconTextGap(-10);
        
        //add start label to the content pane
        panelIcon.add(startLabel);
        myContentPane.add(panelIcon);
        myContentPane.add(buttonPanel);
        myContentPane.add(buttonPanel2);
        createMenu();//sets menu bar onto GUI
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==letterButton)
        {
            letterSelected();
        }
        else if(e.getSource()==numberButton)
        {
            myContentPane.removeAll();
            myContentPane.revalidate();
            myContentPane.repaint();
            numberSelected();
        }
        else if(e.getSource()==closeButton)
        {
            myContentPane.removeAll();
            myContentPane.revalidate();
            myContentPane.repaint();
            createGUI();
        }

    }

    public void letterSelected(){
        LinkedList <Grade> gradeList = new LinkedList<Grade>();
        
        LinkedList<Integer> creditList = new LinkedList<Integer>();
        
        String grade = "";
        String hours = "";
        int credits=0;
        double gpa = 0.0;
        int creditSum = 0;
        double quality=0;
        while(true)
        {
            try{
                grade = JOptionPane.showInputDialog(null, "Enter a Letter Grade to calculate your GPA! Enter STOP to stop");
                if(grade.equals("STOP")) break;
                Grade enteredGrade = new Grade(grade);
                gradeList.add(enteredGrade);
                try{
                    hours = JOptionPane.showInputDialog(null, "How many credits?");
                    credits = Integer.parseInt(hours);
                    
                    if(credits<1 || credits>4) throw new IllegalGradeException("illegal credits");
                    creditList.add(credits);
                    
                }
                catch(IllegalGradeException e)
                {
                    while(credits<1||credits>4)
                    {
                        try{
                            hours = JOptionPane.showInputDialog(null, "Please enter a valid number of credits(from 1 to 4)");
                            credits = Integer.parseInt(hours);
                        }
                        catch(IllegalGradeException f){
                            credits = 0;
                        }
                    }
                    creditList.add(credits);
                    
                }
            }
            catch(IllegalGradeException g)
            {
                JOptionPane.showMessageDialog(null, "That is not a valid letter grade, please re-enter!");
            }
            
        }

        double currGrade;
        int credit;
        ListIterator<Grade> gradeIterator = gradeList.listIterator();
        ListIterator<Integer> creditIterator = creditList.listIterator();

        while(gradeIterator.hasNext() && creditIterator.hasNext())
        {
            currGrade = gradeIterator.next().getGPA();
            credit = creditIterator.next();
            
            creditSum+= credit;
            quality += credit*currGrade;
        }

        gpa = quality/creditSum;
        myContentPane.removeAll();
        myContentPane.revalidate();
        myContentPane.repaint();

        DecimalFormat df = new DecimalFormat("#.##");
        String gpaFinal = df.format(gpa);

        JPanel gpaFinalShower = new JPanel();
        
        JLabel gpaShow = new JLabel("Your GPA is a "+ gpaFinal);
        gpaFinalShower.setBounds(300,250,250,100);
        
        gpaShow.setFont(new Font("MV Boli",Font.BOLD,20));
        gpaFinalShower.add(gpaShow);

        JPanel closeButtonPanel = new JPanel();
        closeButton = new JButton("Return home");
        closeButtonPanel.setBounds(300,450,250,100);
        closeButton.addActionListener(this);
        closeButtonPanel.add(closeButton);

        myContentPane.add(closeButtonPanel);
        myContentPane.add(gpaFinalShower);
    }

    public void numberSelected(){

        LinkedList <Grade> gradeList = new LinkedList<Grade>();
        
        LinkedList<Integer> creditList = new LinkedList<Integer>();
        
        String grade = "";
        String hours = "";
        int credits=0;
        double gpa = 0.0;
        int creditSum = 0;
        double quality=0;

        while(true)
        {
            try{
                grade = JOptionPane.showInputDialog(null, "Enter a Number Grade to calculate your GPA! Enter STOP to stop");
                if(grade.equals("STOP")) break;
                Grade enteredGrade = new Grade(Integer.parseInt(grade));
                gradeList.add(enteredGrade);
                try{
                    hours = JOptionPane.showInputDialog(null, "How many credits?");
                    credits = Integer.parseInt(hours);
                    
                    if(credits<1 || credits>4) throw new IllegalGradeException("illegal credits");
                    creditList.add(credits);
                    
                }
                catch(IllegalGradeException e)
                {
                    while(credits<1||credits>4)
                    {
                        try{
                            hours = JOptionPane.showInputDialog(null, "Please enter a valid number of credits(from 1 to 4)");
                            credits = Integer.parseInt(hours);
                        }
                        catch(IllegalGradeException f){
                            credits = 0;
                        }
                    }
                    creditList.add(credits);
                    
                }
            }
            catch(IllegalGradeException g)
            {
                JOptionPane.showMessageDialog(null, "That is not a valid number grade, please re-enter!");
            }
            
        }

        double currGrade;
        int credit;
        ListIterator<Grade> gradeIterator = gradeList.listIterator();
        ListIterator<Integer> creditIterator = creditList.listIterator();

        while(gradeIterator.hasNext() && creditIterator.hasNext())
        {
            currGrade = gradeIterator.next().getGPA();
            credit = creditIterator.next();
            
            creditSum+= credit;
            quality += credit*currGrade;
        }

        gpa = quality/creditSum;
        myContentPane.removeAll();
        myContentPane.revalidate();
        myContentPane.repaint();

        DecimalFormat df = new DecimalFormat("#.##");
        String gpaFinal = df.format(gpa);

        JPanel gpaFinalShower = new JPanel();
        
        JLabel gpaShow = new JLabel("Your GPA is a "+ gpaFinal);
        gpaFinalShower.setBounds(300,250,250,100);
        
        gpaShow.setFont(new Font("MV Boli",Font.BOLD,20));
        gpaFinalShower.add(gpaShow);

        JPanel closeButtonPanel = new JPanel();
        closeButton = new JButton("Return home");
        closeButtonPanel.setBounds(300,450,250,100);
        closeButton.addActionListener(this);
        closeButtonPanel.add(closeButton);

        myContentPane.add(closeButtonPanel);
        myContentPane.add(gpaFinalShower);
    }

    private void createMenu() {
        JMenuBar menuBar  = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem item;
        FileMenuHandler fmh  = new FileMenuHandler(this);
        item = new JMenuItem("Open"); 
        item.addActionListener( fmh );
        fileMenu.add( item );
        fileMenu.addSeparator(); 
        item = new JMenuItem("Quit"); 
        item.addActionListener( fmh );
        fileMenu.add( item );
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);

        JMenu fileMenu2 = new JMenu("Converter");
        FileMenuHandler cfmh  = new FileMenuHandler(this);
        item = new JMenuItem("Letter to Number");
        item.addActionListener( cfmh );
        fileMenu2.add(item);
        fileMenu.addSeparator(); 
        item = new JMenuItem("Number to Letter");
        item.addActionListener( cfmh );
        fileMenu2.add(item);
        setJMenuBar(menuBar);
        menuBar.add(fileMenu2);

     } //createMenu    
}