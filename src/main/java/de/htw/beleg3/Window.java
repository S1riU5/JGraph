package de.htw.beleg3;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Window extends Buttonbar {
    
    
    public Window(){
     
    }

    /**
     * Frame method to create the Frame and manage the entire Layout
     */
    public void frame() {
        
        
        //create new frame
        JFrame mainframe = new JFrame();
        //select layout 
        mainframe.setLayout(new BorderLayout());
        //create panel
        JPanel setup = new JPanel();
        //

        setup.setBorder(BorderFactory.createEtchedBorder());
        mainframe.add(setup, BorderLayout.EAST);
        mainframe.setSize(700, 700);
        mainframe.setVisible(true);

    }

    // uses a layout to structure all JPanels
    // chose and Implement Layout [ ]
    // include buttonPanel[ ]
    // Include GraphPanel [ ]
    // Include ActionMOdifier [ ]

}
