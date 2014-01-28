package de.htw.beleg3;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class Window extends JFrame {

   
    JButton addNode;
    JButton addEdge;
    JButton removeNode;
    JButton removeEdge;
    JButton Save;
    //Textfields
    JTextField nodename;
    JTextField edgelength;
    // labels
    JLabel edgetext;
    JLabel nodetext;
    // panels 
    JPanel menu;
    
    // dropdown menu
    JComboBox dropNodename;
    JComboBox startege;
    JComboBox endedge;
    Graph Drakular;
    
    
    //Draw line  Object
    
    DrawLine draw;
    
    
   

    public Window() {
        super("Graph");
        runloop();
       

    }
    public void runloop() {
        framesettings();
        frame();
        drop();

    }
    
    public void framesettings(){
        
        setSize(700, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    
    
    public void drop(){
    
    }

  

    /**
     * Frame method to create the Frame and manage the entire Layout
     */
    public void frame() {
        // set layout on frame
        setLayout(null);

        // create JPanel
        menu = new JPanel();
        // create Buttons
        addNode = new JButton("Add Node");
        removeNode = new JButton("Remove Node");
        removeEdge = new JButton("Remove Edge");
        addEdge = new JButton("Add Edge");
        
        //create Label with text
        nodetext = new JLabel("Enter nodename");
        edgetext = new JLabel("Enter edgelength");
        draw = new DrawLine();
        //crete textlable
        nodename = new JTextField();
        edgelength = new JTextField();
        
        //create dropdown menu
        dropNodename = new JComboBox();
        
        //Objects
        Drakular = new Graph(50);
          
        // set textformat
        Font schrift = (nodetext.getFont().deriveFont(Font.BOLD + Font.ITALIC,12));
         
        //set Layout1 on Panel
        menu.setLayout(null);
        
        
       //set Position
        menu.setBounds(500,0, 195, 500);
        
        addNode.setBounds(0, 0, 195, 30);                       removeNode.setBounds(0, 180, 195, 30);
        addEdge.setBounds(0, 90, 195, 30);                      removeEdge.setBounds(0, 225, 195, 30);
       
        
        
        //Instructions (label)
        nodetext.setBounds(50, 30, 150, 15);
        edgetext.setBounds(50, 120, 150, 15);
        draw.setBounds(0, 0, 500, 700);
        // textfields
        nodename.setBounds(0, 45, 195, 30);
        edgelength.setBounds(0, 135, 195, 30);
        
        //dropdown menu
        dropNodename.setBounds(0, 270, 195, 30);
       
        //add Textformater to text
        nodetext.setFont(schrift); 
        edgetext.setFont(schrift);
        
        
        // add JPanel and Label to Frame
        add(menu);
        add(draw);
       // add Components to Panel (menu)
        //BUTTONS
        menu.add(addNode);           menu.add(addEdge);
        menu.add(removeNode);        menu.add(removeEdge);
        
        //TEXTFIELDS
        menu.add(nodetext);          menu.add(edgetext);
       
        //TEXTLABLE
        menu.add(nodename);          menu.add(edgelength);
        
        //DROPDOWNMENU
        menu.add(dropNodename);
        
        
        
  
        // set windwo Visable
        setVisible(true);

    }
    
    private class DrawLine extends JLabel{
        
        @Override
        protected void paintComponent(Graphics g) {
            for(int i= 0; i<Drakular.getNodes().length; i++ )
                
            g.drawOval(50, 50, 5, 5);
            super.paintComponent(g);
        }
        
    }
    
    private class Boxhandler implements ItemListener{
       
        public void itemStateChanged(ItemEvent event) {
            // TODO Auto-generated method stub
            
        }
        
    }
 
        
   

}

