package de.htw.beleg3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

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

	// ********************************DeklarateConstantFrameLayout****************************************
	final int labelHight = 550;
	final int labelWidth = 500;
	final int labelCoordY = 0;
	final int labelCoordX = 0;
	final int panelCoordX = 500;
	final int panelCoordY = 0;
	final int panelHight = 500;
	final int panelWidth = 200;
	final int textHight = 15;
	final int textLength = 150;
	final int textCoordX = 50;
	final int textCoordY = 0;
	final int buttonCoordX = 0;
	final int buttonCoordY = 15;
	final int buttonWidth = 195;
	final int buttonHight = 30;
	final int nsize = 400;
	final int csize = 400;
	// ********************************DeklarateJButtons************************************************
	JButton addNode;
	JButton addEdge;
	JButton removeNode;
	JButton removeEdge;
	JButton save;
	JButton load;
	JButton Save;
	JButton reP;
	JButton search;
	// ********************************DeklarateTextfields***********************************************
	JTextField nodename;
	JTextField edgelength;
	// ********************************DeklarateLabels***************************************************
	JLabel edgetext;
	JLabel nodetext;
	DrawLine draw;
	// ********************************DeklaratePanels********************************************************
	JPanel menu;
	JPanel DPan;

	// ********************************
	// Array*************************************************
	String[] snodename;
	Integer bt[];
	// ********************************
	// Objects***************************************************
	Graph Drakular;
	Searcher se;
	Search so;

	/**
	 * Constructer: Window (MainFrame)
	 */
	// **************************************ConstructorOfWindwo********************************************
	public Window() {
		super("Graph");
		degree(1);
		runloop();
		bt = null;

	}

	/**
	 * Runloope
	 */
	// **********************************************runloopMethod******************************************
	public void runloop() {
		framesettings();
		frame();

	}

	/**
	 * get contains framesettings
	 */
	// **********************************************framesettingsMethod************************************
	public void framesettings() {

		setSize(700, 550);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * calculate the distance between all points in Graph()
	 * 
	 * @param nodenumber
	 * @return degree
	 */
	// **************************************************degree***************************************************
	private double degree(int nodenumber) {

		return Math.toDegrees(2 * Math.PI) / nodenumber;

	}

	/**
	 * Frame method to create the Frame and manage the entire Layout
	 */
	// *****************************************frameMethod************************************************
	public void frame() {
		// set layout on frame
		setLayout(null);

		// create JPanel
		menu = new JPanel();
		// initialize Buttons
		addNode = new JButton("Add Node");
		removeNode = new JButton("Remove Node");
		removeEdge = new JButton("Remove Edge");
		addEdge = new JButton("Add Edge");
		save = new JButton("Save");
		load = new JButton("Load");
		reP = new JButton("Repaint");
		search = new JButton("Search");

		// initialize Label with text
		nodetext = new JLabel("Enter nodename");
		edgetext = new JLabel("Enter edgelength");
		draw = new DrawLine();
		// initialize textlable
		nodename = new JTextField();
		edgelength = new JTextField();

		// initialize Objects
		Drakular = new Graph(20);
		se = new Searcher();
		so = new Search(Window.this, Drakular);
		// initialize textformat
		Font schrift = (nodetext.getFont().deriveFont(Font.BOLD + Font.ITALIC,
				12));

		// set Layout1 on Panel
		menu.setLayout(null);

		// ********************************************setCoordinatesPanel**********************************************
		menu.setBounds(panelCoordX, panelCoordY, panelWidth, panelHight);
		// ********************************************setCoordinatesButtons********************************************
		addNode.setBounds(buttonCoordX, 4 * buttonCoordY, buttonWidth,
				buttonHight);
		removeNode.setBounds(buttonCoordX, 10 * buttonCoordY, buttonWidth,
				buttonHight);
		addEdge.setBounds(buttonCoordX, 7 * buttonCoordY, buttonWidth,
				buttonHight);
		removeEdge.setBounds(buttonCoordX, 13 * buttonCoordY, buttonWidth,
				buttonHight);
		save.setBounds(buttonCoordX, 16 * buttonCoordY, buttonWidth,
				buttonHight);
		load.setBounds(buttonCoordX, 19 * buttonCoordY, buttonWidth,
				buttonHight);
		reP.setBounds(buttonCoordX, 25 * buttonCoordY, buttonWidth, buttonHight);
		search.setBounds(buttonCoordX, 22 * buttonCoordY, buttonWidth,
				buttonHight);

		// ********************************************setCoordinatesLabels*******************************************
		nodetext.setBounds(textCoordX, textCoordY, textLength, textHight);
		draw.setBounds(labelCoordX, labelCoordY, labelWidth, labelHight);
		// ********************************************setCoordinatestextfields******************************************
		nodename.setBounds(buttonCoordX, buttonCoordY, buttonWidth, buttonHight);

		// *********************************************settextformat2text**********************************************
		nodetext.setFont(schrift);

		// **************************************************add2Frame****************************************************
		add(menu);
		add(draw);

		// ***********************************************add2PanBUTTONS*************************************************
		menu.add(addNode);
		menu.add(addEdge);
		menu.add(removeNode);
		menu.add(removeEdge);
		menu.add(save);
		menu.add(load);
		menu.add(reP);
		menu.add(search);

		// ********************************************add2PanEXTFIELDS*************************************************
		menu.add(nodetext);

		// *******************************************Add2PanTEXTLABLE**********************************************************
		menu.add(nodename);

		// ************************************add actionlistener to
		// buttons*************************************************
		/**
		 * repaint the Windwo
		 */
		// ************************************repaintActionlistener*************************************************************
		reP.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				repaint();

			}
		});
		/**
		 * add Node to Graph
		 */
		// **************************************AddNodeActionlistener**************************************************
		addNode.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					Drakular.addNode(nodename.getText());

				} catch (IllegalArgumentException iae) {
					System.err.println(iae.getMessage());
				}

				repaint();

			}
		});
		/**
		 * save Graph
		 */
		// *************************************SaveActionListener************************************************************
		save.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Drakular.save();
				repaint();

			}
		});
		/**
		 * Load Graph
		 */
		// ******************************
		// loadActionlistener*****************************************************
		load.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Drakular.load();
				repaint();

			}
		});
		/**
		 * add Edge
		 */
		// *************************************addEdgeActionlistener*********************************************
		addEdge.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ADDdelEDGE edge = new ADDdelEDGE(Window.this, Drakular, true);
				repaint();

			}
		});
		// *****************************removeEdgeActionlistener*********************************************
		/**
		 * delete edge to graph
		 */
		removeEdge.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ADDdelEDGE Deledge = new ADDdelEDGE(Window.this, Drakular,
						false);

				repaint();

			}
		});
		/**
		 * Add Node to graph
		 */
		removeNode.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				RemNode rm = new RemNode(Window.this, Drakular);
				Drakular.printGraph();
				repaint();

			}
		});

		search.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				so.run();
				repaint();

			}
		});

		// ***********************************set windwo
		// Visable***********************************
		setVisible(true);

	}

	/**
	 * 
	 * DrawLine class extends JLable to draw the Graph
	 * 
	 */
	// *************************************DrawLine************************************************
	private class DrawLine extends JLabel {

		/**
		 * create Nodes and Edges on DrawLine
		 */
		// *********************************paintComponent@Override************************************
		@Override
		protected void paintComponent(Graphics g) {
			int tmp[], tmp2[] = new int[2];
			int tmpCoord[], tmpCoord2[] = new int[2];
			int numberofnodes = Drakular.getNumerOfNodes();
			List<String>useName = new ArrayList<String>();
			String[] tmpName =Drakular.getNodes();
			//*** fix draw Bug
			//FIXME convert array to list
			useName.clear();
			for (int k = 0; k<tmpName.length; k++){
				if(!tmpName[k].equals("")){
					useName.add(tmpName[k]);
//					System.err.println(useName.get(k));
//					System.err.println(useName.size());
				}
			}
			/*
			 * sample Oval
			 * g.drawOval(25+(csize/2)-(nsize/2),25+(csize/2)-(nsize/2),nsize
			 * ,nsize);
			 */
			// *****************************************Antialaising***********************************
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			for (int i = 0; i < numberofnodes; i++) {

				tmpCoord = getCenter(csize, csize, 25, 25);
				tmp = getCoord(degree(Drakular.getNumerOfNodes()), (csize / 2),
						i);
				g.setColor(Color.LIGHT_GRAY);
				g.fillOval(tmpCoord[0] + tmp[0], tmpCoord[1] + tmp[1], 25, 25);
				g.setColor(Color.RED);
				g.drawString(useName.get(i), tmpCoord[0] + tmp[0],
						tmpCoord[1] + tmp[1]);

			}
			int linepusher = 10;
			g.setColor(Color.BLUE);
			// TODO Zeichenen wenn Beim Löschen von punkt in der punktmenge
			// fehlt der name bis alle punkte dahinter gelöscht
			// sind
			for (int i = 0; i < numberofnodes; i++) {
				for (int j = 0; j < numberofnodes; j++) {
					if (Drakular.getEdgeValue(i, j) >= 0) {
						g.setColor(Color.BLUE);

						tmpCoord = getCenter(csize, csize, 25, 25);
						tmpCoord2 = getCenter(csize, csize, 25, 25);
						tmp = getCoord(degree(Drakular.getNumerOfNodes()),
								(csize / 2), i);
						tmp2 = getCoord(degree(Drakular.getNumerOfNodes()),
								(csize / 2), j);
						int x1 = tmpCoord[0] + tmp[0] + linepusher;
						int y1 = tmpCoord[1] + tmp[1] + linepusher;
						int x2 = tmpCoord2[0] + tmp2[0] + linepusher;
						int y2 = tmpCoord2[1] + tmp2[1] + linepusher;
						g.drawLine(x1, y1, x2, y2);
						g.drawString("" + Drakular.getEdgeValue(i, j),
								(x1 + x2) / 2, (y1 + y2) / 2);

					}

				}

			}
			if (bt != null) {
				tmpCoord = getCenter(csize, csize, 25, 25);
				tmpCoord2 = getCenter(csize, csize, 25, 25);
				g.setColor(Color.RED);
				for (int k = bt.length-1; k > 0; k--) {
					if (bt[k] != null 
							&& bt[k - 1] != null) {
						int[] tmpy = getCoord(
								degree(Drakular.getNumerOfNodes()),
								(csize / 2), (int) bt[k]);
						int[] tmpy2 = getCoord(
								degree(Drakular.getNumerOfNodes()),
								(csize / 2), (int) bt[k - 1]);
						
						g.drawLine(tmpCoord[0]+tmpy[0]+linepusher, tmpCoord[1]+tmpy[1]+linepusher,
								tmpCoord[0]+tmpy2[0]+linepusher, tmpCoord[1]+tmpy2[1]+linepusher);
					}
				}
			}
			super.paintComponent(g);

		}

		/**
		 * 
		 * @param posX
		 *            int
		 * @param posY
		 *            int
		 * @param high
		 *            int
		 * @param weidth
		 *            int
		 * @return int tmp [] draw Coordinates
		 */
		// ***************************getCenter************************************************
		private int[] getCenter(int posX, int posY, int high, int weidth) {
			int[] tmp = new int[2];

			tmp[0] = (25 + (posX / 2) - (high / 2));
			tmp[1] = (25 + (posY / 2) - (weidth / 2));

			return tmp;
		}

		/**
		 * 
		 * @param degree
		 * @param radius
		 * @param multiplicator
		 * @return int Coords [] calculate with coords the position of a Node
		 */
		// ********************************getCoords**********************************************
		private int[] getCoord(double degree, int radius, int multiplicator) {
			int[] Coord = new int[2];
			Coord[0] = ((int) (Math.floor((int) (radius * Math.sin(Math
					.toRadians(degree * multiplicator))))));
			Coord[1] = ((int) (Math.floor(radius
					* Math.cos(Math.toRadians(degree * multiplicator)))));

			return Coord;

		}
	}

	/**
	 * 
	 * RemNode Class frame to select a Node to delete
	 * 
	 */
	// *********************************RemNode
	// Class************************************************
	private class RemNode extends JDialog {
		JPanel remPan;
		JComboBox nodes;
		JButton ok;
		

		public RemNode(JFrame Window, Graph Drakular) {
			super(Window);
			setTitle("Remove Node");
			initializeContent();
			setLocationRelativeTo(Window);
			setVisible(true);
			pack();

		}

		/**
		 * Method to init Content of Frame
		 */
		// ***********************************InitializeContentMethod******************************
		public void initializeContent() {
			remPan = new JPanel();
			ok = new JButton(" OK ");
			nodes = new JComboBox(Drakular.getNodes());
			add(remPan);
			remPan.add(nodes);
			remPan.add(ok);
			

			// ********************************Actionlistener*************************************
			/**
			 * Delet Node
			 */
			ok.addActionListener(new ActionListener() {
				// ******************************Action*******************************************
				public void actionPerformed(ActionEvent arg0) {
					int noddel;
					noddel = nodes.getSelectedIndex();
					Drakular.deleteNode(noddel);
					setVisible(false);

					// TODO Name geht verloren
					Window.this.repaint();
				}
			});
			
			
		}
		// **********************************ActionListener************************************

	}

	/**
	 * Create an dialog frame
	 */
	// **********************Dialog Window ADD and REMOVE
	// EDGE*********************************
	private class ADDdelEDGE extends JDialog {
		// mainpan
		JPanel AddEdgePan;
		// ok Button
		JButton ok;
		// Dropdown menus
		JComboBox startedge;
		JComboBox endedge;
		// JSlide
		JSlider slid;

		// int
		int jmin = 1;
		int jmax = 30;
		int jdef = 15;
		boolean adddel;

		/**
		 * Create an dialog frame
		 * 
		 * @param f
		 *            type JFrame
		 * @param Drakular
		 *            typ Graph
		 * @param adddel
		 *            typ boolen to manage Del/add
		 */
		// **************************Constructor of
		// ADDdelEDGE******************************************
		public ADDdelEDGE(JFrame f, Graph Drakular, boolean test) {
			super(f);
			setTitle("Add Edge");
			setResizable(false);
			setLocationRelativeTo(f);
			frame();
			setModal(true);
			this.adddel = test;
			pack();
			setVisible(true);
		}

		/**
		 * Method to manage the Buttons of dialogframe ADDdelEDGE
		 */
		// ********************************** frame
		// method*******************************************
		private void frame() {
			slid = new JSlider(JSlider.HORIZONTAL, jmin, jmax, jdef);
			DPan = new JPanel();
			ok = new JButton("  OK  ");
			startedge = new JComboBox(Drakular.getNodes());

			add(DPan);

			DPan.add(startedge);

			/**
			 * ActionListener for ok Button in ADDdelEDGE
			 */
			// *************************************add
			// listener**************************************
			ok.addActionListener(new ActionListener() {
				// ************************************Action*********************************************
				public void actionPerformed(ActionEvent e) {
					int x = startedge.getSelectedIndex();
					int y = endedge.getSelectedIndex();
					int val = slid.getValue();
					System.out.println("x:" + x + "y:" + y + " value: " + val);

					try {
						if (adddel) {
							Drakular.addEdge(x, y, val);
						} else {
							Drakular.delEdge(x, y);
						}

					} catch (IllegalArgumentException iae) {
						System.err.println(iae.getMessage());
					}

					setVisible(false);
					repaint();

				}
			});

			startedge.addItemListener(new ItemListener() {
				/**
				 * Item listener for Combobox Startedge
				 */
				// *********************************Item
				// Listener******************************************
				public void itemStateChanged(ItemEvent e) {
					// ************************************Action**********************************************
					if (e.getStateChange() == ItemEvent.SELECTED) {
						int Indexarray = startedge.getSelectedIndex();
						int numberOfEdges = Drakular.getEdgesOfNode(Indexarray).length;
						snodename = new String[Drakular.getNodes().length];

						if (adddel) {
							for (int i = 0; i < Drakular.getNodes().length; i++) {
								if (!Drakular.getEdgesOfNode(Indexarray)[i]) {
									snodename[i] = Drakular.getNodeName(i);
								} else {
									snodename[i] = " - ";
								}
							}
							DPan.add(slid);
						} else {
							for (int i = 0; i < Drakular.getNodes().length; i++) {
								if (Drakular.getEdgesOfNode(Indexarray)[i]) {
									snodename[i] = Drakular.getNodeName(i);
								} else {
									snodename[i] = " - ";
								}
							}

						}

						endedge = new JComboBox(snodename);

						DPan.add(endedge);
						DPan.add(ok);

						startedge.enable(false);

						pack();

					}
				}

			});

		}

	}

	
	private class Search extends JDialog {
		List<String> startlist = new ArrayList<String>();
		List<String> endlist = new ArrayList<String>();
		JComboBox startbox = new JComboBox();
		JComboBox endbox = new JComboBox();
		JButton ok = new JButton("OK");
		JButton Cancel = new JButton("Cancel");
		JPanel panele = new JPanel();

		public Search(JFrame Window, Graph Drakular) {
			super(Window);
			setTitle("Search");
			setModal(true);
			setLocationRelativeTo(Window);
			setResizable(false);
			add(panele);
			panele.add(ok);
			panele.add(startbox);
			panele.add(endbox);
			panele.add(Cancel);
			pack();

		}

		private void fillliststart() {
			int length = Drakular.getNumerOfNodes();
			String[] tmp = Drakular.getNodes();

			startlist.clear();
			for (int i = 0; i < length; i++) {
				startlist.add(i, tmp[i]);
			}

		}

		public void run() {
			managecontent();
			content();
			pack();
			setVisible(true);
		}

		private void managecontent() {
			fillliststart();
			content();
			for (String elem : startlist) {
				startbox.addItem(elem);
			}

		}

		private void content() {

			startbox.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						for (String elem : startlist) {
							endbox.addItem(elem);
						}
						ok.enable(true);

						ok.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent arg0) {
								Integer startnode = null;
								Integer endnode = null;
								String start = (String) startbox
										.getSelectedItem();
								String end = (String) endbox.getSelectedItem();
								String nodes[] = Drakular.getNodes();
								for (int i = 0; i < nodes.length; i++) {
									if (start.equals(nodes[i])) {
										startnode = i;
									} else if (end.equals(nodes[i])) {
										endnode = i;
									}

								}
								if (endnode == null || startnode == null) {
									throw new IllegalStateException(
											"something get wrong!");
								}

								bt = se.backtrack(endnode, se.dms(
										Drakular.getAdjMat(), startnode,
										endnode));
								for (int i = 0; i < bt.length; i++)
									System.out.println(bt[i]);
								setVisible(false);
								repaint();

							}
						});
					}

				}
			});

		}

	}

}
