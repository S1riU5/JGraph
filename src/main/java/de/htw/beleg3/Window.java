package de.htw.beleg3;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Window extends JFrame {

    JPanel menu = new JPanel();
    JButton addNode = new JButton("Add Node");
    JButton addEdge = new JButton("Add Edge");
    JButton removeNode = new JButton("Remove Node");
    JButton removeEdge = new JButton("Remove Edge");
    JButton Save = new JButton("Save Graph");

    public Window() {
        setSize(700, 700);
        setLayout(new BorderLayout());
        runloop();

    }

    public void runloop() {
        controllbar();
        frame();

    }

    /**
     * Frame method to create the Frame and manage the entire Layout
     */
    public void frame() {
        
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBorder(BorderFactory.createEtchedBorder()); 
        add(menu, BorderLayout.EAST);

        setVisible(true);

    }

    public void controllbar() {

        menu.add(addNode);
        menu.add(removeNode);
        menu.add(addEdge);
        menu.add(removeEdge);
        menu.add(Save);

        addNode.setAlignmentX(0);
        addNode.setMaximumSize(new Dimension(Integer.MAX_VALUE, addNode.getMaximumSize().height));
        addEdge.setAlignmentX(0);
        addEdge.setMaximumSize(new Dimension(Integer.MAX_VALUE, addNode.getMaximumSize().height));
        Save.setAlignmentX(0);
        Save.setMaximumSize(new Dimension(Integer.MAX_VALUE, addNode.getMaximumSize().height));
        removeEdge.setAlignmentX(0);
        removeEdge.setMaximumSize(new Dimension(Integer.MAX_VALUE, addNode.getMaximumSize().height));
        removeNode.setAlignmentX(0);
        removeNode.setMaximumSize(new Dimension(Integer.MAX_VALUE, addNode.getMaximumSize().height));

    }
    

}
