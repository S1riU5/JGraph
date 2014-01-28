package de.htw.beleg3;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class DrawOptions extends JDialog {
    
    JButton ok;
    JTextField lengthName;
    
    
    public DrawOptions(String framename,String componetname,Window frame){
        // übergeben mit Window.this
        super(frame);
        setTitle(framename);
        settings();
        
        
        
    }
    
    
    private void settings(){
        setResizable(false);
        setModal(true);    
    }
    
    
    
   
 // Graph Panel
    
    // layout [ ]
    // Actionlistener [ ]
}
