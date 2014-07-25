/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplecalc;

//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Container;

public class SimpleCalc implements ActionListener{
 
    JFrame guiFrame;
    JPanel buttonPanel;
    JTextField numberCalc;
    String calcOperation ;
    int currentCalc;
    
    //Note: Typically the main method will be in a
    //separate class. As this is a simple one class
    //example it's all in the one class.
    public static void main(String[] args) {
     
         //Use the event dispatch thread for Swing components
         EventQueue.invokeLater(new Runnable()
         {
             
            @Override
             public void run()
             {
                 
                 new SimpleCalc();         
             }
         });
              
    }
    
    public SimpleCalc()
    {
        guiFrame = new JFrame();
        
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Simple Calculator");
        guiFrame.setSize(300,300);
      
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        
        numberCalc = new JTextField();
        numberCalc.setHorizontalAlignment(JTextField.RIGHT);
        numberCalc.setEditable(false);
        
        guiFrame.add(numberCalc, BorderLayout.NORTH);
        
        buttonPanel = new JPanel();
               
        //Make a Grid that has three rows and four columns
        buttonPanel.setLayout(new GridLayout(6,3));   
        guiFrame.add(buttonPanel, BorderLayout.CENTER);
        
        //Add the number buttons
        for (int i=1;i<10;i++)
        {
            addButton(buttonPanel, String.valueOf(i));
        }
        addButton(buttonPanel, "0");
        addButton(buttonPanel, "+");
        addButton(buttonPanel, "-");
        addButton(buttonPanel, "/");
        addButton(buttonPanel, "*");
        addButton(buttonPanel, "MOD");
        addButton(buttonPanel, "=");
        
        guiFrame.setVisible(true);  
    }
    
    //All the buttons are following the same pattern
    //so create them all in one place.
    private void addButton(Container parent, String name) {
        JButton but = new JButton(name);
        but.setActionCommand(name);
        if ( !added.isIntNumber(name) && name != "=" ) {
            but.addActionListener( new OperatorAction(name) );
        } 
        else if ( name == "=" ) {
            but.addActionListener( new EqualAction() );
        } 
        else {
            but.addActionListener(this);
        }
        parent.add(but);
    }
    
    //As all the buttons are doing the same thing it's
    //easier to make the class implement the ActionListener
    //interface and control the button clicks from one place
    @Override
    public void actionPerformed(ActionEvent event) {
        //get the Action Command text from the button
        String action = event.getActionCommand();
        
        //set the text using the Action Command text
        if ( added.isIntNumber(action) ){
            numberCalc.setText( numberCalc.getText() + action );  
        }
        else {
            numberCalc.setText(action);  
        }
        
    }

    private class OperatorAction implements ActionListener {
        private String operator;
        
        public OperatorAction(String operation) {
            operator = operation;
        }
        
        public void actionPerformed(ActionEvent event) {
            currentCalc = Integer.parseInt(numberCalc.getText());
            numberCalc.setText( Integer.toString( 
                    (int)added.calcola( 
                            (float)currentCalc , 
                            calcOperation , 
                            Float.parseFloat(numberCalc.getText()) 
                    ) 
            ) );
            numberCalc.setText( "" );
            calcOperation = operator;
        }
    }

    private class EqualAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String schermo = numberCalc.getText();
            if ( !schermo.isEmpty() )
                numberCalc.setText( Integer.toString( 
                    (int)added.calcola( 
                            (float)currentCalc , 
                            calcOperation , 
                            Float.parseFloat(schermo) 
                    ) 
                ) );
        }
    }
}

////////////////////////////////////////////////////////////////////////////////
class added {
    public static boolean isIntNumber(String numero) {
        try {
            Integer.parseInt(numero);
            return true;
            }
        catch(Exception e){return false;}
    }

    /**
     *
     */
    public static float calcola(float left, String calcOperation, float right){
        if (calcOperation == "+") {
            return left + right;
        }
        else if (calcOperation == "-") {
            return left - right;    
        }
        else if (calcOperation == "/") {
            return left / right;    
        }
        else if (calcOperation == "*") {
            return left * right;    
        }
        else if (calcOperation == "MOD") {
            return left % right;    
        }
        else {
            return 0;
        }
    }

}
////////////////////////////////////////////////////////////////////////////////
