import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class FileMenuHandler implements ActionListener {
   JFrame jframe;
   int a = 0;
   public FileMenuHandler (JFrame jf) {
      jframe = jf;
   }
   public void actionPerformed(ActionEvent event) {
      String  menuName;
      menuName = event.getActionCommand();
      if (menuName.equals("Open"))
         openFile();
      else if (menuName.equals("Quit"))
         System.exit(0);
      else if(menuName.equals("Number to Letter"))
         convertNum();
      else if(menuName.equals("Letter to Number"))
         convertLet();
   } //actionPerformed

    private void openFile( ) {
      JFileChooser chooser;
      int status;
      chooser = new JFileChooser( );
      status = chooser.showOpenDialog(null);
      if (status == JFileChooser.APPROVE_OPTION) 
         readSource(chooser.getSelectedFile());
      else 
         JOptionPane.showMessageDialog(null, "Open File dialog canceled");
     } //openFile
  
     private void readSource(File chosenFile) {
        String chosenFileName = chosenFile.getName();
        TextFileInput inFile = new TextFileInput(chosenFileName);
        String ssn;
        int subscript = 0;
        Container myContentPane = jframe.getContentPane();
       TextArea myTextArea = new TextArea();
        TextArea mySubscripts = new TextArea();
        myContentPane.add(myTextArea, BorderLayout.EAST);
        myContentPane.add(mySubscripts, BorderLayout.WEST);
    
        ssn = inFile.readLine();
        while (ssn != null) {
           mySubscripts.append(Integer.toString(subscript++)+"\n");
           myTextArea.append(ssn+"\n");
          ssn = inFile.readLine();
        } //while
      jframe.setVisible(true);  
     }

     private void convertNum()
     {
         String grade = JOptionPane.showInputDialog(null, "Enter a grade number");
         Grade gradeInput;
         try{
            gradeInput = new Grade(Integer.parseInt(grade));
            JOptionPane.showMessageDialog(null, "Your grade for this class is a " + gradeInput);
         }
         catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Please enter a valid number");
         }
         catch(IllegalGradeException f)
         {
            JOptionPane.showMessageDialog(null, "Please enter a non-negative number");
         }
     }

     private void convertLet()
     {
         String grade = JOptionPane.showInputDialog(null, "Enter a letter grade");
         Grade gradeInput;
         try{
            if(!grade.matches("A[+]?||A[-]?||B[+]?||B[-]?||C[+]?||C[-]?||D[+]?||F")) throw new IllegalGradeException("illegal letter grade");
            gradeInput = new Grade(grade);
            JOptionPane.showMessageDialog(null, "Your grade for this class is at least a " + gradeInput.getNum());
         }
         catch(IllegalGradeException e){
            JOptionPane.showMessageDialog(null,"Please enter a valid letter grade");
         }
     }
}
