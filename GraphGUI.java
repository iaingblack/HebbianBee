/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public final class GraphGUI implements java.awt.event.ActionListener, javax.swing.event.ListSelectionListener, java.awt.event.ItemListener, java.awt.event.ComponentListener
/*     */ {
/*     */   private javax.swing.JFrame graphFrame;
/*     */   private javax.swing.JSplitPane splitPane;
/*     */   private JPanel listPanel;
/*     */   private JPanel graphPanel;
/*     */   private JPanel graphControlsPanel;
/*     */   javax.swing.JLabel listLabel;
/*     */   private javax.swing.JLabel graphLabel;
/*     */   private javax.swing.JScrollPane listScrollPane;
/*     */   private javax.swing.JButton quitButton;
/*     */   private javax.swing.JButton drawButton;
/*     */   private javax.swing.JButton resultsButton;
/*     */   private javax.swing.JButton printButton;
/*     */   private javax.swing.JList list;
/*     */   private java.util.ArrayList selectedTrials;
/*     */   private GraphDrawer graphDrawer;
/*     */   private javax.swing.JComboBox choiceList;
/*     */   private javax.swing.JComboBox scaleList;
/*     */   private javax.swing.JCheckBox averageResultsCheckBox;
/*     */   ViewResultsGUI resultsGUI;
/*     */   private final ResultList resultList;
/*     */   
/*     */   public GraphGUI(ResultList resultlist)
/*     */   {
/*  28 */     this.resultList = resultlist;
/*  29 */     createFrame();
/*  30 */     createSplitPane();
/*  31 */     createlist();
/*     */   }
/*     */   
/*     */   private void createFrame()
/*     */   {
/*  36 */     this.graphFrame = new javax.swing.JFrame("Graph of Results");
/*  37 */     this.graphFrame.setIconImage(new javax.swing.ImageIcon("images/beeIcon.gif").getImage());
/*  38 */     this.graphFrame.setSize(new java.awt.Dimension(800, 600));
/*     */     
/*  40 */     java.awt.Dimension screenDim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
/*  41 */     java.awt.Dimension frameDim = this.graphFrame.getSize();
/*  42 */     int x = (screenDim.width - frameDim.width) / 2;
/*  43 */     int y = (screenDim.height - frameDim.height) / 2;
/*  44 */     this.graphFrame.setLocation(x, y);
/*  45 */     this.graphFrame.setVisible(true);
/*  46 */     this.graphFrame.addWindowListener(new java.awt.event.WindowAdapter() {
/*     */       public void windowClosing(java.awt.event.WindowEvent e) {
/*  48 */         GraphGUI.this.graphFrame.dispose();
/*     */       }
/*     */       
/*  51 */       public void window(java.awt.event.WindowEvent e) { GraphGUI.this.graphFrame.dispose();
/*     */       }
/*  53 */     });
/*  54 */     this.graphFrame.addComponentListener(this);
/*     */   }
/*     */   
/*     */   public void componentResized(java.awt.event.ComponentEvent e)
/*     */   {
/*  59 */     drawGraph();
/*     */   }
/*     */   
/*     */   public void componentMoved(java.awt.event.ComponentEvent e) {}
/*     */   
/*     */   public void componentShown(java.awt.event.ComponentEvent e) {}
/*     */   
/*     */   public void componentHidden(java.awt.event.ComponentEvent e) {}
/*     */   
/*     */   private void createSplitPane() {
/*  69 */     this.listPanel = new JPanel(new java.awt.BorderLayout());
/*  70 */     this.listPanel.setBorder(new javax.swing.border.BevelBorder(1));
/*  71 */     this.listScrollPane = new javax.swing.JScrollPane(this.listPanel);
/*  72 */     this.listScrollPane.setMinimumSize(new java.awt.Dimension(70, 0));
/*  73 */     this.listScrollPane.setMaximumSize(new java.awt.Dimension(70, 0));
/*     */     
/*  75 */     this.graphPanel = new JPanel(new java.awt.BorderLayout());
/*  76 */     this.graphPanel.setBorder(new javax.swing.border.BevelBorder(1));
/*  77 */     this.graphPanel.setMinimumSize(new java.awt.Dimension(600, 500));
/*  78 */     this.graphLabel = new javax.swing.JLabel("Nothing Selected");
/*  79 */     this.graphLabel.setBorder(new javax.swing.border.BevelBorder(0));
/*  80 */     this.graphPanel.add(this.graphLabel, "North");
/*     */     
/*  82 */     this.graphControlsPanel = new JPanel(new java.awt.FlowLayout());
/*  83 */     this.graphControlsPanel.setBorder(new javax.swing.border.BevelBorder(0));
/*  84 */     this.drawButton = new javax.swing.JButton("Graph Results", new javax.swing.ImageIcon("images/picviewgraphbutton2.gif"));
/*  85 */     this.drawButton.setMnemonic('G');
/*  86 */     this.drawButton.setToolTipText("Draw The Currently Selected Trials");
/*  87 */     this.drawButton.addActionListener(this);
/*  88 */     this.graphControlsPanel.add(this.drawButton);
/*     */     
/*  90 */     this.resultsButton = new javax.swing.JButton("View Results", new javax.swing.ImageIcon("images/picviewresultsbutton.gif"));
/*  91 */     this.resultsButton.setMnemonic('V');
/*  92 */     this.resultsButton.setToolTipText("View The Results In A Table");
/*  93 */     this.resultsButton.addActionListener(this);
/*  94 */     this.graphControlsPanel.add(this.resultsButton);
/*     */     
/*  96 */     this.printButton = new javax.swing.JButton("Print", new javax.swing.ImageIcon("images/picprintbutton.gif"));
/*  97 */     this.printButton.setMnemonic('P');
/*  98 */     this.printButton.setToolTipText("Print The Current Graph");
/*  99 */     this.printButton.addActionListener(this);
/* 100 */     this.graphControlsPanel.add(this.printButton);
/*     */     
/* 102 */     this.quitButton = new javax.swing.JButton("QUIT", new javax.swing.ImageIcon("images/picquitbutton.gif"));
/* 103 */     this.quitButton.setMnemonic('Q');
/* 104 */     this.quitButton.setToolTipText("Quit The Graph Viewer");
/* 105 */     this.quitButton.addActionListener(this);
/* 106 */     this.graphControlsPanel.add(this.quitButton);
/*     */     
/* 108 */     String[] graphChoicesString = { "Weights", "Flower Lands", "Weights/Lands" };
/* 109 */     this.choiceList = new javax.swing.JComboBox(graphChoicesString);
/* 110 */     this.choiceList.setToolTipText("Choose Which Parameters To Graph");
/* 111 */     this.choiceList.setSelectedIndex(0);
/* 112 */     this.choiceList.addActionListener(this);
/* 113 */     this.graphControlsPanel.add(this.choiceList);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 124 */     this.averageResultsCheckBox = new javax.swing.JCheckBox("Average Results?");
/* 125 */     this.averageResultsCheckBox.setMnemonic('A');
/* 126 */     this.averageResultsCheckBox.setToolTipText("Draws An Average Of The Selected Trials");
/* 127 */     this.averageResultsCheckBox.setSelected(false);
/* 128 */     this.averageResultsCheckBox.addItemListener(this);
/* 129 */     this.graphControlsPanel.add(this.averageResultsCheckBox);
/*     */     
/*     */ 
/* 132 */     this.graphFrame.getContentPane().add(this.graphControlsPanel, "South");
/*     */     
/* 134 */     this.splitPane = new javax.swing.JSplitPane(1, this.listScrollPane, this.graphPanel);
/* 135 */     this.splitPane.setDividerLocation(60);
/* 136 */     this.splitPane.setDividerSize(4);
/* 137 */     this.graphFrame.getContentPane().add(this.splitPane, "Center");
/* 138 */     this.graphFrame.show();
/*     */   }
/*     */   
/*     */   private void createlist()
/*     */   {
/* 143 */     javax.swing.DefaultListSelectionModel selection = new javax.swing.DefaultListSelectionModel();
/* 144 */     javax.swing.DefaultListModel model = new javax.swing.DefaultListModel();
/* 145 */     this.list = new javax.swing.JList(model);
/* 146 */     this.list.setSelectionModel(selection);
/* 147 */     this.list.addListSelectionListener(this);
/* 148 */     for (int i = 0; i < this.resultList.getTrials(); i++) {
/* 149 */       model.addElement("Trial " + (i + 1));
/*     */     }
/*     */     
/*     */ 
/* 153 */     this.listPanel.add(this.list);
/*     */   }
/*     */   
/*     */   public final void actionPerformed(java.awt.event.ActionEvent av)
/*     */   {
/* 158 */     if (av.getSource() == this.quitButton) {
/* 159 */       quit();
/*     */     }
/* 161 */     if (av.getSource() == this.resultsButton)
/*     */     {
/* 163 */       this.resultsGUI = new ViewResultsGUI(this.resultList);
/*     */     }
/* 165 */     if (av.getSource() == this.printButton) {
/* 166 */       printGraph();
/*     */     }
/* 168 */     if (av.getSource() == this.drawButton) {
/* 169 */       drawGraph();
/*     */     }
/* 171 */     if (av.getSource() == this.choiceList) {
/* 172 */       drawGraph();
/*     */     }
/*     */   }
/*     */   
/*     */   public void itemStateChanged(java.awt.event.ItemEvent ie) {
/* 177 */     if (ie.getSource() == this.averageResultsCheckBox) {
/* 178 */       drawGraph();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public final void valueChanged(javax.swing.event.ListSelectionEvent e)
/*     */   {
/* 185 */     setSelectedTrials();
/* 186 */     drawGraph();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void setSelectedTrials()
/*     */   {
/* 193 */     String selection = "";
/* 194 */     this.selectedTrials = null;
/* 195 */     this.selectedTrials = new java.util.ArrayList();
/*     */     
/*     */ 
/* 198 */     int[] selected = this.list.getSelectedIndices();
/*     */     
/* 200 */     for (int i = 0; i < selected.length; i++)
/*     */     {
/* 202 */       selection = selection + (i == 0 ? "" : ",") + (selected[i] + 1);
/*     */       
/*     */ 
/*     */ 
/* 206 */       this.selectedTrials.add("" + selected[i]);
/*     */     }
/* 208 */     this.graphLabel.setText("Trial" + (selected.length == 1 ? "" : "s") + " Selected - " + selection);
/*     */   }
/*     */   
/*     */   private void quit()
/*     */   {
/* 213 */     this.graphFrame.dispose();
/*     */   }
/*     */   
/*     */ 
/*     */   private void drawGraph()
/*     */   {
/* 219 */     if (this.selectedTrials != null) {
/* 220 */       if (this.selectedTrials.size() != 0)
/*     */       {
/* 222 */         java.awt.Dimension panelSize = this.graphPanel.getSize();
/* 223 */         if (this.graphDrawer == null) {
/* 224 */           this.graphDrawer = new GraphDrawer(this.selectedTrials, this.resultList, this.choiceList, this.scaleList, this.averageResultsCheckBox, panelSize);
/* 225 */           this.graphPanel.remove(this.graphDrawer);
/* 226 */           this.graphPanel.add(this.graphDrawer, "Center");
/* 227 */           this.graphDrawer.drawGraph(this.selectedTrials);
/* 228 */           this.graphLabel.setText(this.graphLabel.getText() + " ");
/*     */         }
/*     */         else {
/* 231 */           this.graphDrawer.adjustSize(panelSize);
/* 232 */           this.graphDrawer.drawGraph(this.selectedTrials);
/* 233 */           this.graphPanel.repaint();
/*     */         }
/*     */       } else {
/* 236 */         javax.swing.JOptionPane.showMessageDialog(null, "You Must Select Some Trials To Plot", "OK", 1);
/*     */       }
/* 238 */     } else { javax.swing.JOptionPane.showMessageDialog(null, "You Must Select Some Trials To Plot", "OK", 1);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void printGraph()
/*     */   {
/* 245 */     if (this.graphDrawer == null) {
/* 246 */       javax.swing.JOptionPane.showMessageDialog(null, "Graph Must Be Generated First", "Print Graph Error", 1);
/*     */     }
/*     */     else {
/* 249 */       java.awt.Dimension panelSize = this.graphPanel.getSize();
/* 250 */       this.graphDrawer = new GraphDrawer(this.selectedTrials, this.resultList, this.choiceList, this.scaleList, this.averageResultsCheckBox, panelSize);
/* 251 */       this.graphDrawer.setGraphPrintingDefaults();
/* 252 */       this.graphPanel.remove(this.graphDrawer);
/* 253 */       this.graphPanel.add(this.graphDrawer, "Center");
/* 254 */       this.graphDrawer.drawGraph(this.selectedTrials);
/* 255 */       this.graphLabel.setText(this.graphLabel.getText() + " ");
/*     */       try {
/* 257 */         java.awt.print.PrinterJob pJob = java.awt.print.PrinterJob.getPrinterJob();
/* 258 */         pJob.setJobName("Simulator - Graph Printout");
/* 259 */         pJob.setCopies(1);
/*     */         
/* 261 */         pJob.setPrintable(new java.awt.print.Printable() {
/*     */           public int print(java.awt.Graphics pg, java.awt.print.PageFormat pf, int pageNum) {
/* 263 */             pf.setOrientation(0);
/* 264 */             if (pageNum > 0) {
/* 265 */               return 1;
/*     */             }
/* 267 */             java.awt.Graphics2D graphics2D = (java.awt.Graphics2D)pg;
/* 268 */             graphics2D.translate(pf.getImageableX(), pf.getImageableY());
/* 269 */             java.awt.geom.Rectangle2D.Double rectangle = new java.awt.geom.Rectangle2D.Double();
/* 270 */             rectangle.setRect(72.0D, 72.0D, 72.0D, 72.0D);
/* 271 */             graphics2D.draw(rectangle);
/*     */             
/* 273 */             GraphGUI.this.graphDrawer.paint(graphics2D);
/* 274 */             return 0;
/*     */           }
/*     */         });
/* 277 */         if (!pJob.printDialog()) {
/* 278 */           return;
/*     */         }
/* 280 */         pJob.print();
/*     */       } catch (java.awt.print.PrinterException pe) {
/* 282 */         javax.swing.JOptionPane.showMessageDialog(null, "pe", "Print Graph Error", 1);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\GraphGUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */