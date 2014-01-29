package de.htw.beleg3;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class Window extends JFrame {
    int nsize = 400;
    int csize = 400;
    JButton addNode;
    JButton addEdge;
    JButton removeNode;
    JButton removeEdge;
    JButton save;
    JButton load;
    JButton Save;
    JButton reP;
    //Textfields
    JTextField nodename;
    JTextField edgelength;
    // labels
    JLabel edgetext;
    JLabel nodetext;
    // panels 
    JPanel menu;
    JPanel DPan;
   
    
    //String Array
       String [] snodename;
    
    // dropdown menu
    JComboBox dropNodename;
   
    
    //Graph Object
    Graph Drakular;
    
    
    //Draw line  Object
    
    DrawLine draw;
    
    
   

    public Window() {
        super("Graph");
        degree(1);
        runloop();
       

    }
    public void runloop() {
        framesettings();
        frame();
        drop();

    }
    
    public void framesettings(){
        
        setSize(700, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    private double degree(int nodenumber){
        
        return Math.toDegrees(2*Math.PI)/nodenumber;
     
        
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
        save = new JButton("Save");
        load = new JButton("Load");
        reP = new JButton("Repaint");
        
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
        Drakular = new Graph(500);
          
        // set textformat
        Font schrift = (nodetext.getFont().deriveFont(Font.BOLD + Font.ITALIC,12));
         
        //set Layout1 on Panel
        menu.setLayout(null);
        
        
       //set Position
        menu.setBounds(500,0, 195, 500);
        
        addNode.setBounds(0, 0, 195, 30);                       
        removeNode.setBounds(0, 180, 195, 30);
        addEdge.setBounds(0, 90, 195, 30);                     
        removeEdge.setBounds(0, 225, 195, 30);
        save.setBounds(0, 270, 195, 30);
        load.setBounds(0, 315, 195, 30);
        reP.setBounds(0, 350, 195, 30);
        
        
        //Instructions (label)
        nodetext.setBounds(50, 30, 150, 15);
        edgetext.setBounds(50, 120, 150, 15);
        draw.setBounds(0, 0, 500, 700);
        // textfields
        nodename.setBounds(0, 45, 195, 30);
        edgelength.setBounds(0, 135, 195, 30);
        
       
      
       
        //add Textformater to text
        nodetext.setFont(schrift); 
        edgetext.setFont(schrift);
        
        
        // add JPanel and Label to Frame
        add(menu);
        add(draw);
       // add Components to Panel (menu)
        //BUTTONS
        menu.add(addNode);           
        menu.add(addEdge);
        menu.add(removeNode);        
        menu.add(removeEdge);
        menu.add(save);
        menu.add(load);
        menu.add(reP);
        
        
        //TEXTFIELDS
        menu.add(nodetext);          
        menu.add(edgetext);
       
        //TEXTLABLE
        menu.add(nodename);          
        menu.add(edgelength);
        
     
        
        //add actionlistener to buttons
        
        reP.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                repaint();
                
                
            }
        });
            
        addNode.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                try{
                    Drakular.addNode(nodename.getText());
                    
                }catch(IllegalArgumentException iae){
                    System.err.println(iae.getMessage());
                }
                
                
                repaint();
                
            }
        });
        
        save.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent arg0) {
                Drakular.save();
                repaint();
                
            }
        });
        
        load.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent arg0) {
                Drakular.load();
                repaint();
                
            }
        });
       
        addEdge.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent arg0) {
                ADDEDGE edge = new ADDEDGE(Window.this, Drakular);
                
                
            }
        });
  
        // set windwo Visable
        setVisible(true);

    }
    
    
    private class DrawLine extends JLabel{
        
        @Override
        protected void paintComponent(Graphics g) {
            int tmp[], tmp2[] = new int [2];
            int tmpCoord[], tmpCoord2[] = new int [2];
               int numberofnodes = Drakular.getNumerOfNodes();  
               //g.drawOval(25+(csize/2)-(nsize/2),25+(csize/2)-(nsize/2),nsize ,nsize);
           for(int i=0; i<numberofnodes; i++){
               
                 
              
               tmpCoord=getCenter(csize,csize,25,25);
               tmp = getCoord(degree(Drakular.getNumerOfNodes()),(csize/2),i);
               g.setColor(Color.LIGHT_GRAY);
               g.fillOval(tmpCoord[0]+tmp[0],tmpCoord[1]+tmp[1],25,25);
               g.drawString(Drakular.getNodeName(i), tmpCoord[0]+tmp[0], tmpCoord[1]+tmp[1]);
              
              
           }
           int linepusher = 10;
           g.setColor(Color.BLUE);
           for(int i = 0; i<numberofnodes; i++){
              for(int j = 0; j< numberofnodes; j++){
                  if(Drakular.getEdgeValue(i, j)>= 0){
                      tmpCoord=getCenter(csize,csize,25,25);
                      tmpCoord2=getCenter(csize,csize,25,25);
                      tmp = getCoord(degree(Drakular.getNumerOfNodes()),(csize/2),i);
                      tmp2 = getCoord(degree(Drakular.getNumerOfNodes()),(csize/2),j);
                      int x1 = tmpCoord[0]+tmp[0]+linepusher;
                      int y1 =tmpCoord[1]+tmp[1]+linepusher;
                      int x2 = tmpCoord2[0]+tmp2[0]+linepusher;
                      int y2 = tmpCoord2[1]+tmp2[1]+linepusher;
                      g.drawLine(x1,y1 ,x2 ,y2);      
                      g.drawString(""+Drakular.getEdgeValue(i, j), (x1+x2)/2, (y1+y2)/2);
                  }
                  
              }
              
           }
           super.paintComponent(g);
         
           
        }
        
        private int[] getCenter(int posX, int posY, int high,int weidth){
            int[] tmp = new int[2];
            
            tmp[0]= (25+(posX/2)-(high/2));
            tmp[1]= (25+(posY/2)-(weidth/2));
            
            return tmp;
        }
        private int[] getCoord(double degree , int radius, int multiplicator ){
           int[] Coord = new int[2];
           Coord[0]=((int)(Math.floor((int)(radius*Math.sin(Math.toRadians(degree*multiplicator)))))); 
           Coord[1]=((int)(Math.floor(radius*Math.cos(Math.toRadians(degree*multiplicator)))));

           
           return Coord;
            
                   
        }}
        
        //FIXME DIALOG FERTIG MACHEN
        private class ADDEDGE extends JDialog{
            //mainpan
            JPanel AddEdgePan;
            // ok Button
            JButton ok;
            //Dropdown menus
            JComboBox startege;
            JComboBox endedge;
            //JSlide
            JSlider slid;
                
            // int
            int jmin = 1;
            int jmax = 30;
            int jdef = 15;
            
            public ADDEDGE(JFrame f, Graph Drakular){
                super(f);
                setTitle("Add Edge");
                setResizable(false);
                
                setLocationRelativeTo(f);
                frame();
                setModal(true);
                pack();
                setVisible(true);
            }
            
            private void frame(){
                slid = new JSlider(JSlider.HORIZONTAL,jmin,jmax,jdef);
                DPan = new JPanel();
                ok = new JButton("  OK  ");
                startege = new JComboBox(Drakular.getNodes());
                
                //TODO Listener aufrufen
              
                        
                        
                add(DPan);     
                DPan.add(slid);
                DPan.add(startege);
                DPan.add(ok);
                //TODO 2. dropdown menu
                //add(endedge);
                //endedge.setEnabled(false);
                
                
                //add listener
                ok.addActionListener(new ActionListener() {
            
                    public void actionPerformed(ActionEvent e) {
                        int x = startege.getSelectedIndex();
                        int y = endedge.getSelectedIndex();
                        int val = slid.getValue();
                        System.out.println("x:"+ x + "y:" + y + " value: " + val);
                        
                        try{
                          Drakular.addEdge(x, y, val);  
                        }catch(IllegalArgumentException iae){
                            System.err.println(iae.getMessage());
                        }
                        
                        setVisible(false);
                        repaint();
                
                    }
                    });
                
                startege.addItemListener(new ItemListener() {
                    
                    public void itemStateChanged(ItemEvent e) {
                        
                        if(e.getStateChange()==ItemEvent.SELECTED){
                            int  Indexarray = startege.getSelectedIndex();
                            int  numberOfEdges = Drakular.getEdgesOfNode(Indexarray).length;
                            snodename = new String[Drakular.getNodes().length];
                            
                           
                            
                            for (int i=0; i<Drakular.getNodes().length; i++){
                                   if (!Drakular.getEdgesOfNode(Indexarray)[i]){
                                       snodename[i] = Drakular.getNodeName(i);
                                   }else {
                                    snodename[i] = " - ";
                                }
                            }
                            
                            endedge = new JComboBox(snodename);
                            DPan.add(endedge);
                            startege.enable(false);
                            
                            pack();
                            
                            
                            
                        }
                        }
                            
                       
                     
                });
               
                
                
                
            }
            
            
          
            
        }
        
    
    
  
            
        
        
    
 
        
   

}

