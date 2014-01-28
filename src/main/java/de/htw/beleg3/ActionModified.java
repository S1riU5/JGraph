package de.htw.beleg3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;


public class ActionModified implements ActionListener {
    
    JButton button;
    Window frame;
    
    
   ActionModified(JButton button,Window frame) {
       this.button = button;
       this.frame = frame;
    }
    
    public void actionPerformed(ActionEvent e) {
        DrawOptions dro = new DrawOptions("test", "test2", frame);
            dro.setVisible(true);
        
        
    }
    
    //Build actionlistener for the window [ ]
    

}
