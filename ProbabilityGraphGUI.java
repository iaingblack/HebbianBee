/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSlider;
/*     */ 
/*     */ public class ProbabilityGraphGUI implements javax.swing.event.ChangeListener, java.awt.event.ActionListener
/*     */ {
/*     */   JFrame probabilityFrame;
/*     */   ProbabilityGraphDrawer graphPanel;
/*     */   JSlider mSlider;
/*     */   JSlider bSlider;
/*     */   JLabel mValue;
/*     */   JLabel bValue;
/*     */   JLabel mValueLabelLink;
/*     */   JLabel bValueLabelLink;
/*     */   javax.swing.JButton okButton;
/*     */   javax.swing.JButton cancelButton;
/*     */   javax.swing.JButton resetButton;
/*     */   JPanel buttonPanel;
/*     */   ModelParameters modelParameters;
/*     */   
/*     */   public ProbabilityGraphGUI(ModelParameters m, JLabel mL, JLabel bL)
/*     */   {
/*  24 */     this.modelParameters = m;
/*  25 */     this.mValueLabelLink = mL;
/*  26 */     this.bValueLabelLink = bL;
/*  27 */     createFrame();
/*  28 */     createGUI();
/*     */   }
/*     */   
/*     */   private void createFrame() {
/*  32 */     this.probabilityFrame = new JFrame("Change of Direction Probability");
/*  33 */     this.probabilityFrame.setIconImage(new javax.swing.ImageIcon("images/beeIcon.gif").getImage());
/*  34 */     this.probabilityFrame.setSize(new java.awt.Dimension(700, 600));
/*     */     
/*  36 */     java.awt.Dimension screenDim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
/*  37 */     java.awt.Dimension frameDim = this.probabilityFrame.getSize();
/*  38 */     int x = (screenDim.width - frameDim.width) / 2;
/*  39 */     int y = (screenDim.height - frameDim.height) / 2;
/*  40 */     this.probabilityFrame.setLocation(x, y);
/*  41 */     this.probabilityFrame.setVisible(true);
/*  42 */     this.probabilityFrame.setResizable(false);
/*  43 */     this.probabilityFrame.addWindowListener(new java.awt.event.WindowAdapter() {
/*     */       public void windowClosing(java.awt.event.WindowEvent e) {
/*  45 */         ProbabilityGraphGUI.this.probabilityFrame.dispose();
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   private void createGUI() {
/*  51 */     JPanel centerPanel = new JPanel(new java.awt.BorderLayout());
/*  52 */     this.mSlider = new JSlider(3, 50, this.modelParameters.getmValue());
/*  53 */     this.mSlider.addChangeListener(this);
/*  54 */     this.mSlider.setMinorTickSpacing(1);
/*  55 */     this.mSlider.setMajorTickSpacing(3);
/*  56 */     this.mSlider.setPaintTicks(true);
/*  57 */     this.mSlider.setPaintLabels(true);
/*  58 */     this.mSlider.putClientProperty("JSlider.isFilled", Boolean.TRUE);
/*  59 */     this.mValue = new JLabel("m Value: " + this.modelParameters.getmValue());
/*     */     
/*  61 */     this.bSlider = new JSlider(1, 0, 50, (int)this.modelParameters.getbValue());
/*  62 */     this.bSlider.addChangeListener(this);
/*  63 */     this.bSlider.setMinorTickSpacing(1);
/*  64 */     this.bSlider.setMajorTickSpacing(5);
/*  65 */     this.bSlider.setPaintTicks(true);
/*  66 */     this.bSlider.setPaintLabels(true);
/*  67 */     this.bSlider.putClientProperty("JSlider.isFilled", Boolean.TRUE);
/*  68 */     this.bValue = new JLabel("b Value: " + this.modelParameters.getbValue() / 10.0D);
/*  69 */     java.util.Hashtable bLabelTable = new java.util.Hashtable();
/*  70 */     bLabelTable.put(new Integer(this.bSlider.getMinimum()), new JLabel("0.0"));
/*  71 */     bLabelTable.put(new Integer(this.bSlider.getMinimum() + 5), new JLabel("0.5"));
/*  72 */     bLabelTable.put(new Integer(this.bSlider.getMinimum() + 10), new JLabel("1.0"));
/*  73 */     bLabelTable.put(new Integer(this.bSlider.getMinimum() + 15), new JLabel("1.5"));
/*  74 */     bLabelTable.put(new Integer(this.bSlider.getMinimum() + 20), new JLabel("2.0"));
/*  75 */     bLabelTable.put(new Integer(this.bSlider.getMinimum() + 25), new JLabel("2.5"));
/*  76 */     bLabelTable.put(new Integer(this.bSlider.getMinimum() + 30), new JLabel("3.0"));
/*  77 */     bLabelTable.put(new Integer(this.bSlider.getMinimum() + 35), new JLabel("3.5"));
/*  78 */     bLabelTable.put(new Integer(this.bSlider.getMinimum() + 40), new JLabel("4.0"));
/*  79 */     bLabelTable.put(new Integer(this.bSlider.getMinimum() + 45), new JLabel("4.5"));
/*  80 */     bLabelTable.put(new Integer(this.bSlider.getMaximum()), new JLabel("5.0"));
/*  81 */     this.bSlider.setLabelTable(bLabelTable);
/*     */     
/*  83 */     this.graphPanel = new ProbabilityGraphDrawer(this.mSlider, this.bSlider);
/*  84 */     centerPanel.add(this.graphPanel, "Center");
/*  85 */     centerPanel.setBorder(new javax.swing.border.TitledBorder("Probability Graph"));
/*  86 */     this.probabilityFrame.getContentPane().add("Center", centerPanel);
/*     */     
/*  88 */     this.buttonPanel = new JPanel();
/*  89 */     this.okButton = new javax.swing.JButton("OK", new javax.swing.ImageIcon("images/picstartbutton.gif"));
/*  90 */     this.okButton.setMnemonic('O');
/*  91 */     this.okButton.addActionListener(this);
/*  92 */     this.buttonPanel.add(this.okButton);
/*  93 */     this.cancelButton = new javax.swing.JButton("Cancel", new javax.swing.ImageIcon("images/picstopbutton.gif"));
/*  94 */     this.cancelButton.setMnemonic('C');
/*  95 */     this.cancelButton.addActionListener(this);
/*  96 */     this.buttonPanel.add(this.cancelButton);
/*  97 */     this.resetButton = new javax.swing.JButton("Reset", new javax.swing.ImageIcon("images/picslideriterations.gif"));
/*  98 */     this.resetButton.setMnemonic('R');
/*  99 */     this.resetButton.addActionListener(this);
/* 100 */     this.buttonPanel.add(this.resetButton);
/* 101 */     this.buttonPanel.setBorder(new javax.swing.border.EtchedBorder());
/* 102 */     this.probabilityFrame.getContentPane().add("South", this.buttonPanel);
/*     */     
/* 104 */     centerPanel.add(this.mSlider, "South");
/* 105 */     centerPanel.add(this.bSlider, "East");
/*     */     
/* 107 */     JPanel labelPanel = new JPanel(new java.awt.GridLayout(2, 1));
/* 108 */     labelPanel.add(this.mValue);
/* 109 */     labelPanel.add(this.bValue);
/* 110 */     this.probabilityFrame.getContentPane().add(labelPanel, "North");
/* 111 */     this.probabilityFrame.show();
/*     */   }
/*     */   
/*     */   public void actionPerformed(java.awt.event.ActionEvent e) {
/* 115 */     if (e.getSource() == this.okButton) {
/* 116 */       this.modelParameters.setmValue(this.mSlider.getValue());
/* 117 */       this.modelParameters.setbValue(this.bSlider.getValue());
/* 118 */       this.mValueLabelLink.setText("m = " + this.modelParameters.getmValue());
/* 119 */       this.bValueLabelLink.setText("b = " + this.modelParameters.getbValue() / 10.0D);
/* 120 */       this.probabilityFrame.dispose();
/*     */     }
/* 122 */     if (e.getSource() == this.cancelButton) {
/* 123 */       this.probabilityFrame.dispose();
/*     */     }
/* 125 */     if (e.getSource() == this.resetButton) {
/* 126 */       this.mSlider.setValue(this.modelParameters.getmValue());
/* 127 */       this.bSlider.setValue((int)this.modelParameters.getbValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public void stateChanged(javax.swing.event.ChangeEvent e) {
/* 132 */     if (e.getSource() == this.mSlider) {
/* 133 */       this.mValue.setText("m Value: " + this.mSlider.getValue());
/* 134 */       this.graphPanel.repaint();
/*     */     }
/* 136 */     if (e.getSource() == this.bSlider) {
/* 137 */       this.bValue.setText("b Value: " + this.bSlider.getValue() / 10.0D);
/* 138 */       this.graphPanel.repaint();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\ProbabilityGraphGUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */