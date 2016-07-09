/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Image;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.util.Hashtable;
/*     */ import java.util.StringTokenizer;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.border.BevelBorder;
/*     */ import javax.swing.border.TitledBorder;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ 
/*     */ public final class FieldGeneratorGUI
/*     */   implements ActionListener, ChangeListener, ItemListener
/*     */ {
/*     */   private JFrame generatorFrame;
/*     */   private JSlider grainSlider;
/*     */   private JSlider iterationSlider;
/*     */   private JSlider seedSlider;
/*     */   private JSlider ratioSlider;
/*     */   JTextField grainTextField;
/*     */   JTextField heightTextField;
/*     */   JTextField widthTextField;
/*     */   JLabel sizeLabel;
/*     */   JLabel xSizeLabel;
/*     */   JLabel ySizeLabel;
/*     */   JLabel baseLabel;
/*     */   JLabel grainLabel;
/*     */   private JLabel iterationLabel;
/*     */   private JLabel seedLabel;
/*     */   JLabel seedValueLabel;
/*     */   private JLabel yellowPixelsLabel;
/*     */   
/*     */   public FieldGeneratorGUI(Field f, JPanel fp)
/*     */   {
/*  18 */     this.baseColor = "blue";
/*  19 */     this.x = 480;
/*  20 */     this.y = 360;
/*  21 */     this.grain = 5;
/*  22 */     this.iterations = 5;
/*     */     
/*  24 */     this.field = f;
/*  25 */     this.fieldPanel = fp;
/*  26 */     createFrame();
/*  27 */     passParameters();
/*     */   }
/*     */   
/*     */   private JLabel bluePixelsLabel;
/*     */   
/*     */   private void createFrame()
/*     */   {
/*  31 */     this.generatorFrame = new JFrame("Field Generator");
/*  32 */     this.generatorFrame.setIconImage(new ImageIcon("images/beeIcon.gif").getImage());
/*  33 */     this.generatorFrame.addWindowListener(new WindowAdapter() {
/*     */       public void windowClosing(WindowEvent e) {
/*  35 */         FieldGeneratorGUI.this.generatorFrame.dispose();
/*     */       }
/*     */       
/*     */ 
/*  39 */     });
/*  40 */     this.guiPanel = new JPanel();
/*     */     
/*  42 */     this.guiPanel.setLayout(new FlowLayout());
/*     */     
/*  44 */     createGrain();
/*  45 */     createIteration();
/*  46 */     createSeed();
/*  47 */     createButton();
/*  48 */     createRatio();
/*  49 */     createLayout();
/*     */     
/*  51 */     this.fieldGenerator = new FieldGenerator(this.ratioSlider, this.yellowPixelsLabel, this.bluePixelsLabel, this.ybRatioLabel, this.autoUpdate);
/*     */     
/*  53 */     this.guiScrollPane = new JScrollPane(this.guiPanel);
/*  54 */     this.generatorFrame.getContentPane().setLayout(new BorderLayout());
/*  55 */     this.generatorFrame.getContentPane().add("South", this.guiPanel);
/*  56 */     this.generatorScrollPane = new JScrollPane(this.fieldGenerator);
/*  57 */     this.generatorFrame.getContentPane().add("Center", this.generatorScrollPane);
/*  58 */     this.generatorFrame.setSize(new Dimension(700, 720));
/*  59 */     this.generatorFrame.setVisible(true);
/*     */     
/*     */ 
/*  62 */     Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
/*  63 */     Dimension frameDim = this.generatorFrame.getSize();
/*  64 */     int x = (screenDim.width - frameDim.width) / 2;
/*  65 */     int y = (screenDim.height - frameDim.height) / 2;
/*  66 */     this.generatorFrame.setLocation(x, y);
/*     */   }
/*     */   
/*     */   JLabel ratioLabel;
/*     */   private JLabel ybRatioLabel;
/*     */   
/*     */   private void createGrain()
/*     */   {
/*  71 */     this.grainPanel = new JPanel();
/*  72 */     this.grainPanel.setToolTipText("Adjusts Size Of Flowers");
/*  73 */     this.grainPanel.setBorder(new TitledBorder("Grain Size"));
/*     */     
/*     */ 
/*  76 */     this.grainSlider = new JSlider(0, 1, 76, 25);
/*  77 */     this.grainSlider.addChangeListener(this);
/*  78 */     this.grainSlider.setMinorTickSpacing(1);
/*  79 */     this.grainSlider.setMajorTickSpacing(5);
/*  80 */     this.grainSlider.setPaintTicks(true);
/*  81 */     this.grainSlider.setPaintLabels(true);
/*  82 */     this.grainSlider.setSnapToTicks(true);
/*  83 */     this.grainSlider.setPreferredSize(new Dimension(550, 50));
/*     */     
/*  85 */     this.grainPanel.add(new JLabel("", new ImageIcon("images/picgrainy.gif", "Grainy Field"), 0));
/*  86 */     this.grainPanel.add(this.grainSlider);
/*  87 */     this.grainPanel.add(new JLabel("", new ImageIcon("images/picblocky.gif", "Blocky Field"), 0));
/*     */     
/*  89 */     Hashtable labelTable = new Hashtable();
/*  90 */     labelTable.put(new Integer(this.grainSlider.getMinimum()), new JLabel("Grainy"));
/*  91 */     labelTable.put(new Integer(this.grainSlider.getMaximum()), new JLabel("Blocky"));
/*  92 */     this.grainSlider.setLabelTable(labelTable);
/*     */   }
/*     */   
/*     */   private JButton generateButton;
/*     */   private JButton saveButton;
/*     */   
/*     */   private void createIteration()
/*     */   {
/*  97 */     this.iterationPanel = new JPanel();
/*  98 */     this.iterationPanel.setToolTipText("Adjust How Many Flowers To Generate - Useful On Small Grain Settings");
/*  99 */     this.iterationPanel.setBorder(new TitledBorder("Iterations"));
/* 100 */     this.iterationLabel = new JLabel("", new ImageIcon("images/picslideriterations.gif"), 4);
/* 101 */     this.iterationPanel.add(this.iterationLabel);
/* 102 */     this.iterationSlider = new JSlider(0, 1, 10, 5);
/* 103 */     this.iterationSlider.addChangeListener(this);
/* 104 */     this.iterationSlider.setMinorTickSpacing(1);
/* 105 */     this.iterationSlider.setMajorTickSpacing(1);
/* 106 */     this.iterationSlider.setPaintTicks(true);
/* 107 */     this.iterationSlider.setPaintLabels(true);
/* 108 */     this.iterationSlider.setSnapToTicks(true);
/* 109 */     this.iterationSlider.putClientProperty("JSlider.isFilled", Boolean.TRUE);
/* 110 */     this.iterationPanel.add(this.iterationSlider);
/*     */   }
/*     */   
/*     */   private JButton loadButton;
/*     */   
/*     */   private void createRatio()
/*     */   {
/* 115 */     this.ratioPanel = new JPanel();
/* 116 */     this.ratioPanel.setToolTipText("Bias The Field Towards One Colour");
/* 117 */     this.ratioPanel.setBorder(new TitledBorder("Yellow/Blue Ratio"));
/* 118 */     this.ratioSlider = new JSlider(0, 1, 101, 50);
/* 119 */     this.ratioSlider.addChangeListener(this);
/* 120 */     this.ratioSlider.setMinorTickSpacing(1);
/* 121 */     this.ratioSlider.setMajorTickSpacing(5);
/* 122 */     this.ratioSlider.setPaintTicks(true);
/* 123 */     this.ratioSlider.setPaintLabels(true);
/* 124 */     this.ratioSlider.setSnapToTicks(true);
/* 125 */     this.ratioSlider.setPreferredSize(new Dimension(300, 50));
/* 126 */     this.ratioPanel.add(new JLabel("", new ImageIcon("images/picbluebias.gif", "Blue Bias"), 0));
/* 127 */     this.ratioPanel.add(this.ratioSlider);
/* 128 */     this.ratioPanel.add(new JLabel("", new ImageIcon("images/picyellowbias.gif", "Yellow Bias"), 0));
/*     */     
/* 130 */     Hashtable labelTable = new Hashtable();
/* 131 */     labelTable.put(new Integer(this.ratioSlider.getMinimum()), new JLabel("100%"));
/* 132 */     labelTable.put(new Integer(this.ratioSlider.getMinimum() + 10), new JLabel("90%"));
/* 133 */     labelTable.put(new Integer(this.ratioSlider.getMinimum() + 20), new JLabel("80%"));
/* 134 */     labelTable.put(new Integer(this.ratioSlider.getMinimum() + 30), new JLabel("70%"));
/* 135 */     labelTable.put(new Integer(this.ratioSlider.getMinimum() + 40), new JLabel("60%"));
/* 136 */     labelTable.put(new Integer(this.ratioSlider.getMinimum() + 50), new JLabel("="));
/* 137 */     labelTable.put(new Integer(this.ratioSlider.getMinimum() + 60), new JLabel("60%"));
/* 138 */     labelTable.put(new Integer(this.ratioSlider.getMinimum() + 70), new JLabel("70%"));
/* 139 */     labelTable.put(new Integer(this.ratioSlider.getMinimum() + 80), new JLabel("80%"));
/* 140 */     labelTable.put(new Integer(this.ratioSlider.getMinimum() + 90), new JLabel("90%"));
/* 141 */     labelTable.put(new Integer(this.ratioSlider.getMinimum() + 100), new JLabel("100%"));
/* 142 */     this.ratioSlider.setLabelTable(labelTable);
/*     */   }
/*     */   
/*     */   private JButton okButton;
/*     */   private JButton cancelButton;
/*     */   private JButton flipButton;
/*     */   private void createSeed()
/*     */   {
/* 147 */     this.seedPanel = new JPanel();
/* 148 */     this.seedPanel.setToolTipText("Choose The Random Number Seed Used To The Generate Field");
/* 149 */     this.seedPanel.setBorder(new TitledBorder("Seed Number"));
/* 150 */     this.seedLabel = new JLabel("", new ImageIcon("images/picseedslider.gif"), 4);
/* 151 */     this.seedPanel.add(this.seedLabel);
/* 152 */     this.seedSlider = new JSlider(0, 0, 100, 0);
/* 153 */     this.seedSlider.addChangeListener(this);
/* 154 */     this.seedSlider.setMinorTickSpacing(5);
/* 155 */     this.seedSlider.setMajorTickSpacing(20);
/* 156 */     this.seedSlider.setPaintTicks(true);
/* 157 */     this.seedSlider.setPaintLabels(true);
/* 158 */     this.seedSlider.setSnapToTicks(false);
/* 159 */     this.seedPanel.add(this.seedSlider);
/* 160 */     this.seedTextField = new JTextField(3);
/* 161 */     this.seedTextField.setEditable(false);
/* 162 */     this.seedTextField.setText("" + this.seedSlider.getValue());
/* 163 */     this.seedValueLabel = new JLabel("" + this.seedSlider.getValue());
/* 164 */     this.seedPanel.add(this.seedTextField);
/*     */   }
/*     */   
/*     */   private void createButton()
/*     */   {
/* 168 */     this.buttonPanel = new JPanel();
/*     */     
/*     */ 
/* 171 */     GridBagLayout layout = new GridBagLayout();
/* 172 */     GridBagConstraints constraints = new GridBagConstraints();
/* 173 */     constraints.fill = 1;
/* 174 */     this.buttonPanel.setLayout(layout);
/*     */     
/*     */ 
/* 177 */     this.controlPanel = new JPanel();
/* 178 */     this.controlPanel.setBorder(new BevelBorder(0));
/* 179 */     this.buttonPanel.add(this.controlPanel);
/* 180 */     this.generateButton = new JButton("Generate", new ImageIcon("images/picgeneratefieldbutton2.gif"));
/* 181 */     this.generateButton.setMnemonic('G');
/* 182 */     this.generateButton.setToolTipText("Generate A Field Using The Current Parameters");
/* 183 */     this.generateButton.addActionListener(this);
/* 184 */     this.controlPanel.add(this.generateButton);
/* 185 */     this.flipButton = new JButton("Flip Colors", new ImageIcon("images/picflipcolorsbutton.gif"));
/* 186 */     this.flipButton.setMnemonic('F');
/* 187 */     this.flipButton.setToolTipText("Generate Opposite Image Of Field");
/* 188 */     this.flipButton.setEnabled(false);
/* 189 */     this.flipButton.addActionListener(this);
/* 190 */     this.controlPanel.add(this.flipButton);
/* 191 */     this.saveButton = new JButton("Save", new ImageIcon("images/picsavebutton.gif"));
/* 192 */     this.saveButton.setMnemonic('S');
/* 193 */     this.saveButton.setToolTipText("Save These Parameters To File");
/* 194 */     this.saveButton.addActionListener(this);
/* 195 */     this.controlPanel.add(this.saveButton);
/* 196 */     this.loadButton = new JButton("Load", new ImageIcon("images/picloadbutton.gif"));
/* 197 */     this.loadButton.setMnemonic('L');
/* 198 */     this.loadButton.setToolTipText("Load A Saved Set Of Field Parameters");
/* 199 */     this.loadButton.addActionListener(this);
/* 200 */     this.controlPanel.add(this.loadButton);
/* 201 */     this.okButton = new JButton("OK", new ImageIcon("images/picstartbutton.gif"));
/* 202 */     this.okButton.setMnemonic('O');
/* 203 */     this.okButton.setToolTipText("Use Current Field In The Simulator");
/* 204 */     this.okButton.setEnabled(false);
/* 205 */     this.okButton.addActionListener(this);
/* 206 */     this.controlPanel.add(this.okButton);
/* 207 */     this.cancelButton = new JButton("Cancel", new ImageIcon("images/picstopbutton.gif"));
/* 208 */     this.cancelButton.setMnemonic('C');
/* 209 */     this.cancelButton.setToolTipText("Go Back To The Current Field");
/* 210 */     this.cancelButton.addActionListener(this);
/* 211 */     this.controlPanel.add(this.cancelButton);
/*     */     
/* 213 */     constraints.gridx = 0;
/* 214 */     constraints.gridy = 0;
/* 215 */     constraints.gridwidth = 1;
/* 216 */     constraints.gridheight = 1;
/* 217 */     constraints.weightx = 1.0D;
/* 218 */     layout.setConstraints(this.controlPanel, constraints);
/*     */     
/*     */ 
/* 221 */     this.pixelsPanel = new JPanel();
/* 222 */     this.buttonPanel.add(this.pixelsPanel);
/* 223 */     this.yellowPixelsLabel = new JLabel("Generate a Field For Field Details");
/* 224 */     this.pixelsPanel.add(this.yellowPixelsLabel);
/* 225 */     this.bluePixelsLabel = new JLabel("");
/* 226 */     this.pixelsPanel.add(this.bluePixelsLabel);
/* 227 */     this.ybRatioLabel = new JLabel("");
/* 228 */     this.pixelsPanel.add(this.ybRatioLabel);
/* 229 */     this.autoUpdate = new JCheckBox("AutoUpdate Field?", true);
/* 230 */     this.autoUpdate.addItemListener(this);
/* 231 */     this.pixelsPanel.add(this.autoUpdate);
/*     */     
/* 233 */     constraints.gridx = 0;
/* 234 */     constraints.gridy = 1;
/* 235 */     constraints.gridwidth = 1;
/* 236 */     constraints.gridheight = 1;
/* 237 */     constraints.weightx = 1.0D;
/* 238 */     layout.setConstraints(this.pixelsPanel, constraints);
/*     */   }
/*     */   private JPanel guiPanel;
/*     */   private JPanel buttonPanel;
/*     */   private JPanel controlPanel;
/*     */   private JPanel pixelsPanel;
/*     */   
/*     */   private void createLayout()
/*     */   {
/* 243 */     GridBagLayout layout = new GridBagLayout();
/* 244 */     GridBagConstraints constraints = new GridBagConstraints();
/* 245 */     constraints.fill = 1;
/* 246 */     this.guiPanel.setLayout(layout);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 256 */     this.guiPanel.add(this.grainPanel);
/* 257 */     constraints.gridx = 0;
/* 258 */     constraints.gridy = 0;
/* 259 */     constraints.gridwidth = 2;
/* 260 */     constraints.gridheight = 1;
/* 261 */     constraints.weightx = 1.0D;
/* 262 */     layout.setConstraints(this.grainPanel, constraints);
/*     */     
/* 264 */     this.guiPanel.add(this.seedPanel);
/* 265 */     constraints.gridx = 0;
/* 266 */     constraints.gridy = 2;
/* 267 */     constraints.gridwidth = 1;
/* 268 */     constraints.gridheight = 1;
/* 269 */     constraints.weightx = 1.0D;
/* 270 */     layout.setConstraints(this.seedPanel, constraints);
/*     */     
/* 272 */     this.guiPanel.add(this.ratioPanel);
/* 273 */     constraints.gridx = 1;
/* 274 */     constraints.gridy = 2;
/* 275 */     constraints.gridwidth = 1;
/* 276 */     constraints.gridheight = 1;
/* 277 */     constraints.weightx = 1.0D;
/* 278 */     layout.setConstraints(this.ratioPanel, constraints);
/*     */     
/* 280 */     this.guiPanel.add(this.buttonPanel);
/* 281 */     constraints.gridx = 0;
/* 282 */     constraints.gridy = 3;
/* 283 */     constraints.gridwidth = 2;
/* 284 */     constraints.gridheight = 2;
/* 285 */     constraints.weightx = 1.0D;
/* 286 */     layout.setConstraints(this.buttonPanel, constraints);
/*     */   }
/*     */   
/*     */   JPanel basePanel;
/*     */   
/*     */   public final void actionPerformed(ActionEvent ev)
/*     */   {
/* 290 */     if (ev.getSource() == this.generateButton) {
/* 291 */       generate();
/*     */     }
/*     */     
/* 294 */     if (ev.getSource() == this.flipButton) {
/* 295 */       this.fieldGenerator.setFlip(true);
/* 296 */       this.fieldGenerator.flipColor();
/*     */       
/* 298 */       this.generatorFrame.repaint();
/* 299 */       this.fieldGenerator.getColorInfo();
/*     */     }
/* 301 */     if (ev.getSource() == this.loadButton)
/*     */     {
/* 303 */       this.fieldGeneratorFileHandler = new FieldGeneratorFileHandler();
/* 304 */       String data = this.fieldGeneratorFileHandler.load();
/* 305 */       StringTokenizer st = new StringTokenizer(data, ":");
/* 306 */       String fileType = st.nextToken();
/* 307 */       if (fileType.equals("FIELD")) {
/* 308 */         int x = Integer.parseInt(st.nextToken());
/* 309 */         int y = Integer.parseInt(st.nextToken());
/*     */         
/* 311 */         this.iterationSlider.setValue(Integer.parseInt(st.nextToken()));
/* 312 */         this.grainSlider.setValue(Integer.parseInt(st.nextToken()));
/* 313 */         this.seedSlider.setValue(Integer.parseInt(st.nextToken()));
/* 314 */         this.ratioSlider.setValue(Integer.parseInt(st.nextToken()));
/*     */         
/* 316 */         this.fieldGenerator = null;
/* 317 */         this.fieldGenerator = new FieldGenerator(this.ratioSlider, this.yellowPixelsLabel, this.bluePixelsLabel, this.ybRatioLabel, x, y);
/*     */         
/* 319 */         this.generatorFrame.getContentPane().remove(this.generatorScrollPane);
/* 320 */         this.generatorScrollPane = new JScrollPane(this.fieldGenerator);
/* 321 */         this.generatorFrame.getContentPane().add("Center", this.generatorScrollPane);
/* 322 */         this.generatorFrame.show();
/* 323 */         this.okButton.setEnabled(false);
/*     */       }
/* 325 */       else if (fileType.equals("RESULTS")) {
/* 326 */         JOptionPane.showMessageDialog(null, "This is a file of RESULTS", "Results File", 1);
/* 327 */       } else if (fileType.equals("EDITMODEL")) {
/* 328 */         JOptionPane.showMessageDialog(null, "This is an EDIT MODEL settings file", "Edit Model File", 1);
/*     */       } else {
/* 330 */         JOptionPane.showMessageDialog(null, "This File is Not For Use With The Simulator", "Invalid File", 1);
/*     */       }
/*     */     }
/* 333 */     if (ev.getSource() == this.saveButton)
/*     */     {
/* 335 */       int width = getWidth();
/* 336 */       int height = getHeight();
/* 337 */       int iterations = this.iterationSlider.getValue();
/* 338 */       int grain = this.grainSlider.getValue();
/* 339 */       int seed = this.seedSlider.getValue();
/* 340 */       int ratio = this.ratioSlider.getValue();
/* 341 */       this.fieldGeneratorFileHandler = new FieldGeneratorFileHandler(this.fieldGenerator);
/* 342 */       this.fieldGeneratorFileHandler.save(width, height, iterations, grain, seed, ratio);
/*     */     }
/* 344 */     if (ev.getSource() == this.okButton) {
/* 345 */       Image generatedField = this.fieldGenerator.getField();
/* 346 */       this.field.setField(generatedField);
/* 347 */       this.fieldPanel.setPreferredSize(this.field.getPreferredSize());
/* 348 */       this.fieldPanel.revalidate();
/* 349 */       this.generatorFrame.dispose();
/*     */     }
/* 351 */     if (ev.getSource() == this.cancelButton) {
/* 352 */       this.generatorFrame.dispose();
/*     */     }
/*     */   }
/*     */   
/*     */   JPanel sizePanel;
/*     */   private JPanel grainPanel;
/*     */   
/*     */   public final void stateChanged(ChangeEvent e)
/*     */   {
/* 357 */     JSlider source = (JSlider)e.getSource();
/*     */     
/* 359 */     if (source == this.seedSlider) {
/* 360 */       this.seedTextField.setText("" + this.seedSlider.getValue());
/*     */     }
/* 362 */     else if (this.autoUpdate.isSelected()) {
/* 363 */       generate();
/*     */     }
/*     */   }
/*     */   
/*     */   private JPanel iterationPanel;
/*     */   private JPanel seedPanel;
/*     */   
/*     */   public void itemStateChanged(ItemEvent ie)
/*     */   {
/* 367 */     if (ie.getSource() == this.autoUpdate) {}
/*     */   }
/*     */   
/*     */   private JPanel ratioPanel;
/*     */   JScrollPane guiScrollPane;
/*     */   
/*     */   private void generate()
/*     */   {
/* 374 */     passParameters();
/* 375 */     this.fieldGenerator.setGenerate(true);
/* 376 */     this.generatorFrame.repaint();
/* 377 */     this.okButton.setEnabled(true);
/* 378 */     this.flipButton.setEnabled(true);
/*     */   }
/*     */   
/*     */   private JScrollPane generatorScrollPane;
/*     */   
/*     */   private void passParameters()
/*     */   {
/* 383 */     this.grain = this.grainSlider.getValue();
/* 384 */     this.iterations = (this.iterationSlider.getValue() - 1);
/* 385 */     this.seed = this.seedSlider.getValue();
/*     */     
/* 387 */     this.fieldGenerator.setParameters(this.grain, this.iterations, this.seed);
/*     */   }
/*     */   
/*     */   JRadioButton yellowRadioButton;
/*     */   
/*     */   private int getWidth()
/*     */   {
/* 391 */     return this.fieldGenerator.getFieldWidth();
/*     */   }
/*     */   
/*     */   JRadioButton blueRadioButton;
/*     */   
/*     */   private int getHeight()
/*     */   {
/* 395 */     return this.fieldGenerator.getFieldHeight();
/*     */   }
/*     */   
/*     */   private JTextField seedTextField;
/*     */   ButtonGroup baseGroup;
/*     */   private FieldGenerator fieldGenerator;
/*     */   private final Field field;
/*     */   String baseColor;
/*     */   int x;
/*     */   int y;
/*     */   private int grain;
/*     */   private int iterations;
/*     */   private long seed;
/*     */   private final JPanel fieldPanel;
/*     */   private FieldGeneratorFileHandler fieldGeneratorFileHandler;
/*     */   private JCheckBox autoUpdate;
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\FieldGeneratorGUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */